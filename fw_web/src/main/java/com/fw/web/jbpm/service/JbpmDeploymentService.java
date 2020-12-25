package com.fw.web.jbpm.service;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.web.jbpm.service.fbk.JbpmDeploymentServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-22
 */
@FeignClient(value = "fw-service/jbpmDeployment", fallback = JbpmDeploymentServiceFbk.class)
public interface JbpmDeploymentService {


    /**
     * 新增/修改
     *
     * @param jbpmDeployment
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody JbpmDeployment jbpmDeployment);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

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
    Result findList(@RequestParam(value = "code", required = false) String code,
                    @RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);



    @GetMapping("/findJbpmFormList")
    Result findJbpmFormList();


    @GetMapping("/findUserList")
    Result findUserList();
}
