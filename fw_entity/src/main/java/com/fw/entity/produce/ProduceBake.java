package com.fw.entity.produce;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

/**
 * <p>
 * 烘烤时长
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-08
 */
@Data
@ApiModel(value = "烘烤时长")
public class ProduceBake implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 增加时长
     */
    @ApiModelProperty(value = "增加时长")
    private Integer addTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createUser;


    @ApiModelProperty(value = "创建人名称")
    private Integer createUserName;


    /**
     * 指派时间
     */
    @ApiModelProperty(value = "指派时间")
    private String  createTime;

    /**
     * 注塑排产计划
     */
    @ApiModelProperty(value = "注塑排产计划")
    private Integer planMoldingId;

    @ApiModelProperty(value = "生产指令")
    private String productCode;

    @ApiModelProperty(value = "设备编码")
    private String deviceCode;


    @ApiModelProperty(value = "产品名称")
    private String productName;




}
