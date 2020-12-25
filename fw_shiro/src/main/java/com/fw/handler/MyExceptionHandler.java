package com.fw.handler;

import com.fw.domain.Result;
import com.fw.enums.ResultEnum;
import com.fw.exceptions.MyException;
import com.fw.utils.Convert;
import com.fw.utils.ResultUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description 统一异常处理
 * @Author lpsong
 * @Date 2020/3/31
 */
@RestControllerAdvice
public class MyExceptionHandler{

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myException(MyException e) {
        e.printStackTrace();
        return ResultUtils.error(Convert.stringToInteger(e.getCode()), e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    @ResponseBody
    public Result defaultErrorHandler(UnauthorizedException e)  {
        e.printStackTrace();
        return ResultUtils.error(ResultEnum.NO_PERMISS);
    }

    @ExceptionHandler(value = UnauthenticatedException.class)//处理访问方法时权限不足问题
    @ResponseBody
    public Result errorHandler(UnauthenticatedException e)  {
        e.printStackTrace();
        return ResultUtils.error(ResultEnum.NO_LOGIN);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result requestSupportException(HttpRequestMethodNotSupportedException e){//处理非法请求
        return ResultUtils.error(ResultEnum.URL_ERROR.getStateInfo());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result Exception(Exception e) {
       e.printStackTrace();
        return ResultUtils.error(ResultEnum.UNKNOWN_ERR.getState(), ResultEnum.UNKNOWN_ERR.getStateInfo());
    }

}
