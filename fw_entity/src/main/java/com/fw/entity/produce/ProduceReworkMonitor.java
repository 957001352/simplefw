package com.fw.entity.produce;

import com.fw.entity.quality.QualityStoreCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 二次加工过程监控
 *
 * @author xkliu
 * @since 2020-12-15
 */
@Data
@ApiModel(value = "produceReworkMonitor", description = "二次加工过程监控")
public class ProduceReworkMonitor {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 二次加工排产计划
     */
    @ApiModelProperty(value = "二次加工排产计划")
    private Integer planMoldingId;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productOrder;

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
     * 开始生产人员
     */
    @ApiModelProperty(value = "开始生产人员", hidden = true)
    private String productStartUser;

    /**
     * 开始生产时间
     */
    @ApiModelProperty(value = "开始生产时间", hidden = true)
    private String productStartTime;

    /**
     * 完成生产人员
     */
    @ApiModelProperty(value = "完成生产人员", hidden = true)
    private String productEndUser;

    /**
     * 完成生产时间
     */
    @ApiModelProperty(value = "二次加工排产计划", hidden = true)
    private String productEndTime;

    /**
     * 入库检验人员
     */
    @ApiModelProperty(value = "入库检验人员", hidden = true)
    private String checkUser;

    /**
     * 入库检验时间
     */
    @ApiModelProperty(value = "入库检验时间", hidden = true)
    private String checkTime;

    /**
     * 入库人员
     */
    @ApiModelProperty(value = "入库人员", hidden = true)
    private String storeUser;

    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间", hidden = true)
    private String storeTime;

    /**
     * 入库检验数据集合
     */
    @ApiModelProperty(value = "入库检验数据集合")
    List<QualityStoreCheck> qualityStoreChecks;


    /**
     * 计划添加人员
     */
    @ApiModelProperty(value = "计划添加人员", hidden = true)
    private Integer createUser;

    /**
     * 计划添加人员
     */
    @ApiModelProperty(value = "计划添加人员姓名", hidden = true)
    private String createUserName;

    /**
     * 计划添加时间
     */
    @ApiModelProperty(value = "计划添加时间", hidden = true)
    private String createTime;

    @ApiModelProperty(value = "计划生产总数", hidden = true)
    private String planCount;
    /**
     * 生产总数
     */
    @ApiModelProperty(value = "生产总数", hidden = true)
    private String qualified;

    /**
     * 泛沃批次号 二次加工领料单中泛沃批次号，既是注塑加工中的生产指令
     */
    @ApiModelProperty(value = "泛沃批次号", hidden = true)
    private String fwBatch;

    /**
     * 报工记录
     */
    @ApiModelProperty(value = "报工记录")
    List<ProduceReworkRecord>  ProduceReworkRecords;


}
