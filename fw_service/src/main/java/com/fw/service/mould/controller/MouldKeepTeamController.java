package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTeam;
import com.fw.service.mould.service.MouldKeepTeamService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 模具保养项目组
 * @date 2020/10/27
 */
@RestController
@RequestMapping(value = "/mouldKeepTeam")
public class MouldKeepTeamController {

    @Autowired
    private MouldKeepTeamService mouldKeepTeamService;

    /**
     * 新增/修改模具保养项目组
     *
     * @param mouldKeepTeam
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody MouldKeepTeam mouldKeepTeam) {
        return mouldKeepTeamService.save(mouldKeepTeam);
    }


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldKeepTeamService.delete(ids);
    }


    /**
     * 获取模具保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getAllKeepItem")
    public Result getAllKeepItem() {
        return mouldKeepTeamService.getAllKeepItem();
    }


    /**
     * 列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepTeamService.findList(name, mouldCode, pageNum, pageSize);
    }

    /**
     * 获取所有模具编号
     *
     * @return result
     */
    @GetMapping(value = "/getMouldCode")
    public Result getMouldCode() {
        return mouldKeepTeamService.getMouldCode();
    }


    /**
     * 查看模具保养项目组
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldKeepTeam")
    public Result getMouldKeepTeam(@RequestParam(value = "id") Integer id) {
        return mouldKeepTeamService.getMouldKeepTeam(id);
    }

    /**
     * 根据保养周期获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getKeepItemByCycle")
    public Result getKeepItemByCycle(@RequestParam(value = "cycle") Integer cycle) {
        return mouldKeepTeamService.getKeepItemByCycle(cycle);
    }
}
