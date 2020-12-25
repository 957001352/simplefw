package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 模具停用实体
 * @Author: gchen
 * @CreateDate: 2020/10/26 15:00
 */
@Data
@ApiModel(value = "mouldStop", description = "模具停用实体")
public class MouldStop implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "模具履历")
    private String mouldDevicesId;                               // 模具履历
    @ApiModelProperty(value = "申请人")
    private Integer creatUser;                                // 申请人
    @ApiModelProperty(value = "申请时间")
    private String createTime;                             // 申请时间
    @ApiModelProperty(value = "停用原因")
    private String reason;                                // 停用原因
    @ApiModelProperty(value = "附件")
    private String dataId;                              // 附件
}
