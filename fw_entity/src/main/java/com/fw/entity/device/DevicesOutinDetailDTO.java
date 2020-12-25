package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description:
 */

@Data
@ApiModel(value = "DevicesOutinDetailDTO")
public class DevicesOutinDetailDTO {

    @ApiModelProperty(value = "出入库明细")
    List<DevicesOutinDetail> devicesOutinDetails;

}
