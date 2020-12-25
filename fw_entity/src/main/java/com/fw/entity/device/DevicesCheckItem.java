package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "devicesCheckItem", description = "点检项目")
public class DevicesCheckItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 保养项目名称
     */
    @ApiModelProperty(value = "保养项目名称")
    private String name;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String devicesClassify;

    /**
     * 保养内容描述
     */
    @ApiModelProperty(value = "保养内容描述")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 设备类型名称
     */
    @ApiModelProperty(hidden = true,value = "设备类型名称")
    private String classifyName;
    /**
     * 删除状态
     */
    @ApiModelProperty(hidden = true,value = "删除状态")
    private Integer deleted;

}
