package com.fw.entity.quality;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 首末检验管理
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Data

public class QualityFirstendCheck implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 检验订单号
     */
    private String checkNo;

    /**
     * 设备ID
     */
    private Integer productDevicesId;

    /**
     * 设备编号名称
     */
    private String productDevicesName;

    /**
     * 生产指令
     */
    private String productCode;


    /**
     * 模具编码
     */
    private String mouldNo;

    /**
     * 物料对象
     */
    private Integer productId;

    /**
     * 物料名称
     */
    private String materialName;


    /**
     * 物料代码
     */
    private String materialCode;


    /**
     * 检验结果分类：0-1 首件外观 0-2首件尺寸 1-1末件外观 1-2 末检尺寸 2-1巡检外观 2-2巡检尺寸  3来料检验 4-0入库外观 4-1入库尺寸 5出库检验
     */
    private String  checkClassify;


    /**
     * 审核结果
     */
    private Integer auditResult;

    /**
     * 0 首件 1巡检 2末件
     */
    private Integer checkType;

    /**
     * 未执行原因
     */
    private String nonExecution;

    /**
     * 未执行原因描述
     */
    private String nonExecutCause;

    /**
     * 延期天数 默认0
     */
    private Integer carryDay;

    /**
     * 是否超时 0 未超时 1已超时 
     */
    private Integer isOut;

    /**
     * 0待执行 1申请延后 2 强制关闭 3 执行完成
     */
    private Integer status;

    /**
     * 检验时间
     */
    private String checkTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 用户ID
     */
    private Integer createUser;


    /**
     * 用户名称
     */
    private String createUserName;

    /**
     * 测量结束日期
     */
    private String endTime;
/*

    *//**
     * 检验结果对象
     *//*
    private QualityInspectResult qualityInspectResult;*/


}
