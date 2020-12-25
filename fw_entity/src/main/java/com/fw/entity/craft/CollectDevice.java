package com.fw.entity.craft;

import lombok.Data;

@Data
public class CollectDevice {
    private Integer id;
    private String deviceId;
    private String paramKey;
    private String paramValue;
    private String createTime;
}
