package com.fw.entity.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 物料过程监控
 * @author lpsong
 * @since 2020-12-15 16:45:40
 */
@Data
@ApiModel(value = "物料过程监控实体")
public class ProduceMoldingMonitor implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
    * 注塑排产计划
    */
    @ApiModelProperty(value = "注塑排产计划")
    private Integer planMoldingId;
    /**
    * 生产指令
    */
    @ApiModelProperty(value = "生产指令")
    private String productOrder;
    /**
    * 生产设备
    */
    @ApiModelProperty(value = "生产设备")
    private Integer productDevicesId;
    /**
    * 设备编码
    */
    @ApiModelProperty(value = "设备编码")
    private String productDevicesCode;
    /**
    * 零件对象
    */
    @ApiModelProperty(value = "零件对象")
    private Integer productId;
    /**
    * 零件代码
    */
    @ApiModelProperty(value = "零件代码")
    private String productCode;
    /**
    * 零件名称
    */
    @ApiModelProperty(value = "零件名称")
    private String productName;
    /**
    * 模具对象
    */
    @ApiModelProperty(value = "模具对象")
    private Integer mouldId;
    /**
    * 模具编码
    */
    @ApiModelProperty(value = "模具编码")
    private String mouldCode;
    /**
    * 模具名称
    */
    @ApiModelProperty(value = "模具名称")
    private String mouldName;
    /**
    * 工艺卡模型
    */
    @ApiModelProperty(value = "工艺卡模型")
    private Integer craftModelId;
    /**
    * 工艺卡对象
    */
    @ApiModelProperty(value = "工艺卡对象")
    private Integer craftCardId;
    /**
    * 工艺卡名称
    */
    @ApiModelProperty(value = "工艺卡名称")
    private String craftCardName;
    /**
    * 工艺卡参数
    */
    @ApiModelProperty(value = "工艺卡参数")
    private String craftCardParams;
    /**
    * 上模人员
    */
    @ApiModelProperty(value = "上模人员")
    private String mouldUpUser;
    /**
    * 上模时间
    */
    @ApiModelProperty(value = "上模时间")
    private String mouldUpTime;
    /**
    * 领料人员
    */
    @ApiModelProperty(value = "领料人员")
    private String pickUser;
    /**
    * 领料时间
    */
    @ApiModelProperty(value = "领料时间")
    private String pickTime;
    /**
    * 投料人员
    */
    @ApiModelProperty(value = "投料人员")
    private String feedUser;
    /**
    * 投料时间
    */
    @ApiModelProperty(value = "投料时间")
    private String feedTime;
    /**
    * 首件调试人员
    */
    @ApiModelProperty(value = "首件调试人员")
    private String firstDebugUser;
    /**
    * 首件调试时间
    */
    @ApiModelProperty(value = "首件调试时间")
    private String firstDebugTime;
    /**
    * 首检人员
    */
    @ApiModelProperty(value = "首检人员")
    private String firstCheckUser;
    /**
    * 首检时间
    */
    @ApiModelProperty(value = "首检时间")
    private String firstCheckTime;
    /**
    * 开始生产人员
    */
    @ApiModelProperty(value = "开始生产人员")
    private String productStartUser;
    /**
    * 开始生产时间
    */
    @ApiModelProperty(value = "开始生产时间")
    private String productStartTime;
    /**
    * 完成生产人员
    */
    @ApiModelProperty(value = "完成生产人员")
    private String productEndUser;
    /**
    * 完成生产时间
    */
    @ApiModelProperty(value = "完成生产时间")
    private String productEndTime;
    /**
    * 尾检人员
    */
    @ApiModelProperty(value = "尾检人员")
    private String lastCheckUser;
    /**
    * 尾检时间
    */
    @ApiModelProperty(value = "尾检时间")
    private String lastCheckTime;
    /**
    * 入库人员
    */
    @ApiModelProperty(value = "入库人员")
    private String storeUser;
    /**
    * 入库时间
    */
    @ApiModelProperty(value = "入库时间")
    private String storeTime;

    /**
     * 生产状态 0调试 1开始生产 2完成生产
     */
    @ApiModelProperty(value = "生产状态 0调试 1开始生产 2完成生产")
    private Integer status;

    /**
     * 计划生产周期
     */
    @ApiModelProperty(value = "计划生产周期")
    private String planProductCycle;

    /**
     * 实际生产周期
     */
    @ApiModelProperty(value = "实际生产周期")
    private String actuaProductCycle;

    /**
     * 开始调试人员
     */
    @ApiModelProperty(value = "开始调试人员")
    private String debugStartUser;

    /**
     * 开始调试时间
     */
    @ApiModelProperty(value = "开始调试时间")
    private String debugStartTime;

    @ApiModelProperty(value = "计划生产数量",hidden = true)
    private String planCount;
    @ApiModelProperty(value = "生产总数",hidden = true)
    private String qualified;
    @ApiModelProperty(value = "不合格数量",hidden = true)
    private String unqualified;
}