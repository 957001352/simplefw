package com.fw.entity.audit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 审核项目结论实体
 * @author: wqiang
 * @create: 2020-10-29 10:13
 **/
@Data
@ApiModel(value = "AuditItemDetail", description = "审核项目结论实体")
public class AuditItemDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "审核任务ID")
    private Integer auditTaskId;

    @ApiModelProperty(value = "审核项目ID")
    private Integer auditItemId;

    @ApiModelProperty(value = "0不合格 1合格")
    private Integer auditItemResult;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "审核项目Name")
    private String auditItemName;


    @ApiModelProperty(value = "图片证据")
    private String picture;


}
