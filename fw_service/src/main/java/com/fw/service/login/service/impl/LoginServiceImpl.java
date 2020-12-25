package com.fw.service.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.PdaLoginLog;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.exceptions.MyException;
import com.fw.service.RedisService;
import com.fw.service.basic.dao.PdaLoginLogDao;
import com.fw.service.device.service.DevicesCheckService;
import com.fw.service.login.service.LoginService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.FwConst;
import com.fw.service.util.HeaderUtil;
import com.fw.systemconst.Const;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private RedisService redisService;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private PdaLoginLogDao pdaLoginLogDao;
    @Autowired
    private DevicesCheckService devicesCheckService;

    @Override
    public Result checkToken() {
        Result result = e2CServicesUtil.linkedE2C(FwConst.GET,"/checkToken", null);
        if(result == null){
            return ResultUtils.error("校验失败");
        }
        if(result.getCode() == 0){
            Map<String, Object> map = (Map<String, Object>) result.getData();
            Map<String,String> permissions = (Map<String, String>) map.get("permissions");
            String token = map.get("token")+"";
            User user = JSON.parseObject( map.get("loginUser")+"",User.class);
            long expire = Long.parseLong(map.get("expire")+"");

            redisService.set(Const.TOKEN_CACHE_ITEM_PREFIX+token,JSON.toJSONString(user),expire); //与token过期时间保持一致
            redisService.set(Const.PERMISSIONS_CACHE_ITEM_PREFIX+user.getLoginName(),JSON.toJSONString(permissions.get("perms")),expire);//将用户app应用权限存入app应用中

            //返回前端需要数据
            Map<String,Object> result1 = new HashMap<>();
            result1.put("permissions",permissions.get("codes"));
            result1.put("token",token);
            result1.put("loginUser",user);
            return ResultUtils.success(map);

        }
        return result;
    }

    @Override
    public Result login(String loginName, String password) {
        if(CheckUtils.isNull(loginName) || CheckUtils.isNull(password)){
            return ResultUtils.error("参数错误");
        }
        User user1 = new User();
        user1.setLoginName(loginName);
        user1.setPassword(password);
        Result result = e2CServicesUtil.linkedE2CPostParams(FwConst.POST, "/login", JSON.toJSONString(user1));
        if(result == null){
            return ResultUtils.error("登陆账号或密码错误");
        }
        if(result.getCode() == 0){
            Map<String, Object> map = (Map<String, Object>) result.getData();
            Map<String,String> permissions = (Map<String, String>) map.get("permissions");
            String token = map.get("token")+"";
            User user = JSON.parseObject( map.get("loginUser")+"",User.class);

            redisService.set(Const.TOKEN_CACHE_ITEM_PREFIX+token,JSON.toJSONString(user));
            redisService.set(Const.PERMISSIONS_CACHE_ITEM_PREFIX+user.getLoginName(),JSON.toJSONString(permissions.get("perms")));//将用户app应用权限存入app应用中
        }
        return result;
    }



    @Override
    @Transactional
    public Result pdaLogin(String loginName, String password, String productDeviceCode) {
        if(CheckUtils.isNull(loginName) || CheckUtils.isNull(password) || CheckUtils.isNull(productDeviceCode)){
            return ResultUtils.error("参数错误");
        }
        User user1 = new User();
        user1.setLoginName(loginName);
        user1.setPassword(password);
        Result result = e2CServicesUtil.linkedE2CPostParams(FwConst.POST, "/login", JSON.toJSONString(user1));
        if(result == null){
            return ResultUtils.error("登陆账号或密码错误");
        }
        if(result.getCode() == 0){
            Map<String, Object> map = (Map<String, Object>) result.getData();
            String token = map.get("token")+"";
            DevicesItemVo devicesItemVo = getProductDevicesId(token, productDeviceCode);
            if(devicesItemVo == null) {
                return ResultUtils.error("设备编码填写错误");
            }

            if(redisService.hasKeyAndItem(FwConst.DEVICE_LOGIN_TOKEN_KEY,productDeviceCode)){
                String deviceToken = redisService.hget(FwConst.DEVICE_LOGIN_TOKEN_KEY, productDeviceCode).toString();
                updateLogExitTime(deviceToken,Integer.parseInt(devicesItemVo.getId()));
                redisService.del(Const.TOKEN_CACHE_ITEM_PREFIX+deviceToken);
            }
            redisService.hset(FwConst.DEVICE_LOGIN_TOKEN_KEY,productDeviceCode,token);

            Map<String,String> permissions = (Map<String, String>) map.get("permissions");
            User user = JSON.parseObject( map.get("loginUser")+"",User.class);

            redisService.set(Const.TOKEN_CACHE_ITEM_PREFIX+token,JSON.toJSONString(user));
            redisService.set(Const.PERMISSIONS_CACHE_ITEM_PREFIX+user.getLoginName(),JSON.toJSONString(permissions.get("perms")));//将用户app应用权限存入app应用中
            //保存登录日志
            PdaLoginLog pdaLoginLog = new PdaLoginLog();
            pdaLoginLog.setProductDevicesId(Integer.parseInt(devicesItemVo.getId()));
            pdaLoginLog.setUserId(user.getId());
            pdaLoginLog.setLoginTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
            pdaLoginLogDao.insert(pdaLoginLog);
            //插入点检任务
            devicesCheckService.insert(user.getId(),Integer.parseInt(devicesItemVo.getId()));
            map.put("device",devicesItemVo);
            result.setData(map);
        }
        return result;
    }

    @Override
    public Result padLogout() {
        try{
            //删除redis里缓存的token
            String token = headerUtil.cloudToken();
            redisService.del(Const.TOKEN_CACHE_ITEM_PREFIX+token);
            return ResultUtils.success();
        }catch (MyException e){
            return ResultUtils.failure();
        }
    }

    @Override
    @Transactional
    public Result pdaLogout(String productDeviceCode) {
        if(!redisService.hasKeyAndItem(FwConst.DEVICE_LOGIN_TOKEN_KEY, productDeviceCode)){
            return ResultUtils.error(ResultEnum.NO_LOGIN);
        }
        String deviceToken = redisService.hget(FwConst.DEVICE_LOGIN_TOKEN_KEY, productDeviceCode).toString();
        DevicesItemVo devicesItemVo = getProductDevicesId(deviceToken, productDeviceCode);
        if(devicesItemVo == null) {
            return ResultUtils.error("设备编码填写错误");
        }
        updateLogExitTime(deviceToken,Integer.parseInt(devicesItemVo.getId()));
        redisService.hdel(FwConst.DEVICE_LOGIN_TOKEN_KEY,productDeviceCode);
        return padLogout();
    }

    //获取登陆设备id
    public DevicesItemVo getProductDevicesId(String token,String productDeviceCode){
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(token);
        DevicesItemVo device = null;
        for (DevicesItemVo devicesItemVo:productDevices) {
            if(productDeviceCode.equals(devicesItemVo.getName())){
                device = devicesItemVo;
            }
        }
        return device;
    }

    //设置登陆日志的退出时间
    public void updateLogExitTime(String token,Integer productDevicesId){
        if(redisService.hasKey(Const.TOKEN_CACHE_ITEM_PREFIX + token)){
            String oldLoginUser = redisService.get(Const.TOKEN_CACHE_ITEM_PREFIX + token).toString();
            User user = JSON.parseObject(oldLoginUser, User.class);
            PdaLoginLog unFinishLog = pdaLoginLogDao.findUnFinishLog(user.getId(), productDevicesId);
            if(unFinishLog != null){
                unFinishLog.setExitTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
                pdaLoginLogDao.update(unFinishLog);
            }
        }
    }
}
