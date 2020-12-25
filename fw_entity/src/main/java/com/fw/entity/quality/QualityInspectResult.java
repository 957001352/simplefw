package com.fw.entity.quality;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 检验结果
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Data

public class QualityInspectResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;


    /**
     * 零件代码
     */
    private String partCode;

    /**
     * 抽样
     */
    private String samping;

    /**
     * 材料
     */
    private String material;

    /**
     * 模具编号
     */
    private String mouldNo;

    /**
     * 名称
     */
    private String partName;

    /**
     * 频率
     */
    private String frequence;

    /**
     * 材料批号
     */
    private String materialNo;

    /**
     * 生产指令号
     */
    private String ofNo;

    /**
     * 测量结束时间
     */
    private String endTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建人
     */
    private Integer user;

    /**
     * 检验结果分类 0-1 首件外观 0-2首件尺寸 1-1末件外观 1-2 末检尺寸 2-1巡检外观 2-2巡检尺寸  3来料检验 4-0入库外观 4-1入库尺寸 5出库检验
     */
    private String checkClassify;

    /**
     * 真实检验结果
     */
    private String realResult;

    /**
     * 客户检验结果
     */
    private String viewResult;

    /**
     * 规范分类 0来料 1首末检/巡检 2入库 3出库
     */
    private Integer classify;

    /**
     * 检验对象
     */
    private Integer dataId;

    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 测量人
     */
    private String measureUser;


    /**
     * 检验判定结果 0合格 1不合格
     */
    private Integer result;

}
