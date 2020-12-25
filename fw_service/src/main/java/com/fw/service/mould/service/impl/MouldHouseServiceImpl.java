package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldHouse;
import com.fw.entity.mould.MouldStorageLocation;
import com.fw.enums.ResultEnum;
import com.fw.exceptions.MyException;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.MouldHouseDao;
import com.fw.service.mould.dao.MouldStorageHouseDao;
import com.fw.service.mould.service.MouldHouseService;
import com.fw.service.mould.service.MouldKeepTaskService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 模具入库、出库、移库
 * @author lpsong
 * @since 2020-10-26
 */
@Service
public class MouldHouseServiceImpl implements MouldHouseService {

    @Autowired
    private MouldHouseDao mouldHouseDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private MouldStorageHouseDao mouldStorageHouseDao;
    @Value("${attachment.path}")
    private String rootPath;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private MouldKeepTaskService mouldKeepTaskService;
    @Override
    @Transactional
    public Result save(MouldHouse mouldHouse) {
        Integer flag = 0;
        //id为空新增设备保养项目
        if (CheckUtils.isNull(mouldHouse.getId())) {
            mouldHouse.setCreateUser(authUserUtil.userId());
            if(CheckUtils.isNull(mouldHouse.getMouldId())){
                return ResultUtils.error(ResultEnum.PARAM_ERR);
            }
            MouldDevices mould= mouldHouseDao.selectMouldDevicesById(mouldHouse.getMouldId());
            if(mould==null){
                return ResultUtils.error("模具不存在");
            }
            MouldStorageLocation mouldStorageLocation= mouldStorageHouseDao.selectLocationById(mould.getNowLocationId());
            if(CheckUtils.isNull(mouldHouse.getNowLocationId())){
                //mouldHouse.getNowLocationId() 为空，表示出入库
                mouldHouse.setNowLocationId(mould.getNowLocationId());
            }else{
                //mouldHouse.getNowLocationId() 不为空，表示移库，移库时将模具老的库位记录到库存操作记录表里
                mouldHouse.setOldLocationId(mould.getNowLocationId());
            }
            //编码设置
            if(mouldHouse.getOperate()==0){//入库
                mouldHouse.setHouseNo(mouldHouseDao.findCode(CodeEnum.MOULD_06.getCode()));
            }else if(mouldHouse.getOperate()==1){//出库
                mouldHouse.setHouseNo(mouldHouseDao.findCode(CodeEnum.MOULD_05.getCode()));
            }else if(mouldHouse.getOperate()==2){//移库
                mouldHouse.setHouseNo(mouldHouseDao.findCode(CodeEnum.MOULD_07.getCode()));
            }
            flag = mouldHouseDao.insert(mouldHouse);
            if(flag>0){
                MouldDevices mouldDevices=new MouldDevices();
                mouldDevices.setId(mouldHouse.getMouldId());
                if(mouldHouse.getOperate()==0){//入库
                    mouldStorageLocation.setStatus(5);//模具待入库
                }else if(mouldHouse.getOperate()==1){//出库
                    mouldStorageLocation.setStatus(3);//模具待出库
                }else if(mouldHouse.getOperate()==2){//移库
                    mouldStorageLocation.setStatus(7);//模具待移库
                }
                mouldStorageHouseDao.updateLocation(mouldStorageLocation);
            }
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            MouldHouse entrty = mouldHouseDao.selectById(mouldHouse.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            mouldHouse.setExecuteUser(authUserUtil.userId());
            mouldHouse.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            mouldHouse.setStatus(1);//已执行
            flag = mouldHouseDao.update(mouldHouse);
            if(flag>0){
                MouldStorageLocation nowStorageLocation= mouldStorageHouseDao.selectLocationById(entrty.getNowLocationId());
                if(entrty.getOperate()==0){//入库
                    nowStorageLocation.setStatus(6);//模具已入库
                }else if(entrty.getOperate()==1){//出库
                    nowStorageLocation.setStatus(4);//模具已出库
                    //判断是否是生产指令产生的模具出库任务
                    if(entrty.getInjectionMoldingId() != null){
                        Integer con = mouldKeepTaskService.issuedTask(2, entrty.getMouldId(),entrty.getInjectionMoldingId());
                        if(con < 1){
                            throw new MyException("500","生成产前保养任务出错");
                        }
                    }
                }else if(entrty.getOperate()==2){//移库
                    //移库时，修改模具库位
                    nowStorageLocation.setStatus(6);//模具已入库
                    MouldDevices mould= mouldHouseDao.selectMouldDevicesById(entrty.getMouldId());
                    mould.setNowLocationId(entrty.getNowLocationId());
                    mould.setOldLocationId(entrty.getNowLocationId());
                    mouldHouseDao.updateMouldStatus(mould);
                    //移库时，修改旧库位状态
                    MouldStorageLocation oldStorageLocation= mouldStorageHouseDao.selectLocationById(entrty.getOldLocationId());
                    oldStorageLocation.setStatus(0);//库位状态设初始状态0
                    mouldStorageHouseDao.updateLocation(oldStorageLocation);
                }
                mouldStorageHouseDao.updateLocation(nowStorageLocation);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        flag = mouldHouseDao.delete(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String code, String mouldCode,Integer operate, String startTime, String endTime,Integer status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldHouse> lists = mouldHouseDao.findList(code,mouldCode,operate,startTime,endTime,status,rootPath);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(lists)&&!CollectionUtils.isEmpty(userList)) {
            //用户设置
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    if (!CheckUtils.isNull(item.getCreateUser()) && item.getCreateUser().equals(vo.getId())) {
                        item.setCreateUserName(vo.getName());
                    }
                });
            });
        }
        return ResultUtils.success( new PageInfo<>(lists));
    }
}
