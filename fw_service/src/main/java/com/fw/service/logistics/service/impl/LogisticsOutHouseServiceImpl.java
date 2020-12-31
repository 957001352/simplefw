package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.*;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.service.logistics.dao.*;
import com.fw.service.logistics.service.LogisticsOutHouseService;
import com.fw.service.logistics.util.StorageProductUtil;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogisticsOutHouseServiceImpl implements LogisticsOutHouseService {
    @Resource
    private LogisticsOutHouseDao logisticsOutHouseDao;
    @Resource
    private LogisticsOutSubpackageDao logisticsOutSubpackageDao;
    @Resource
    private LogisticsOutHouseDetailDao logisticsOutHouseDetailDao;
    @Resource
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Resource
    private E2CServicesUtil e2CServicesUtil;
    @Resource
    private LogisticsDeliveryPlanDao logisticsDeliveryPlanDao;
    @Resource
    private StorageProductUtil storageProductUtil;
    @Autowired
    private ProduceMaterialMonitorDao produceMaterialMonitorDao;
    @Autowired
    private LogisticsPickingOrderDao logisticsPickingOrderDao;


    @Override
    public Result findList(String houseNo, String houseType, String status, String startTime, String endTime, Integer pickStatus,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        String[] houseTypes = null;
        if(houseType != null){
            houseTypes = houseType.split(",");
        }
        String[] statusList = null;
        if(!CheckUtils.isNull(status)){
            statusList=status.split(",");
        }
        List<LogisticsOutHouse> list = logisticsOutHouseDao.findList(houseNo, houseTypes, statusList, startTime, endTime);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        for (LogisticsOutHouse logisticsOutHouse:list) {
            if(!CheckUtils.isNull(logisticsOutHouse.getExecuteUser())){
                for (User user:userList) {
                    if(logisticsOutHouse.getExecuteUser().equals(user.getId())){
                        logisticsOutHouse.setUser(user);
                    }
                }
            }
        }
        PageInfo<LogisticsOutHouse> pageInfo = new PageInfo<>(list);
        return ResultUtils.success(pageInfo);
    }

    @Override
    public Result findByQrCode(String qrCode,Integer outHouseId,Integer type) {
        if(!CheckUtils.isNumeric(qrCode)){
            return ResultUtils.error("参数错误");
        }
        Integer id = Integer.parseInt(qrCode);//根据二维码获取物料库存id和物料编号
        LogisticsStorageDetail result;
        if(type == 0){ //内部出库扫描二维码哦
            result = logisticsOutHouseDao.findById(id, outHouseId);
        }else{ //外部出库扫描二维码
            result = logisticsOutHouseDao.outScanQrCode(id,outHouseId);
        }
        return ResultUtils.success(result);
    }

    @Override
    @Transactional
    public Result saveOutSubPack(InfoBox<LogisticsOutHouseDetail> infoBox) {
        List<LogisticsOutHouseDetail> data = infoBox.getData();
        if(data != null && data.size() > 0){


            LogisticsOutHouse logisticsOutHouse = new LogisticsOutHouse();
            logisticsOutHouse.setId(infoBox.getId());
            logisticsOutHouse.setLogisticsOutHouseDetailList(data);
            //修改库存数量
            Integer count = storageProductUtil.updateCount(logisticsOutHouse);
            if(count < 0){
                return ResultUtils.error("出库数量大于库存数量");
            }

            List<LogisticsOutSubpackage> logisticsOutSubpackages = new ArrayList<>();
            Map<Integer,Integer> storageMap = new HashMap<>();
            Map<Integer,Integer> applyMap = new HashMap<>();
            for (LogisticsOutHouseDetail logisticsOutHouseDetail:data) {
                if(logisticsOutHouseDetail.getStorageDetailId() != null && logisticsOutHouseDetail.getStorageDetailId() != 0){
                    //插入出库明细
                    logisticsOutHouseDetailDao.insertOne(logisticsOutHouseDetail);
                    List<LogisticsOutSubpackage> outSubpackages = logisticsOutHouseDetail.getLogisticsOutSubpackages();
                    if(outSubpackages != null && outSubpackages.size() > 0 ){
                        for (LogisticsOutSubpackage logisticsOutSubpackage:outSubpackages) {
                            logisticsOutSubpackage.setOutDetailId(logisticsOutHouseDetail.getId());
                        }
                        logisticsOutSubpackages.addAll(outSubpackages);
                    }
                }
                //计算发货的实出数量
                if(storageMap.containsKey(logisticsOutHouseDetail.getPickingOrderId())){
                    storageMap.put(logisticsOutHouseDetail.getPickingOrderId(),storageMap.get(logisticsOutHouseDetail.getPickingOrderId())+logisticsOutHouseDetail.getStorageCount());
                }else{
                    storageMap.put(logisticsOutHouseDetail.getPickingOrderId(),logisticsOutHouseDetail.getStorageCount());
                }
                //记录发货的应出数量
                if(!applyMap.containsKey(logisticsOutHouseDetail.getPickingOrderId())){
                    applyMap.put(logisticsOutHouseDetail.getPickingOrderId(),Integer.parseInt(logisticsOutHouseDetail.getApplyCount()));
                }
            }
            if(logisticsOutSubpackages.size() > 0){
                //插入拆包明细
                logisticsOutSubpackageDao.insert(logisticsOutSubpackages);
            }
            //修改发货单的实出数量
            Set<Integer> set = storageMap.keySet();
            List<LogisticsPickingOrder> logisticsPickingOrders = new ArrayList<>();
            //查询和出库单相关的发货单
            List<LogisticsPickingOrder> lo = logisticsPickingOrderDao.findByOutHouseId(infoBox.getId());
            //将获取到的发货单转成MAP集合
            Map<Integer, Integer> collect = lo.stream().collect(Collectors.toMap(LogisticsPickingOrder::getId, LogisticsPickingOrder::getStorageCount));
            boolean flag = true;
            for (Integer pickingOrderId:set) {
                int sumCount = 0;
                if(collect.containsKey(pickingOrderId)){
                    sumCount = collect.get(pickingOrderId);
                }
                sumCount += storageMap.get(pickingOrderId);
                LogisticsPickingOrder logisticsPickingOrder = new LogisticsPickingOrder();
                logisticsPickingOrder.setId(pickingOrderId);
                logisticsPickingOrder.setStorageCount(sumCount);
                logisticsPickingOrders.add(logisticsPickingOrder);
                //判断应出数量是否大于实出数量
                if(applyMap.get(pickingOrderId) > sumCount){
                    flag = false;
                }
            }
            //修改发货单的实出数量
            if(logisticsPickingOrders.size() > 0){
                logisticsPickingOrderDao.updateStorageCountList(logisticsPickingOrders);
            }
            //修改出库单状态
            updateOutHouseStatus(infoBox.getId(),flag);
            return ResultUtils.success();
        }
        return ResultUtils.failure();
    }

    @Override
    public Result findOutSubpackage(String outHouseId) {
        return ResultUtils.success(logisticsOutSubpackageDao.findOutSubpackage(outHouseId));
    }

    @Override
    @Transactional
    public Result saveOutHouseDetail(InfoBox<LogisticsDeliveryPlan> infoBox) {
        List<LogisticsDeliveryPlan> logisticsDeliveryPlans = infoBox.getData();
        List<LogisticsOutHouseDetail> resultList = new ArrayList<>();
        int count = 0;
        if(logisticsDeliveryPlans == null){
            return ResultUtils.error("参数错误");
        }
        for (LogisticsDeliveryPlan logisticsDeliveryPlan:logisticsDeliveryPlans) {
            List<LogisticsOutHouseDetail> logisticsOutHouseDetails = logisticsDeliveryPlan.getLogisticsOutHouseDetails();
            if(logisticsOutHouseDetails != null){
                for (LogisticsOutHouseDetail logisticsOutHouseDetail:logisticsOutHouseDetails) {
                    if(logisticsOutHouseDetail.getId() == null){
                        resultList.add(logisticsOutHouseDetail);
                    }
                }
            }
            //如果应出数量小于或者等于实出数量，就将发货计划状态改为完成 1
            if(logisticsDeliveryPlan.getPlanCount() <= logisticsDeliveryPlan.getPartsCount()){
                logisticsDeliveryPlan.setStatus(1);
            }else{
                count++;
            }
        }

        if(resultList.size() > 0){
            //修改库存数量
            LogisticsOutHouse logisticsOutHouse = new LogisticsOutHouse();
            logisticsOutHouse.setId(infoBox.getId());
            logisticsOutHouse.setLogisticsOutHouseDetailList(resultList);
            Integer foo = storageProductUtil.updateCount(logisticsOutHouse);
            if(foo < 0){
                return ResultUtils.error("出库数量大于库存数量");
            }
            logisticsOutHouseDetailDao.insert(resultList);//插入发货明细
        }
        if(logisticsDeliveryPlans.size() > 0){
            if(count == 0){
                updateOutHouseStatus(infoBox.getId(),true);
            }
            logisticsDeliveryPlanDao.updateList(logisticsDeliveryPlans);//修改发货计划的状态和发货数量
            return ResultUtils.success();
        }
        return ResultUtils.failure();
    }

    //出库完成，修改出库单状态
    public Integer updateOutHouseStatus(Integer outHouseId,boolean flag){
        LogisticsOutHouse logisticsOutHouse = new LogisticsOutHouse();
        logisticsOutHouse.setId(outHouseId);
        if(flag){
            logisticsOutHouse.setStatus(1);
        }
        logisticsOutHouse.setExecuteTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
        logisticsOutHouse.setExecuteUser(headerUtil.loginUser().getId());
        logisticsOutHouse.setStoreDate(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
        return logisticsOutHouseDao.update(logisticsOutHouse);
    }
    /**
     * 每天早上8点关闭前一天发货计划
     * @param
     * @return
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void autoUpdateStatus(){
        logisticsOutHouseDao.updateStatus();
    }

    @Override
    public Result findDetailByProductOrder(String productOrder) {
        return ResultUtils.success(logisticsOutHouseDetailDao.findDetailByProductOrder(productOrder));
    }
}
