package com.fw.entity.quality;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 入库检验管理
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-26
 */
@Data
public class QualityStoreCheck implements Serializable {

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
     * 设备id
     */
    private Integer productDevicesId;

    /**
     * 设备编号
     */
    private String productDevicesCode;

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
     * 物料对象
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;


    /**
     * 检验结果分类：0-1 首件外观 0-2首件尺寸 1-1末件外观 1-2 末检尺寸 2-1巡检外观 2-2巡检尺寸  3来料检验 4-0入库外观 4-1入库尺寸 5出库检验
     */
    private String checkClassify;

    /**
     * 检验时间
     */
    private String checkTime;

    /**
     * 0待执行 1已执行
     */
    private Integer status;

    /**
     * 用户对象
     */
    private Integer createUser;


    /**
     * 报检时间
     */
    private String createTime;


    /**
     * 用户名称
     */
    private String createUserName;

    /**
     * 数量
     */
    private Integer count;


}
