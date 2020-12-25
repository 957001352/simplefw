package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 盘库
 * @author lpsong
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsCheckHouse", description = "盘库")
public class LogisticsCheckHouse {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 盘库单号
     */
    @ApiModelProperty(value = "盘库单号")
    private String houseNo;
    /**
     * 制单人
     */
    @ApiModelProperty(hidden = true,value = "制单人")
    private Integer createUser;
    /**
     * 制单时间
     */
    @ApiModelProperty(hidden = true,value = "制单时间")
    private String createTime;
    /**
     * 盘点人
     */
    @ApiModelProperty(value = "盘点人")
    private Integer checkUser;
    /**
     * 盘点人姓名
     */
    @ApiModelProperty(hidden = true,value = "盘点人姓名")
    private String checkUserName;
    /**
     * 盘点时间
     */
    @ApiModelProperty(value = "盘点时间")
    private String checkTime;
    /**
     * 执行状态 0 未执行 1已执行 2关闭
     */
    @ApiModelProperty(value = "执行状态 0 未执行 1已执行 2关闭")
    private Integer status;
    /**
     * 盘点人
     */
    @ApiModelProperty(hidden = true,value = "执行人")
    private Integer executeUser;
    /**
     * 盘点时间
     */
    @ApiModelProperty(hidden = true,value = "执行时间")
    private String executeTime;

    /**
     * 盘点库位
     */
    @ApiModelProperty(value = "盘点库位")
    private String checkHouseIds;
    /**
     * 盘点库位名称
     */
    @ApiModelProperty(hidden = true,value = "盘点库位名称")
    private String checkHouseNames;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
    /**
     * 盘库明细
     */
    @ApiModelProperty(value = "盘库明细")
    private List<LogisticsCheckHouseDetail> checkHouseDetailList;

    /**
     * 执行状态 0 未执行 1已执行
     */
    @ApiModelProperty(value = "盘点结果 0 正常 1异常")
    private Integer checkResult;
}
