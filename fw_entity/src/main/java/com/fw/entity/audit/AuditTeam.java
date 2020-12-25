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
@ApiModel(value = "AuditTeam", description = "分层审核表单实体")
public class AuditTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "审核表单名称")
    private String name;

    @ApiModelProperty(value = "审核项目ids")
    private String itemIds;

    @ApiModelProperty(value = "审核项目ids")
    private String itemNames;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "是否删除 默认0 删除1")
    private Integer deleted;
}
