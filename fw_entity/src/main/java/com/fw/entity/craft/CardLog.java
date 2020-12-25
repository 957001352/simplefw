package com.fw.entity.craft;

import com.fw.entity.e2c.User;
import com.fw.entity.jbpm.JbpmHistTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 工艺卡参数变更日志
 * @Author gchen
 * @Date 2020/11/6
 */
@Data
@ApiModel(value = "cardLog", description = "工艺卡参数变更日志")
public class CardLog implements Serializable {
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 工艺卡
     */
    @ApiModelProperty(value = "变更人员")
    private Integer createUser;
    /**
     * 变更时间
     */
    @ApiModelProperty(value = "变更时间")
    private String createTime;
    /**
     * 变更类型
     */
    @ApiModelProperty(value = "变更类型")
    private Integer updateType;
    /**
     * 变更原因
     */
    @ApiModelProperty(value = "变更原因")
    private String reason;
    /**
     * 变更内容
     */
    @ApiModelProperty(value = "变更内容")
    private String content;

    /**
     * 工艺参数
     */
    @ApiModelProperty(value = "工艺卡参数")
    private Integer paramsId;

    /**
     * 变更人
     */
    @ApiModelProperty(value = "变更人")
    private User user;

    /**
     * 审核流程id
     */
    @ApiModelProperty(value = "审核流程id")
    private Integer executionId;

    /**
     * 审核流程集合
     */
    @ApiModelProperty(value = "审核流程集合")
    private List<JbpmHistTask> execution;

    /**
     * 变更前数据
     */
    @ApiModelProperty(value = "变更前数据")
    private String craftPreData;

    /**
     * 变更后数据
     */
    @ApiModelProperty(value = "变更后数据")
    private String craftAfterData;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "零件编码")
    private String partCode;
    @ApiModelProperty(value = "零件名称")
    private String partName;
    @ApiModelProperty(value = "文档编号")
    private String documentNo;
    @ApiModelProperty(value = "设备编号")
    private Integer productId;
    @ApiModelProperty(value = "工艺编号")
    private String code;

    @ApiModelProperty(value = "工艺卡id")
    private Integer cardId;
}