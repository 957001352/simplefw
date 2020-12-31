package com.fw.entity.produce;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 生产报工记录
 * @author lpsong
 * @since 2020-12-15
 */
@Data
@ApiModel(value = "生产报工记录")
public class ProduceReworkRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 二次加工计划
     */
    @ApiModelProperty(value = "二次加工计划")
    private Integer planReworkId;
    /**
     * 本箱合格数量
     */
    private Integer qualified;
    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令",hidden = true)
    private String productOrder;
    /**
     * 零件编码
     */
    @ApiModelProperty(value = "零件编码",hidden = true)
    private String productCode;
    /**
     * 零件名称
     */
    @ApiModelProperty(value = "零件名称",hidden = true)
    private String productName;
    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人",hidden = true)
    private Integer createUser;
    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人名称",hidden = true)
    private String createUserName;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间",hidden = true)
    private String createTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态",hidden = true)
    private Integer status;

    /**
     * 入库状态
     */
    @ApiModelProperty(value = "入库状态")
    private Integer bankStatus;








}
