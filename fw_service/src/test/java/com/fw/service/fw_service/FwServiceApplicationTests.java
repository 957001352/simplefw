package com.fw.service.fw_service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.service.logistics.dao.LogisticsTakeOrderDao;
import com.fw.service.logistics.service.LogisticsStoreHouseService;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.produce.service.ProduceMoldingRecordService;
import com.fw.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FwServiceApplicationTests {

    @Autowired
    LogisticsStoreHouseService logisticsStoreHouseService;
    @Autowired
    ProduceMaterialMonitorDao produceMaterialMonitorDao;


    @Autowired
    LogisticsTakeOrderDao logisticsTakeOrderDao;
    @Test
    public void getBacthNo(){
        String fwBatchNo = logisticsTakeOrderDao.createFwBatchNo(DateUtils.getCurrentTime("yyyyMMdd"));
        System.out.println("--------------------->"+fwBatchNo);
    }

    @Autowired
    ProduceMoldingRecordService produceMoldingRecordService;

    @Test
    public void saveRedis(){
        //produceMoldingRecordService.callMaterialOrCastMaterial("code456",2,"碎石颗粒");
        produceMoldingRecordService.findCallAndCastList();
    }


}
