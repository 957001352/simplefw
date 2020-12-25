package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.entity.logistics.LogisticsTakeOrderDetail;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.entity.quality.QualityMaterialCheck;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsTakeOrderDao;
import com.fw.service.logistics.service.LogisticsTakeOrderService;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.quality.dao.QualityMaterialCheckDao;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:收货业务逻辑层
 * @author: wqiang
 * @create: 2020-11-12 17:26
 **/
@Repository
@Transactional
public class LogisticsTakeOrderServiceImpl implements LogisticsTakeOrderService {

    @Autowired
    private LogisticsTakeOrderDao logisticsTakeOrderDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private QualityMaterialCheckDao qualityMaterialCheckDao;
    @Autowired
    private ProduceMaterialMonitorDao produceMaterialMonitorDao;



    @Override
    public Result update(LogisticsTakeOrder logisticsTakeOrder) {

        //物料过程监控集合
        List<ProduceMaterialMonitor> produceMaterialMonitorList = new ArrayList<>();

        int flag = 0;
        if (logisticsTakeOrder != null) {
            logisticsTakeOrder.setExecuteUser(authUserUtil.userId());
            logisticsTakeOrder.setStatus(1); //1已接收
            flag = logisticsTakeOrderDao.updateTakeOrder(logisticsTakeOrder);
            if (flag > 0) {
                if (!CollectionUtils.isEmpty(logisticsTakeOrder.getTakeOrderDetailList())) {
                    for (LogisticsTakeOrderDetail detail : logisticsTakeOrder.getTakeOrderDetailList()) {
                        //物料过程监控对象
                        ProduceMaterialMonitor produceMaterialMonitor = new ProduceMaterialMonitor();
                        //收货后，明细生成泛沃批次号 批次号规则为日期+流水号，例如：20201221001
                        detail.setBatchNo(logisticsTakeOrderDao.createFwBatchNo(DateUtils.getCurrentTime("yyyyMMdd")));
                        //更新明细
                        Integer updateCount = logisticsTakeOrderDao.updateTakeOrderDetail(detail);
                        if (updateCount  > 0 ) {
                            //记录物料生产监控对象的id,就是收货明细的id(uuid)
                            produceMaterialMonitor.setId(detail.getId());
                            produceMaterialMonitor.setProductId(detail.getProductId());
                            produceMaterialMonitor.setProductCode(detail.getProductCode());
                            produceMaterialMonitor.setProductName(detail.getProductName());
                            produceMaterialMonitor.setTakeUser(headerUtil.loginUser().getName());
                            produceMaterialMonitor.setTakeTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                            produceMaterialMonitorList.add(produceMaterialMonitor);
                        }
                    }
                    //批量插入物料过程监控集合
                    if(produceMaterialMonitorList != null && produceMaterialMonitorList.size() > 0 ){
                        produceMaterialMonitorDao.insert(produceMaterialMonitorList);
                    }
                }
                LogisticsTakeOrder TakeOrder = logisticsTakeOrderDao.findById(logisticsTakeOrder.getId());
                //生成来料检验任务
                QualityMaterialCheck qualityMaterialCheck = new QualityMaterialCheck();
                qualityMaterialCheck.setTakeOrderId(TakeOrder.getId());
                qualityMaterialCheck.setCheckNo(qualityMaterialCheckDao.findCode(CodeEnum.QUALITY_04.getCode()));
                qualityMaterialCheck.setTakeDate(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                qualityMaterialCheck.setBuyDate(TakeOrder.getTakeDate());
                qualityMaterialCheck.setSourceNo(TakeOrder.getOrderNo());
                qualityMaterialCheckDao.save(qualityMaterialCheck);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String taskNo, Integer status, Integer kind, String startTime, String stopTime, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsTakeOrder> lists = logisticsTakeOrderDao.findList(taskNo, status, kind, startTime, stopTime);
        PageInfo<LogisticsTakeOrder> logisticsTakeOrderPage = new PageInfo<>(lists);
        return ResultUtils.success(logisticsTakeOrderPage);
    }
}
