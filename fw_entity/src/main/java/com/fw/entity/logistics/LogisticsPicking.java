package com.fw.entity.logistics;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@Data
@ApiModel(value = "领料")
public class LogisticsPicking implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 出库对象
     */
    @ApiModelProperty(value = "出库对象")
    private Integer outHouseId;
    /**
     * 任务单号
     */
    @ApiModelProperty(value = "任务单号",hidden = true)
    private String houseNo;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人",hidden = true)
    private Integer createUser;
    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人名称",hidden = true)
    private String createUserName;
    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间",hidden = true)
    private String createTime;
    /**
     * 出库明细
     */
    @ApiModelProperty(value = "出库明细",hidden = true)
    private List<LinkedHashMap> logisticsOutHouseDetailList;
}
