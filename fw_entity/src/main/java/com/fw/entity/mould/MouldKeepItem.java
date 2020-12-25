package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模具保养项目
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Data
@ApiModel(value = "mouldKeepItem", description = "模具保养项目")
public class MouldKeepItem implements Serializable {

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
     * 删除状态 0正常 1删除
     */
    @ApiModelProperty(value = "删除状态", hidden = true)
    private Integer deleted;

    /**
     * 保养周期 0日保养，1周保养，2月保养，3季度保养，4年保养
     */
    @ApiModelProperty(value = "保养周期")
    private Integer cycle;

    /**
     * 指导书附件
     */
    @ApiModelProperty(value = "指导书附件")
    private String instructor;

    /**
     * 附件路径
     */
    @ApiModelProperty(value = "附件路径", hidden = true)
    private String webPath;

    /**
     * 附件名字
     */
    @ApiModelProperty(value = "附件名字", hidden = true)
    private String fileName;

    /**
     * 附件后缀
     */
    @ApiModelProperty(value = "附件后缀", hidden = true)
    private String suffix;

}
