package com.fw.web.jbpm.controller;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.utils.ResultUtils;
import com.fw.web.jbpm.service.JbpmDeploymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-21
 */
@RestController
@RequestMapping(value = "/jbpmDeployment")
@Api(description = "审核流程定义", value = "JbpmDeploymentController")
public class JbpmDeploymentController {

    @Autowired
    private JbpmDeploymentService jbpmDeploymentService;

    /**
     * 新增/修改设备保养
     *
     * @param jbpmDeployment
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改审核流程定义")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody JbpmDeployment jbpmDeployment, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return jbpmDeploymentService.save(jbpmDeployment);
        }
        return result;
    }



    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return jbpmDeploymentService.delete(ids);
    }

    /**
     * 列表查询
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("审核流程定义列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return jbpmDeploymentService.findList(code, name,pageNum, pageSize);
    }
    /**
     * 流程审核表单
     * @param
     * @return
     */
    @GetMapping("/findJbpmFormList")
    public Result findJbpmFormList() {
        return jbpmDeploymentService.findJbpmFormList();
    }


    /**
     * 用户列表查询
     * @param
     * @return
     */
    @GetMapping("/findUserList")
    public Result findUserList() {
        return jbpmDeploymentService.findUserList();
    }
}
