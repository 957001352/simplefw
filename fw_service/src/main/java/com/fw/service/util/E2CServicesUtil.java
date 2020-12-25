package com.fw.service.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.utils.CheckUtils;
import com.fw.utils.HttpClientResult;
import com.fw.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xkliu
 * @des 调用E2C的设备接口
 * @date 2020/10/21
 */
@Component
@Slf4j
public class E2CServicesUtil {

    @Autowired
    private HeaderUtil headerUtil;
    @Value("${E2C.baseUrl}")
    private String baseUrl;

    /**
     * 根据token获取E2C平台设备的数据
     *
     * @param token
     * @return
     */
    public List<DevicesItemVo> getProductDevices(String token) {
        return getProductDevices(token, null);
    }

    public List<DevicesItemVo> getProductDevices(String token, Map<String, String> params) {
        List<DevicesItemVo> devicesItems = new ArrayList<>();
        try {
            HttpClientResult clientResult = HttpClientUtils.doGet(baseUrl + "/productDevices/findList", getHeader(token), params);
            log.info("访问e2c返回的数据为{}", clientResult.toString());
            //获取E2C设备数据
            if (clientResult.getCode() == 200) {
                Result result = JSON.parseObject(clientResult.getContent(), Result.class);
                if (result.getCode() == 7001) {
                    return devicesItems;
                }
                if (!CheckUtils.isNull(result.getData())) {
                    devicesItems = JSONArray.parseArray(result.getData().toString(), DevicesItemVo.class);
                }
                return devicesItems;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devicesItems;
    }

    /**
     * 根据token获取E2C平台设备类型数据
     *
     * @param token
     * @return
     */
    public List<DevicesClassify> getdevicesClassify(String token) {
        List<DevicesClassify> devicesClassifyList = new ArrayList<>();
        try {
            HttpClientResult clientResult = HttpClientUtils.doGet(baseUrl + "/devicesClassify/findList", getHeader(token), null);
            if (clientResult.getCode() == 200) {
                Result result = JSON.parseObject(clientResult.getContent(), Result.class);
                if (result.getCode() == 7001) {
                    return devicesClassifyList;
                }
                if (!CheckUtils.isNull(result.getData())) {
                    devicesClassifyList = JSONArray.parseArray(result.getData().toString(), DevicesClassify.class);
                }
                return devicesClassifyList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devicesClassifyList;
    }

    /**
     * 根据token获取E2C平台设备类型数据
     *
     * @param token
     * @return
     */
    public List<User> getUserList(String token) {
        return getUserList(token, null);
    }

    public List<User> getUserList(String token, Map<String, String> params) {
        List<User> userList = new ArrayList<>();
        try {
            HttpClientResult clientResult = HttpClientUtils.doGet(baseUrl + "/user/findList", getHeader(token), params);
            //获取E2C设备数据
            if (clientResult.getCode() == 200) {
                Result result = JSON.parseObject(clientResult.getContent(), Result.class);
                if (result.getCode() == 7001) {
                    return userList;
                }
                if (!CheckUtils.isNull(result.getData())) {
                     userList = JSONArray.parseArray(result.getData().toString(), User.class);
                }
                return userList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /*
     * 根据用户id返回E2C用户名称
     * @param userIds
     * @return
     */
    public String findUserNameById(String userIds) {
        List<User> userList = this.getUserList(headerUtil.cloudToken());
        List<String> userNames = new ArrayList<>();
        for (String userId : userIds.split(",")) {
            if (userList != null) {
                userList.forEach(vo -> {
                    if (userId.equals(vo.getId().toString())) {
                        userNames.add(vo.getName());
                    }
                });
            }
        }
        return StringUtils.join(userNames.toArray(), ",");
    }

    public Map<String, String> getHeader(String token) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);
        return headers;
    }

    /**
     * 调用E2C接口
     */
    public Result linkedE2C(String request, String url, Map<String, String> params) {
        try {
            HttpClientResult clientResult = null;
            if ("GET".equals(request)) {
                clientResult = HttpClientUtils.doGet(baseUrl + url, getHeader(headerUtil.cloudToken()), params);
            } else if ("POST".equals(request)) {
                clientResult = HttpClientUtils.doPost(baseUrl + url, getHeader(headerUtil.cloudToken()), params);
            }
            log.info("=========================================>" + clientResult);
            //校验E2C的token令牌
            if (clientResult != null && clientResult.getCode() == 200) {
                return JSON.parseObject(clientResult.getContent(), Result.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 调用E2C接口
     */
    public Result linkedE2CPostParams(String request, String url, String params) {
        try {
            HttpClientResult clientResult = HttpClientUtils.doPostStringParams(baseUrl + url, getHeader(headerUtil.cloudToken()), params);

            log.info("=========================================>" + clientResult);
            //校验E2C的token令牌
            if (clientResult.getCode() == 200) {
                return JSON.parseObject(clientResult.getContent(), Result.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 根据用户id获取用户信息
     */
    public User getUserById(Integer userId) {
        Map<String,String> params = new HashMap<>();
        if(CheckUtils.isNull(userId)){
            return null;
        }
        params.put("id",userId.toString());
        Result result = linkedE2C(FwConst.GET, "/user/findUserById", params);
        if(result != null && result.getCode() == 0){
            return JSON.parseObject(result.getData() + "", User.class);
        }
        return null;
    }
}
