package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsMoveHouse;
import com.fw.service.logistics.service.LogisticsMoveHouseService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-13 11:22
 **/
@RestController
@RequestMapping("/logisticsMoveHouse")
public class LogisticsMoveHouseController {

    @Autowired
    private LogisticsMoveHouseService logisticsMoveHouseService;


    /**
     * 创建任务单
     * @param logisticsMoveHouse
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions(value = "logisticsMoveHouse:save")
    public Result save(@RequestBody LogisticsMoveHouse logisticsMoveHouse){
        return logisticsMoveHouseService.save(logisticsMoveHouse);
    }

    /**
     * 更新任务单状态
     * @param id
     * @return
     */
    @GetMapping(value = "/updateMoveHouseStatus")
    @RequiresAuthentication
    public Result updateMoveHouseStatus(@RequestParam(value = "id") Integer id){
        return logisticsMoveHouseService.updateMoveHouseStatus(id);
    }

    @GetMapping(value = "/findList")
    @RequiresAuthentication
    Result findList(@RequestParam(value = "moveHouseNo",required = false) String moveHouseNo,
                    @RequestParam(value = "status",required = false) Integer status,
                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        return logisticsMoveHouseService.findList(moveHouseNo,status,pageNum,pageSize);

    }

    @GetMapping(value = "/delete")
    @RequiresPermissions(value = "logisticsMoveHouse:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return logisticsMoveHouseService.delete(ids);
    }
}
