package com.fw.entity.board;

import lombok.Data;
/**
* @Description:    生产设备看板参数
* @Author:         gchen
* @CreateDate:     2020/10/19 15:43
*/
@Data
public class ProductParam {
    private String workOrder;//生产工单
    private String code;//产品编号
    private String name;//产品名称
    private String planYield;//计划生产
    private String realYield;//实际生产
    private String normCycle;//预计周期
    private String realCycle;//实际周期
    private String completionTime;//预计完成时间
    private String progress;//生产进度
    private String status;//设备运行状态

    public ProductParam() {
    }

    public ProductParam(String workOrder, String code, String name, String planYield, String realYield, String normCycle, String realCycle, String completionTime, String progress, String status) {
        this.workOrder = workOrder;
        this.code = code;
        this.name = name;
        this.planYield = planYield;
        this.realYield = realYield;
        this.normCycle = normCycle;
        this.realCycle = realCycle;
        this.completionTime = completionTime;
        this.progress = progress;
        this.status = status;
    }
}
