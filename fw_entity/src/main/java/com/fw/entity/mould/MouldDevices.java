package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 模具
 * @Author: gchen
 * @CreateDate: 2020/10/26 10:29
 */
@Data
@ApiModel(value = "mouldDevices", description = "模具实体")
public class MouldDevices implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "模具编号")
    private String code;                               // 模具编号
    @ApiModelProperty(value = "模具名称")
    private String name;                                // 模具名称
    @ApiModelProperty(value = "模具寿命")
    private String lifeTime;                             // 模具寿命
    @ApiModelProperty(value = "使用状态 0-正常，1-使用中，2-EOP  8-报废")
    private Integer status;                                // 使用状态 0-正常，1-使用中，2-EOP 8-报废
    @ApiModelProperty(value = "添加时间")
    private String createTime;                              // 添加时间
    @ApiModelProperty(value = "入库时间")
    private String useTime;                                // 入库时间
    @ApiModelProperty(value = "当前库位对象")
    private Integer nowLocationId;                          // 当前库位对象
    @ApiModelProperty(value = "阶段状态 0-试制阶段 1-量产阶段 2-项目EOP 3-报废")
    private Integer turnStatus;                                // 阶段状态 0-试制阶段 1-量产阶段 2-项目EOP 3-报废
    @ApiModelProperty(value = "成型周期")
    private String molding;                              // 成型周期
    @ApiModelProperty(value = "型腔数")
    private String cavity;                                 // 型腔数
    @ApiModelProperty(value = "物料长代码")
    private String material;                              // 物料长代码
    @ApiModelProperty(value = "初始开始模次数")
    private Integer initCount;                               // 初始开始模次数
    @ApiModelProperty(value = "关联模具id")
    private String parentId;                                 // 关联模具id
    @ApiModelProperty(value = "旧库位对象")
    private Integer oldLocationId;                      // 旧库位对象 默认为空，解绑后将当前库位对象写入旧库位，并清空当前库位对象，绑定后清空旧库位对象

    @ApiModelProperty(value = "重量")
    private Integer weight;                                 // 重量
    @ApiModelProperty(value = "尺寸(长*宽*高)")
    private String sizes;                                 // 尺寸(长*宽*高)
    @ApiModelProperty(value = "模具厂家")
    private String vender;                                 // 模具厂家
    @ApiModelProperty(value = "客户名称")
    private String clientName;                                 // 客户名称
    @ApiModelProperty(value = "产品")
    private String product;                                 // 产品
    @ApiModelProperty(value = "照片")
    private String dataId;                                 // 照片

    @ApiModelProperty(value = "开合模次数")
    private Integer process;                                 // 开合模次数
    @ApiModelProperty(value = "图片路径")
    private String webPath;                                 // 图片路径

    @ApiModelProperty(value = "该模具是否在进行转段审核")
    private Integer auditStatus;                                 // 该模具是否在进行转段审核 0-没有转段申请 1-转段申请正在审核中

    @ApiModelProperty(value = "模具类型 0主体 1镶体 2嵌体")
    private Integer type;

    @ApiModelProperty(value = "自定义属性")
    private String extendAttr;

    @ApiModelProperty(value = "累计开合模次数")
    private Integer totalCount;

}
