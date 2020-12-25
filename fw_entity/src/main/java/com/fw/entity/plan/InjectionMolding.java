package com.fw.entity.plan;

import com.fw.entity.craft.CraftModel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 注塑排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@Data
@ApiModel(value = "injectionMolding", description = "注塑排产计划")
public class InjectionMolding implements Serializable {

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
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码",hidden = true)
    private String partsCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称",hidden = true)
    private String partsName;
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
     * 生产设备
     */
    @ApiModelProperty(value = "生产设备")
    private Integer productDevicesId;
    /**
     * 生产设备名称
     */
    @ApiModelProperty(hidden = true,value = "生产设备名称")
    private String productDevicesName;
    /**
     * 模具
     */
    @ApiModelProperty(value = "模具")
    private Integer mouldId;

    @ApiModelProperty(hidden = true,value = "模具名称")
    private String mouldName;

    @ApiModelProperty(hidden = true,value = "模具编码")
    private String mouldCode;
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
     * 暂停时间
     */
    @ApiModelProperty(value = "暂停时间")
    private Integer stopTime;

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
     * 设备id 用逗号隔开
     */
    @ApiModelProperty(value = "设备id 用逗号隔开")
    private String productIds;

    /**
     * 工艺卡模型
     */
    @ApiModelProperty(value = "工艺卡模型")
    private CraftModel craftMode;
    /**
     * 模具型腔数
     */
    @ApiModelProperty(value = "模具型腔数")
    private String cavity;
    /**
     * 实际开始时间
     */
    @ApiModelProperty(value = "实际开始时间")
    private String actualStart;

    /**
     * 实际完成时间
     */
    @ApiModelProperty(value = "实际完成时间")
    private String actualEnd;
    /**
     * 设备code
     */
    @ApiModelProperty(value = "设备code")
    private String productDeviceCode;

    /**
     * 上模时间
     */
    @ApiModelProperty(value = "上模时间",hidden = true)
    private String createTime;

    /**
     * 上模人员
     */
    @ApiModelProperty(value = "上模人员",hidden = true)
    private Integer createUser;

    /**
     * 零件单位
     */
    @ApiModelProperty(value = "零件单位",hidden = true)
    private String unit;

    /**
     * 是否生成模具出库任务
     */
    @ApiModelProperty(value = "是否生成模具出库任务",hidden = true)
    private Boolean checked;
}
