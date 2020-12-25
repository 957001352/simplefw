package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模具仓库管理
 * @author lpsong
 * @since 2020-10-26
 */
@Data
@ApiModel(value = "mouldStorageHouse", description = "模具仓库管理")
public class MouldStorageHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;


    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String name;


    /**
     * 状态 0 正常 1删除
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;


    @ApiModelProperty(value = "库位列表", hidden = true)
    private  List<MouldStorageLocation> localtionList;

}
