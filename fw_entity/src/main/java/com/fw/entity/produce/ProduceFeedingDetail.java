package com.fw.entity.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 投料明细
 *
 * @author xkliu
 * @since 2020-12-14
 */
@Data
@ApiModel(value = "produceFeedingDetail", description = "投料明细")
public class ProduceFeedingDetail {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 投料对象
     */
    @ApiModelProperty(value = "投料对象", hidden = true)
    private Integer feedingId;

    /**
     * 物料库存
     */
    @ApiModelProperty(value = "物料库存")
    private Integer storageDetailId;

    /**
     * 物料数量
     */
    @ApiModelProperty(value = "物料数量")
    private Integer storageCount;

    /**
     * 物料编号
     */
    @ApiModelProperty(value = "物料编号", hidden = true)
    private String code;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称", hidden = true)
    private String name;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位", hidden = true)
    private String unit;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
    /**
     * 制单人
     */
    @ApiModelProperty(value = "制单人", hidden = true)
    private Integer createUser;

    /**
     * 制单人名字
     */
    @ApiModelProperty(value = "制单人名字", hidden = true)
    private String createUserName;
}
