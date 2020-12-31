package com.fw.service.login.controller;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.service.login.service.LoginService;
import com.fw.utils.DogUtil;
import com.fw.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录 登出 校验token
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginServiceImpl;
    @Value("${dog.check}")
    private boolean check;

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
        if(check || DogUtil.checkLoginDog()){
            return  loginServiceImpl.login(user.getLoginName(),user.getPassword());
        }
        return ResultUtils.error(ResultEnum.DOG_ERROR);
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
