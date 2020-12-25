package com.fw.entity.produce;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

/**
 * <p>
 * 换班交接
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-08
 */
@ApiModel(value = "produceShift", description = "换班交接")
@Data
public class ProduceShift implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;


    /**
     * 交接人
     */
    @ApiModelProperty(value = "交接人")
    private Integer operateUser;

    @ApiModelProperty(value = "交接人名称")
    private String operateUserName;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

    /**
     * 指派时间
     */
    @ApiModelProperty(value = "交接时间")
    private String createTime;

    /**
     * 交接事项
     */
    @ApiModelProperty(value = "交接事项")
    private String matters;

    /**
     * 注塑计划ID
     */
    @ApiModelProperty(value = "注塑计划ID")
    private Integer planMoldingId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;


}
