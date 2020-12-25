package com.fw.entity.audit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题管理
 * @author lpsong
 * @since 2020-12-03
 */
@Data
@ApiModel(value = "auditQuestion", description = "问题管理")
public class AuditQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    public AuditQuestion(){}

    public AuditQuestion(String question, String questionDesc, Integer createUser) {
        this.question = question;
        this.questionDesc = questionDesc;
        this.createUser = createUser;
    }
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "问题描述")
    private String questionDesc;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "创建人姓名", hidden = true)
    private String createUserName;

    @ApiModelProperty(value = "责任人姓名", hidden = true)
    private String dutyUserName;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "处理前照片")
    private String createFile;

    @ApiModelProperty(value = "责任人")
    private Integer dutyUser;

    @ApiModelProperty(value = "处理人")
    private Integer dealUser;

    @ApiModelProperty(value = "完成时间")
    private String dealTime;

    @ApiModelProperty(value = "处理结论")
    private String dealResult;

    @ApiModelProperty(value = "处理后照片")
    private String dealFile;

    @ApiModelProperty(value = "状态 0未执行 1已执行")
    private Integer status;

}
