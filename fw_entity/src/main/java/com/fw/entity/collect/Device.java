package com.fw.entity.collect;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 收集设备
 *
 * @author xkliu
 * @since 2020-12-16
 */
@Data
@ApiModel(value = "device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String deviceId;

    private String paramKey;

    private String paramValue;

    private String createTime;


}
