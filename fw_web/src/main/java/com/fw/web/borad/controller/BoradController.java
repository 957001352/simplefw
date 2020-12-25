package com.fw.web.borad.controller;

import com.fw.domain.Result;
import com.fw.entity.board.InfoBox;
import com.fw.web.borad.service.BoradService;
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
    public Result overView(@RequestBody InfoBox infoBox){
        return boradServiceImpl.overView(infoBox);
    }
}
