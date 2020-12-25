package com.fw.entity.mould;

import com.fw.domain.BaseFile;
import com.fw.entity.e2c.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 模具转段实体
 * @Author: gchen
 * @CreateDate: 2020/10/26 15:00
 */
@Data
@ApiModel(value = "mouldTurn", description = "模具转段实体")
public class MouldTurn implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "模具履历")
    private Integer mouldDevicesId;                               // 模具履历
    @ApiModelProperty(value = "申请人")
    private Integer creatUser;                                // 申请人
    @ApiModelProperty(value = "申请时间")
    private String createTime;                             // 申请时间
    @ApiModelProperty(value = "转段原因")
    private String reason;                                // 转段原因
    @ApiModelProperty(value = "附件")
    private String dataId;                              // 附件
    @ApiModelProperty(value = "阶段")
    private Integer stage;                              // 阶段
    @ApiModelProperty(value = "转段前状态")
    private Integer preStatus;                              // 转段前状态

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;                              // 审核状态
    @ApiModelProperty(value = "审核id")
    private Integer executionId;                              // 审核id
    @ApiModelProperty(value = "模具编号")
    private String code;                              // 模具编号
    @ApiModelProperty(value = "模具名称")
    private String name;                              // 模具名称
    @ApiModelProperty(value = "申请用户信息")
    private User user;                              // 申请用户信息
    @ApiModelProperty(value = "附件")
    private List<BaseFile> baseFiles;


}
