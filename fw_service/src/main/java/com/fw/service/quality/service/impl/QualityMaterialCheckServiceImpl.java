package com.fw.service.quality.service.impl;


import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.entity.logistics.LogisticsTakeOrderDetail;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.entity.quality.QualityMaterialCheck;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsStoreHouseDao;
import com.fw.service.logistics.dao.LogisticsTakeOrderDao;
import com.fw.service.logistics.service.LogisticsStoreHouseService;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.quality.dao.QualityInspectResultDao;
import com.fw.service.quality.dao.QualityInspectionDao;
import com.fw.service.quality.dao.QualityMaterialCheckDao;
import com.fw.service.quality.service.QualityMaterialCheckService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 来料检验管理 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-25
 */
@Service
@Transactional
public class QualityMaterialCheckServiceImpl implements QualityMaterialCheckService {

    @Autowired
    private QualityMaterialCheckDao qualityMaterialCheckDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private QualityInspectResultDao qualityInspectResultDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private LogisticsStoreHouseService logisticsStoreHouseService;

    @Autowired
    private LogisticsStoreHouseDao logisticsStoreHouseDao;

    @Autowired
    private LogisticsTakeOrderDao logisticsTakeOrderDao;

    @Autowired
    private ProduceMaterialMonitorDao produceMaterialMonitorDao;

    @Override
    public Result findList(String checkNo, String startTime, String stopTime, String exeStartTime, String exeStopTime, Integer status, Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QualityMaterialCheck> list = qualityMaterialCheckDao.findList(checkNo, startTime, stopTime, exeStartTime, exeStopTime, status);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());

        //比对获取用户名称
        if (!CollectionUtils.isEmpty(list) && !CollectionUtils.isEmpty(userList)) {
            for (QualityMaterialCheck qualityMaterialCheck : list) {
                for (User user : userList) {
                    if (qualityMaterialCheck.getCreateUser() == user.getId()) {
                        qualityMaterialCheck.setCreateUserName(user.getName());
                    }
                }
            }
        }
        PageInfo<QualityMaterialCheck> listPage = new PageInfo<>(list);
        return ResultUtils.success(listPage);
    }

    @Override
    public Result getCheckoutMaterialInfoById(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return ResultUtils.success(qualityMaterialCheckDao.getCheckoutMaterialInfoById(id));
    }

    /**
     * 来料检验结果提交
     *
     * @param qualityInspectResultList
     * @return
     */
    @Override
    public Result submit(List<QualityInspectResult> qualityInspectResultList) {

        //物料过程监控集合
        List<ProduceMaterialMonitor> produceMaterialMonitorList = new ArrayList<>();

        int flag = 0;
        if (CollectionUtils.isEmpty(qualityInspectResultList)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        for (QualityInspectResult qualityInspectResult : qualityInspectResultList) {

            qualityInspectResult.setUser(authUserUtil.userId());
            flag += qualityInspectResultDao.save(qualityInspectResult);
            //修改任务状态
            if (flag > 0) {
                QualityMaterialCheck qualityMaterialCheck = new QualityMaterialCheck();
                //0待执行 1已执行
                qualityMaterialCheck.setId(qualityInspectResult.getDataId());
                qualityMaterialCheck.setStatus(1);
                qualityMaterialCheck.setCreateUser(authUserUtil.userId());
                qualityMaterialCheckDao.update(qualityMaterialCheck);

                //物料过程监控
                //produceMaterialMonitor.setProductId(Integer.parseInt(qualityInspectResult.getMaterial()));
                //更具来料检验id反查收货明细Ids
                List<String> takeDetailIds = qualityInspectResultDao.findTakeDetailIds(qualityInspectResult.getDataId());
                if (takeDetailIds != null && takeDetailIds.size() > 0) {
                    for (String takeDetailId : takeDetailIds) {
                        //物料过程监控实体
                        ProduceMaterialMonitor produceMaterialMonitor = new ProduceMaterialMonitor();
                        produceMaterialMonitor.setId(takeDetailId);
                        produceMaterialMonitor.setCheckUser(headerUtil.loginUser().getName());
                        produceMaterialMonitor.setCheckTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                        produceMaterialMonitorList.add(produceMaterialMonitor);
                    }
                }

            }
        }

        //插入物料过程监控检验人、检验时间
        if (produceMaterialMonitorList != null && produceMaterialMonitorList.size() > 0) {
            produceMaterialMonitorDao.update(produceMaterialMonitorList);
        }

        //前端将质检是否合格放在第一条中，因为多条检验对应一个质检结果 0合格
        if (flag > 0 && !CollectionUtils.isEmpty(qualityInspectResultList) && qualityInspectResultList.get(0).getResult() == 0) {
            //来料检验任务单  --捞源单单号
            QualityMaterialCheck qualityMaterialCheck = qualityMaterialCheckDao.findCheckoutById(qualityInspectResultList.get(0).getDataId());
            //入库单
            LogisticsStoreHouse logisticsStoreHouse = new LogisticsStoreHouse();
            logisticsStoreHouse.setHouseType("0");//入库类型 0-采购入库 1-委外生产入库 2-生产入库 3-其他入库
            logisticsStoreHouse.setHouseNo(logisticsStoreHouseDao.findCode(CodeEnum.LOGISTICS_02.getCode()));//外购入库单号
            logisticsStoreHouse.setOrderNo(qualityMaterialCheck.getSourceNo()); //源单单号
            //入库明细集合
            List<LogisticsStoreHouseDetail> logisticsStoreHouseDetailList = new ArrayList<>();
            //更具来料检验id反查收货明细Ids
            List<String> takeDetailIds = qualityInspectResultDao.findTakeDetailIds(qualityInspectResultList.get(0).getDataId());
            if (takeDetailIds != null && takeDetailIds.size() > 0) {
                for (String takeDetailId : takeDetailIds) {
                    //收货明细
                    LogisticsTakeOrderDetail takeOrderDetail = logisticsTakeOrderDao.findDetailByid(takeDetailId);
                    //入库明细
                    LogisticsStoreHouseDetail logisticsStoreHouseDetail = new LogisticsStoreHouseDetail();
                    logisticsStoreHouseDetail.setProductId(takeOrderDetail.getProductId());//物料ID
                    logisticsStoreHouseDetail.setMaterialCount(String.valueOf(takeOrderDetail.getBuyCount()));//物料数量
                    logisticsStoreHouseDetail.setStorageCount(takeOrderDetail.getReceiveCount());//实收数量
                    logisticsStoreHouseDetail.setFwBatch(takeOrderDetail.getBatchNo());//泛沃批次号
                    logisticsStoreHouseDetail.setOrderDetailId(takeDetailId);//收货明细ID
                    logisticsStoreHouseDetailList.add(logisticsStoreHouseDetail);
                }

            }

            logisticsStoreHouse.setLogisticsStoreHouseDetailList(logisticsStoreHouseDetailList);
            //推入库任务
            logisticsStoreHouseService.saveStoreHouse(logisticsStoreHouse);

        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 查询质检结果
     *
     * @param dataId
     * @return
     */
    @Override
    public Result findResultByDataIdAndClassify(Integer dataId) {
        if (CheckUtils.isNull(dataId)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //0 来料 1首末检/巡检 2入库 3出库
        return ResultUtils.success(qualityInspectResultDao.findResultByDataIdAndClassify(dataId, 0));
    }
}
