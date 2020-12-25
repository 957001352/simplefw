package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 物流库位管理
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@Data
@ApiModel(value = "logisticsStorageLocation", description = "物流库位管理")
public class LogisticsStorageLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id")
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
    @ApiModelProperty(value = "仓库对象ID")
    private Integer storageHouseId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String mouldCode;


    /**
     * 模具名称
     */
    @ApiModelProperty(value = "状态")
    private String mouldName;


    /**
     * 绑定状态
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private String isBand;


    /**
     * 关联模具
     */
    @ApiModelProperty(value = "关联模具", hidden = true)
    private String groupName;

    /**
     * 全名
     */
    @ApiModelProperty(value = "全名", hidden = true)
    private String allName;

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码", hidden = true)
    private String code;

}
