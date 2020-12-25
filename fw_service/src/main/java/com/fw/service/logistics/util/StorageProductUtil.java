package com.fw.service.logistics.util;

import com.fw.entity.logistics.*;
import com.fw.service.logistics.dao.LogisticsCheckHouseDao;
import com.fw.service.logistics.dao.LogisticsStorageDetailDao;
import com.fw.service.logistics.dao.LogisticsStorageLogDao;
import com.fw.utils.CheckUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 物流物料库存操作
 * @Author lpsong
 * @Date 2020/11/13
 */
@Component
public class StorageProductUtil {

    @Autowired
    private LogisticsStorageDetailDao logisticsStorageDetailDao;

    @Autowired
    private LogisticsStorageLogDao logisticsStorageLogDao;
    /**
     * 入库
     * @param
     * @return
     */
    public Integer inStorage(LogisticsStoreHouse logisticsStoreHouse) {
        Integer flag = 0;
        //获取入库物料
        List<LogisticsStoreHouseDetail> detailList=logisticsStoreHouse.getLogisticsStoreHouseDetailList();
        if(!CollectionUtils.isEmpty(detailList)){
            //循环遍历，封装为库存物料对象
            List<LogisticsStorageDetail> logisticsStorageDetails=new ArrayList<>();
            for (LogisticsStoreHouseDetail houseDetail : detailList) {
                logisticsStorageDetails.add(new LogisticsStorageDetail(houseDetail.getId(),
                        houseDetail.getStorageLocationId(),
                        houseDetail.getProductId(),
                        houseDetail.getStorageCount(),
                        houseDetail.getFwBatch(),
                        logisticsStoreHouse.getProviderName(),
                        houseDetail.getProviderBatch(),
                        houseDetail.getOrderDetailId()));
            }
            //更新库存物料
            flag=logisticsStorageDetailDao.inStorage(logisticsStorageDetails);
            if(flag>0){
                //保存操作日志
                flag= saveStorageLog(0,logisticsStorageDetails);
            }
        }
        return flag;
    }
    /**
    * 保存操作日志
     * @param logisticsStorageDetails
    * @return
    */
    private Integer saveStorageLog(Integer operate,List<LogisticsStorageDetail> logisticsStorageDetails){
        Integer flag = 0;
        List<LogisticsStorageLog> logisticsStorageLogs=new ArrayList<>();
        //更新操作日志
        for (LogisticsStorageDetail storageDetail : logisticsStorageDetails) {
            LogisticsStorageLog storageLog=new LogisticsStorageLog();
            storageLog.setOpreateDetailId(storageDetail.getOpreateDetailId());
            storageLog.setOpreateType(operate);
            storageLog.setStorageDetailId(storageDetail.getId());
            if(!CheckUtils.isNull(storageDetail.getStorageCount())){
                storageLog.setStorageCount(String.valueOf(storageDetail.getStorageCount()));
                storageLog.setBeforeCount(String.valueOf(storageDetail.getBeforeCount()));
                storageLog.setAfterCount(String.valueOf(storageDetail.getBeforeCount()+storageDetail.getStorageCount()));
            }
            logisticsStorageLogs.add(storageLog);
            if(!CollectionUtils.isEmpty(logisticsStorageLogs)){
                flag=logisticsStorageLogDao.insert(logisticsStorageLogs);
            }
        }
        return flag;
    }
    /**
     * 退库
     *
     * @param
     * @return
     */
    public Integer backStorage(LogisticsBackHouse logisticsBackHouse) {
        Integer flag = 0;
        //获取入库物料
        List<LogisticsBackHouseDetail> detailList=logisticsBackHouse.getLogisticsBackHouseDetails();
        if(!CollectionUtils.isEmpty(detailList)){
            //循环遍历，封装为库存物料对象
            List<LogisticsStorageDetail> logisticsStorageDetails=new ArrayList<>();
            for (LogisticsBackHouseDetail houseDetail: detailList) {
                logisticsStorageDetails.add(new LogisticsStorageDetail(houseDetail.getId(),
                        houseDetail.getStorageLocationId(),
                        houseDetail.getProductId(),
                        houseDetail.getStorageCount(),
                        houseDetail.getFwBatch()));
            }
            //更新库存物料
            flag=logisticsStorageDetailDao.inStorage(logisticsStorageDetails);
            if(flag>0){
                //保存操作日志
                flag= saveStorageLog(0,logisticsStorageDetails);
            }
        }
        return flag;
    }

    /**
     * 出库
     *
     * @param
     * @return
     */
    public Integer updateCount(LogisticsOutHouse logisticsOutHouse) {
        Integer flag = 0;
        //获取出库物料
        List<LogisticsOutHouseDetail> detailList=logisticsOutHouse.getLogisticsOutHouseDetailList();
        if(!CollectionUtils.isEmpty(detailList)) {
            List<LogisticsStorageDetail> logisticsStorageDetails = new ArrayList<>();
            //循环判断库存物料是否满足出库要求
            for (LogisticsOutHouseDetail houseDetail : detailList) {
                LogisticsStorageDetail entrty= logisticsStorageDetailDao.selectById(houseDetail.getStorageDetailId());
                //比较库存是否满足出库要求，如果不满足返回-1
                if(entrty.getStorageCount()>=houseDetail.getStorageCount()){
                    logisticsStorageDetails.add(new LogisticsStorageDetail(houseDetail.getStorageDetailId(), entrty.getStorageCount(),entrty.getStorageCount()-houseDetail.getStorageCount()));
                }else{
                    return -1;
                }
            }
            //更新库存物料数量
            if(logisticsStorageDetails!=null&&logisticsStorageDetails.size()>0){
                flag=logisticsStorageDetailDao.updateCount(logisticsStorageDetails);
                if(flag>0){
                    //保存操作日志
                    flag= saveStorageLog(1,logisticsStorageDetails);
                }
            }
        }
        return flag;
    }

}