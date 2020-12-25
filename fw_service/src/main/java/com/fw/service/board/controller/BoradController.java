package com.fw.service.board.controller;

import com.fw.domain.Result;
import com.fw.entity.board.InfoBox;
import com.fw.service.board.service.BoradService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
* @Description:    看盘管理
* @Author:         gchen
* @CreateDate:     2020/10/19 16:19
*/
@RestController
@RequestMapping("/borad")
public class BoradController {
    @Resource
    private BoradService boradServiceImpl;

    /**
     * 看板总览
     */
    @PostMapping("/overView")
    @RequiresAuthentication
    public Result overView(@RequestBody InfoBox infoBox){
        return boradServiceImpl.overView(infoBox.getCodes());
    }
}
