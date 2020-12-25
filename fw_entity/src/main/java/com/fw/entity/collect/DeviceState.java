package com.fw.entity.collect;

import lombok.Data;

@Data
public class DeviceState {
    private Integer id;
    private String deviceId;
    private Integer tmOnlineState;
    private Integer isExistLog;

    public DeviceState() {
    }

    public DeviceState(String deviceId, Integer tmOnlineState) {
        this.deviceId = deviceId;
        this.tmOnlineState = tmOnlineState;
    }
}
