package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 模具库存管理 入库、出库、移库
 * @author lpsong
 * @since 2020-10-26
 */
@Data
@ApiModel(value = "mouldHouse", description = "模具库存管理")
public class MouldHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 库位对象
     */
    @ApiModelProperty(value = "库位对象")
    private Integer nowLocationId;

    @ApiModelProperty(value = "库位对象")
    private String nowLocation;
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
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String houseNo;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", hidden = true)
    private Integer createUser;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", hidden = true)
    private String createTime;
    /**
     * 操作类型  0 入库 1出库 2移库
     */
    @ApiModelProperty(value = "操作类型")
    private Integer operate;

    /**
     * 旧库位对象 移库填入旧库位对象，出入库为空
     */
    @ApiModelProperty(value = "旧库位对象")
    private Integer oldLocationId;

    @ApiModelProperty(value = "旧库位对象", hidden = true)
    private String oldLocation;


    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    private String executeTime;
    /**
     * 执行人
     */
    @ApiModelProperty(value = "执行人")
    private Integer executeUser;


    @ApiModelProperty(value = "操作人", hidden = true)
    private String createUserName;


    /**
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态")
    private Integer status;

    @ApiModelProperty(value = "图片路径", hidden = true)
    private String webPath;
    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令", hidden = true)
    private Integer injectionMoldingId;
}
