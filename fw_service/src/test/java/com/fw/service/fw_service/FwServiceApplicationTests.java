package com.fw.service.fw_service;

import com.fw.domain.Result;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.service.craft.dao.CardLogDao;
import com.fw.service.craft.dao.CardParamsDao;
import com.fw.service.logistics.dao.LogisticsTakeOrderDao;
import com.fw.service.logistics.service.LogisticsStoreHouseService;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.plan.service.InjectionMoldingService;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.produce.service.ProduceMoldingRecordService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.utils.DateUtils;
import io.swagger.models.auth.In;
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
    CardLogDao cardLogDao;
    @Autowired
    CardParamsDao cardParamsDao;

    @Autowired
    LogisticsTakeOrderDao logisticsTakeOrderDao;

    @Autowired
    E2CServicesUtil e2CServicesUtil;

    @Autowired
    InjectionMoldingService injectionMoldingService;
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


    @Test
    public void updateCradParams() {
        //完成生产后判断该设备、零件使用的工艺卡是否是临时变更的工艺卡,如果是就变回永久变更的工艺卡
        CardParams cardParams = cardParamsDao.findByDeviceProduct(103, "1.1.01.0019");
        if (cardParams.getUpdateType() == 0) { //判断是否是临时变更
            CardLog cardLog = cardLogDao.findIngByParamsId(cardParams.getId(), 0);//查询该工艺卡最新的一次记录
            //将工艺卡参数值变回之前永久变更的数据,将变更类型变回永久变更
            cardParams.setCraftData(cardLog.getCraftPreData());
            cardParams.setUpdateType(1);
            cardParamsDao.update(cardParams);
        }
    }

    @Test
    public void collectE2c() {
        InjectionMolding injectionMolding = new InjectionMolding();
        injectionMolding.setProductDevicesId(88);
        injectionMolding.setActualEnd("2020-12-28 10:00:00");
    }
}
