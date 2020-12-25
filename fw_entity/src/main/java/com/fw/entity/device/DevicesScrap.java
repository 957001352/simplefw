package com.fw.entity.device;

import com.fw.domain.BaseFile;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 设备报废记录实体类
 * @Author: gchen
 * @CreateDate: 2020/10/23 11:18
 */
@Data
@Api(value = "devicesScrap", description = "设备报废记录")
public class DevicesScrap implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "设备履历")
    private Integer devicesExtensionId;      //'设备履历'
    @ApiModelProperty(value = "申请人")
    private Integer creatUser;                //'申请人'
    @ApiModelProperty(value = "申请时间")
    private String createTime;                // '申请时间'
    @ApiModelProperty(value = "报废原因")
    private String reason;                      // '报废原因'
    @ApiModelProperty(value = "附件id")
    private String dataId;                      // '附件文件id'
    @ApiModelProperty(value = "设备id")
    private String productDevicesId;
    @ApiModelProperty(value = "报废设备信息")
    private DevicesItemVo devicesItemVo;
    @ApiModelProperty(value = "审批结果")
    private String auditStatus;
    @ApiModelProperty(value = "流程id")
    private Integer executionId;
    @ApiModelProperty(value = "申请人信息")
    private User applyUser;
    @ApiModelProperty(value = "附件")
    private List<BaseFile> baseFiles;
}
