package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 维修项目实体
 * @author: wqiang
 * @create: 2020-10-20 10:30
 **/
@Data
@ApiModel(value = "mouldRepairItem", description = "模具维修项目")
public class MouldRepairItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;


    /**
     * 维修项目名称
     */
    @ApiModelProperty(value = "维修项目")
    private String name;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

}
