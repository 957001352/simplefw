package com.fw.service.jbpm.controller;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.service.jbpm.service.JbpmDeploymentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-22
 */
@RestController
@RequestMapping("/jbpmDeployment")
public class JbpmDeploymentController {

    @Autowired
    private JbpmDeploymentService jbpmDeploymentService;

    /**
     * 新增/修改设备保养项目
     *
     * @param jbpmDeployment
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("jbpmDeployment:save")
    public Result save(@RequestBody JbpmDeployment jbpmDeployment) {
        return jbpmDeploymentService.save(jbpmDeployment);
    }


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("jbpmDeployment:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return jbpmDeploymentService.delete(ids);
    }

    /**
     * 列表查询
     *
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return jbpmDeploymentService.findList(code, name, pageNum, pageSize);
    }
    /**
    * 流程审核表单
     * @param
    * @return
    */
    @GetMapping("/findJbpmFormList")
    @RequiresAuthentication
    public Result findJbpmFormList() {
        return jbpmDeploymentService.findJbpmFormList();
    }
    /**
     * 用户列表查询
     * @param
     * @return
     */
    @GetMapping("/findUserList")
    @RequiresAuthentication
    public Result findUserList() {
        return jbpmDeploymentService.findUserList();
    }
}
