package com.fw.service.mould.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.mould.MouldRepair;

public interface MouldRepairService {

    /**
     * 新增
     * @param mouldRepair
     * @return
     */
    Result save(MouldRepair mouldRepair);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 查询
     * @param code  维修单编码
     * @param mouldId  模具编码
     * @param mouldName   模具名称
     * @return
     */
    Result findList(Integer id,String code, String mouldId, String mouldName, String priority, Integer repairProjectStatus, Integer repairExeStatus, Integer pageNum, Integer pageSize);


    /**
     * 执行维修模具任务
     * @param mouldRepair
     * @return
     */
    Result repairTaskExecute(MouldRepair mouldRepair);

    /**
     * 制作模具维修方案
     * @param mouldRepair
     * @return
     */
    Result makeProject(MouldRepair mouldRepair);


    /**
     * 任务领取
     * @param mouldRepair
     * @return
     */
    Result getTask(MouldRepair mouldRepair);


    /**
     * 审核通过调用接口
     * @param id
     * @return
     */
    public Result auditPass(Integer id);

}
