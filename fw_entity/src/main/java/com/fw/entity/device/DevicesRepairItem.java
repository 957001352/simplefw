package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @program: dhlk_fw_plat
 * @description: 维修项目实体
 * @author: wqiang
 * @create: 2020-10-20 10:30
 **/
@Data
@ApiModel(value = "devicesRepairItem", description = "维修项目")
public class DevicesRepairItem implements Serializable {

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

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型Id")
    private String devicesClassify;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备类型名称")
    private String devicesName;

}
