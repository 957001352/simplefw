package com.fw.service.login.service;

import com.fw.domain.Result;

/**
 * 登录 登出 校验token
 */
public interface LoginService {

    /**
     * 校验token
     */
    Result checkToken();

    /**
     * pad登录
     */
    Result login(String loginName, String password);

    /**
     * pad登出
     */
    Result padLogout();

    /**
     * PDA登录
     */
    Result pdaLogin(String loginName, String password, String productDeviceCode);

    /**
     * PDA登出
     */
    Result pdaLogout(String productDeviceCode);
}
