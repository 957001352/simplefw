package com.fw.web.login.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.login.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceFbk implements LoginService {
    @Override
    public Result checkToken() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result login(User user) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result padLogout() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result pdaLogin(User user) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result pdaLogout(String productDeviceCode) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
