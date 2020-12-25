package com.fw.entity.craft;

import com.fw.domain.BaseFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 注塑成型工艺卡
 * @Author qwang
 * @Date 2020/11/5
 */
@Data
@ApiModel(value = "CraftModel", description = "注塑工艺模型")
public class CraftModel implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "文档编码")
    private String documentCode;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "模具ID")
    private Integer mouldId;

    @ApiModelProperty(value = "模具编码")
    private String mouldCode;

    @ApiModelProperty(value = "生产节拍")
    private Integer beat;

    @ApiModelProperty(value = "生产类型 0全自动 1半自动 2手动")
    private Integer productType;

    @ApiModelProperty(value = "BOM编码")
    private String bomNo;

    @ApiModelProperty(value = "模具类型")
    private Integer modelType;

    @ApiModelProperty(value = "物料库房备料")
    private Integer materialPrepare;

    @ApiModelProperty(value = "领料")
    private Integer pickingTime;

    @ApiModelProperty(value = "拌料")
    private Integer mixingTime;

    @ApiModelProperty(value = "投料")
    private Integer feedTime;

    @ApiModelProperty(value = "烘料")
    private Integer dryTime;

    @ApiModelProperty(value = "含水量监测")
    private Integer waterMonitor;

    @ApiModelProperty(value = "升温")
    private Integer heatUp;

    @ApiModelProperty(value = "首件调试")
    private Integer firstDebug;

    @ApiModelProperty(value = "首件检验")
    private Integer firstCheck;

    @ApiModelProperty(value = "生产库房备料")
    private Integer productPrepare;

    @ApiModelProperty(value = "出库")
    private Integer modelOut;

    @ApiModelProperty(value = "产前保养")
    private Integer prenatalCare;

    @ApiModelProperty(value = "调运至待使用区")
    private Integer modelDebug;

    @ApiModelProperty(value = "上模")
    private Integer modelUp;

    @ApiModelProperty(value = "下模")
    private Integer modelDown;

    @ApiModelProperty(value = "作业指导书Ids")
    private String fileIds;

    @ApiModelProperty(value = "检验规范Ids")
    private String inspectionSpecIds;

    @ApiModelProperty(value = "不合格封样指导书Ids")
    private String noneSampleIds;


    @ApiModelProperty(value = "照片地址")
    private String photo;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新人")
    private Integer updateUser;


    @ApiModelProperty(value = "创建时间")
    private String updateTime;

    @ApiModelProperty(value = "注塑设备列表")
    private List<CraftModelProcess> caftModelProcesscList;

    @ApiModelProperty(value = "作业指导书")
    private List<BaseFile> fileList;

    @ApiModelProperty(value = "检验规范附件")
    private List<BaseFile> InspectionSpecList;

    @ApiModelProperty(value = "不合格封样指导书")
    private List<BaseFile> noneSampleList;

    @ApiModelProperty(value = "工艺卡id",hidden = true)
    private Integer cardId;

    @ApiModelProperty(value = "工艺卡code",hidden = true)
    private String cardCode;

    @ApiModelProperty(value = "工艺卡图片",hidden = true)
    private String picture;


}