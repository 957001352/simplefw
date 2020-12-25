package com.fw.entity.e2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author gchen
 * @Date 2020/3/26
 */
@Data
@ApiModel(value="devicesClassify",description="类型对象")
public class DevicesClassify {
    @ApiModelProperty(value="新增为空/修改传值")
    private String id;
    @ApiModelProperty(value="分类名称",required=true)
    private String classifyName;//分类名称
    @ApiModelProperty(value="分类描述",required=true)
    private String describe;//描述
    @ApiModelProperty(value="类型ID",required=true)
    private String classifyId;//类型
    @ApiModelProperty(value="图片路径",required=true)
    private String imagePath;//图片路径
    @ApiModelProperty(hidden = true)
    private Integer tenantId;//租户

}