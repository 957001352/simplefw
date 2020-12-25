package com.fw.entity.quality;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 来料检验管理
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-25
 */
@Data
public class QualityMaterialCheck implements Serializable {

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
     * 源单单号
     */
    private String sourceNo;

    /**
     * 收货单对象
     */
    private Integer takeOrderId;

    /**
     * 采购日期
     */
    private String buyDate;

    /**
     * 收货日期
     */
    private String takeDate;

    /**
     * 报验时间
     */
    private String checkDate;

    /**
     * 0待执行 1已执行
     */
    private Integer status;

    /**
     * 执行人
     */
    private Integer executeUser;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     * 用户ID
     */
    private Integer createUser;


    /**
     * 用户名称
     */
    private String createUserName;


}
