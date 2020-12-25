package com.fw.web.login.controller;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.web.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录 登出 校验token
 */
@RestController
@Api(description = "登录、token校验",value = "LoginController")
public class LoginController {
    @Resource
    private LoginService loginServiceImpl;

    /**
     * token校验
     */
    @GetMapping("/checkToken")
    @ApiOperation(value = "token校验")
    public Result checkToken(){
        return loginServiceImpl.checkToken();
    }

    /**
     * pad登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "pad登录")
    public Result login(@RequestBody User user){
        return loginServiceImpl.login(user);
    }

    /**
     * PAD登出
     */
    @GetMapping("/padLogout")
    @ApiOperation(value = "PAD登出")
    public Result padLogout(){
        return loginServiceImpl.padLogout();
    }
    /**
     * PDA登录
     */
    @PostMapping("/pdaLogin")
    @ApiOperation(value = "PDA登录")
    public Result pdaLogin(@RequestBody User user){
        return loginServiceImpl.pdaLogin(user);
    }

    /**
     * PDA登出
     */
    @GetMapping("/pdaLogout")
    @ApiOperation(value = "PDA登出")
    public Result pdaLogout(@RequestParam("productDeviceCode") String productDeviceCode){
        return loginServiceImpl.pdaLogout(productDeviceCode);
    }
}
