package com.fw.entity.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 二次加工排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@Data
@ApiModel(value = "reworkInjection", description = "二次加工排产计划")
public class ReworkInjection implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 物料对象
     */
    @ApiModelProperty(value = "物料对象")
    private Integer productId;
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Integer customerId;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 计划生产数量
     */
    @ApiModelProperty(value = "计划生产数量")
    private Double planCount;
    /**
     * 实际生产数量
     */
    @ApiModelProperty(value = "实际生产数量")
    private String productCount;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    /**
     * 优先级 0普通 1紧急
     */
    @ApiModelProperty(value = "优先级 0普通 1紧急")
    private Integer priority;
    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productCode;
    /**
     * 原材料
     */
    @ApiModelProperty(value = "原材料")
    private String material;
    /**
     * 生产时长
     */
    @ApiModelProperty(value = "生产时长")
    private Double productTime;
    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    private String deliverTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
    /**
     * 状态 0 待生产 1生产中 2 暂停 3 取消 4 完成
     */
    @ApiModelProperty(value = "状态 0 待生产 1生产中 2 暂停 3 取消 4 完成")
    private Integer status;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", hidden = true)
    private Integer updateUser;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", hidden = true)
    private String updateTime;

    /**
     * 暂停时长
     */
    @ApiModelProperty(value = "暂停时长")
    private Double stopTime;

    /**
     * 移动位数
     */
    @ApiModelProperty(value = "移动位数", hidden = true)
    private Integer moveCount;

    /**
     * 物料长代码
     */
    @ApiModelProperty(value = "物料长代码", hidden = true)
    private String partsCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称", hidden = true)
    private String partsName;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createUser;
    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private String createTime;
    /**
     * 零件单位
     */
    @ApiModelProperty(value = "零件单位",hidden = true)
    private String unit;
}
