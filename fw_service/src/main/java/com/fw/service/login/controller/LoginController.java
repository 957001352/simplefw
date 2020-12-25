package com.fw.service.login.controller;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.service.login.service.LoginService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录 登出 校验token
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginServiceImpl;

    /**
     * token校验
     */
    @GetMapping("/checkToken")
    public Result checkToken(){
        return loginServiceImpl.checkToken();
    }

    /**
     * PAD登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return loginServiceImpl.login(user.getLoginName(),user.getPassword());
    }

    /**
     * PAD登出
     */
    @GetMapping("/padLogout")
    public Result padLogout(){
        return loginServiceImpl.padLogout();
    }
    /**
     * PDA登录
     */
    @PostMapping("/pdaLogin")
    public Result pdaLogin(@RequestBody User user){
        return loginServiceImpl.pdaLogin(user.getLoginName(),user.getPassword(),user.getProductDeviceCode());
    }

    /**
     * PDA登出
     */
    @GetMapping("/pdaLogout")
    public Result pdaLogout(@RequestParam("productDeviceCode") String productDeviceCode){
        return loginServiceImpl.pdaLogout(productDeviceCode);
    }
}
