package com.fw.web.login.service;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.web.login.service.fbk.LoginServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service", fallback = LoginServiceFbk.class)
public interface LoginService {
    /**
     * token校验
     */
    @GetMapping("/checkToken")
    Result checkToken();

    /**
     * pad登录
     */
    @PostMapping("/login")
    Result login(@RequestBody User user);

    /**
     * PAD登出
     */
    @GetMapping("/padLogout")
    Result padLogout();

    /**
     * PDA登录
     */
    @PostMapping("/pdaLogin")
    Result pdaLogin(@RequestBody User user);

    /**
     * PDA登出
     */
    @GetMapping("/pdaLogout")
    Result pdaLogout(@RequestParam("productDeviceCode") String productDeviceCode);
}
