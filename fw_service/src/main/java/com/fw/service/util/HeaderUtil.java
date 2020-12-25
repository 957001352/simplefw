package com.fw.service.util;

import com.alibaba.fastjson.JSON;
import com.fw.entity.e2c.User;
import com.fw.service.RedisService;
import com.fw.systemconst.Const;
import com.fw.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author gchen
 * @Date 2020/10/19
 */
@Component
public class HeaderUtil {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisService redisService;

    public Integer tenantId() {
        return Convert.stringToInteger(request.getHeader(FwConst.DHLK_TENANTID));
    }

    public String cloudToken() {
        return request.getHeader(Const.TOKEN_HEADER);
    }

    public User loginUser() {
        String userInfo = redisService.get(Const.TOKEN_CACHE_ITEM_PREFIX + cloudToken()).toString();
        return JSON.parseObject(userInfo, User.class);
    }
}