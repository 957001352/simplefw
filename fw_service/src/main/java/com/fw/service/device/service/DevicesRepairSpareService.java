package com.fw.service.device.service;

import com.fw.domain.Result;

public interface DevicesRepairSpareService {

    Result findSpareStoreAndUse(String code);
}
