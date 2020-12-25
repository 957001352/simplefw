package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsMoveHouse;
import com.fw.web.logistics.service.LogisticsMoveHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-13 11:32
 **/

@RestController
@RequestMapping("/logisticsMoveHouse")
@Api(description = "移库管理", value = "LogisticsMoveHouseController")
public class LogisticsMoveHouseController {

    @Autowired
    private LogisticsMoveHouseService logisticsMoveHouseService;

    /**
     * 创建任务单
     * @param logisticsMoveHouse
     * @return
     */
    @ApiOperation(value = "创建任务单")
    @PostMapping(value = "/save")
    public Result save(@RequestBody LogisticsMoveHouse logisticsMoveHouse){
        return logisticsMoveHouseService.save(logisticsMoveHouse);
    }

    /**
     * 更新任务单状态
     * @param id
     * @return
     */
    @ApiOperation(value = "更新任务单状态")
    @GetMapping(value = "/updateMoveHouseStatus")
    public Result updateMoveHouseStatus(@RequestParam(value = "id") Integer id){
        return logisticsMoveHouseService.updateMoveHouseStatus(id);
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "moveHouseNo",required = false) String moveHouseNo,
                    @RequestParam(value = "status",required = false) Integer status,
                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        return logisticsMoveHouseService.findList(moveHouseNo,status,pageNum,pageSize);

    }

    @ApiOperation(value = "批量删除")
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids){
        return  logisticsMoveHouseService.delete(ids);
    }
}
