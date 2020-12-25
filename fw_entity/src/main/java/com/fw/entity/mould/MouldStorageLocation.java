package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 模具库位管理
 * @author lpsong
 * @since 2020-10-26
 */
@Data
@ApiModel(value = "mouldStorageLocation", description = "模具库位管理")
public class MouldStorageLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;


    /**
     * 库位名称
     */
    @ApiModelProperty(value = "库位名称")
    private String name;


    /**
     * 状态 0 正常 1删除
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;

    /**
     * 仓库对象
     */
    private Integer storageHouseId;

    /**
     * 模具id
     */
    @ApiModelProperty(value = "模具id", hidden = true)
    private String mouldId;
    /**
     * 模具编码
     */
    @ApiModelProperty(value = "模具编码", hidden = true)
    private String mouldCode;


    /**
     * 模具名称
     */
    @ApiModelProperty(value = "模具名称", hidden = true)
    private String mouldName;


    /**
     * 绑定状态
     */
    @ApiModelProperty(value = "绑定状态", hidden = true)
    private String isBand;


    /**
     * 关联模具
     */
    @ApiModelProperty(value = "关联模具", hidden = true)
    private String groupName;


    /**
     * 仓位名称
     */
    @ApiModelProperty(value = "仓位名称", hidden = true)
    private String storageHouseName;
    /**
     * 被绑定模具
     */
    @ApiModelProperty(value = "被绑定模具", hidden = true)
    private String parentId;


}
