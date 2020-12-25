package com.fw.entity.audit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 分层审核项目实体
 * @author: wqiang
 * @create: 2020-10-29 10:13
 **/
@Data
@ApiModel(value = "AuditItem", description = "分层审核项目实体")
public class AuditItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目类型 0人 1机器 2物料 3制度 4环境")
    private Integer kind;

    @ApiModelProperty(value = "内容描述")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "是否删除 默认0 删除1")
    private Integer deleted;
}
