package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.logistics.LogisticsOutHouse;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsDeliveryPlanDao;
import com.fw.service.logistics.dao.LogisticsOutHouseDao;
import com.fw.service.logistics.service.LogisticsDeliveryPlanService;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 发货计划
 * @author lpsong
 * @since 2020-12-04
 */
@Service
public class LogisticsDeliveryPlanServiceImpl implements LogisticsDeliveryPlanService {
    @Autowired
    private LogisticsDeliveryPlanDao logisticsDeliveryPlanDao;
    @Resource
    private LogisticsOutHouseDao logisticsOutHouseDao;
    @Override
    @Transactional
    public Result insert(List<LogisticsDeliveryPlan> logisticsDeliveryPlans) {
        Integer flag = 0;
        if (!CollectionUtils.isEmpty(logisticsDeliveryPlans)) {
            //按客户分组，一个客户一个出库任务
            Map<String, List<LogisticsDeliveryPlan>> groupList = logisticsDeliveryPlans.stream().collect(Collectors.groupingBy(LogisticsDeliveryPlan::getCustomer));
            List<LogisticsDeliveryPlan> insertList = new ArrayList<>();
            groupList.forEach((x, y) -> {
                //保存出库任务
                LogisticsOutHouse logisticsOutHouse = new LogisticsOutHouse();
                //查询该客户当前是否有未关闭的出库单，如果有就不新增，只更新发货计划，如果没有就新增一个出库单
                Integer outHouseId=logisticsDeliveryPlanDao.selectOutHouseIdByCustomer(x);
                if(CheckUtils.isNull(outHouseId)){
                    //查询出库任务编号
                    logisticsOutHouse.setHouseNo(logisticsOutHouseDao.findCode(CodeEnum.LOGISTICS_04.getCode()));
                    logisticsOutHouse.setHouseType("0");
                    logisticsOutHouse.setStoreDate(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                    logisticsOutHouseDao.planInsert(logisticsOutHouse);
                }else{
                    logisticsOutHouse.setId(outHouseId);
                }
                y.forEach(item -> {
                    item.setOutHouseId(logisticsOutHouse.getId());
                    insertList.add(item);
                });
            });
            //保存发货计划
            if (!CollectionUtils.isEmpty(insertList)) {
                flag=logisticsDeliveryPlanDao.insert(insertList);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result update(LogisticsDeliveryPlan logisticsDeliveryPlan) {
        Integer flag = 0;
        if(CheckUtils.isNumeric(logisticsDeliveryPlan.getId())){
            LogisticsDeliveryPlan entity=logisticsDeliveryPlanDao.selectPlanById(logisticsDeliveryPlan.getId());
            if(entity!=null){
                flag=logisticsDeliveryPlanDao.update(logisticsDeliveryPlan);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list=Arrays.asList(ids.split(","));
        if(!CollectionUtils.isEmpty(list)){
            //先查询要删除发货计划，并按出库单分组，判断发货计划删除后，出库单下计划是否为空
            List<LogisticsDeliveryPlan> logisticsDeliveryPlans=logisticsDeliveryPlanDao.selectById(list);
            Map<Integer, List<LogisticsDeliveryPlan>> groupList = logisticsDeliveryPlans.stream().collect(Collectors.groupingBy(LogisticsDeliveryPlan::getOutHouseId));
            flag = logisticsDeliveryPlanDao.delete(list);
            //判断发货计划删除后，出库单下发货计划为空，则同时删除发货计划
            groupList.forEach((x, y) -> {
                if(logisticsDeliveryPlanDao.findListByOutHouseId(x)==0){
                    logisticsOutHouseDao.delete(x);
                }
            });
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result updateStatus(Integer id,Integer status) {
        Integer flag = 0;
        if (!CheckUtils.isNull(id)) {
            flag = logisticsDeliveryPlanDao.updateStatus(id,status);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String customer, String productCode, String deliverTime,
                           Integer status, Integer outHouseId, Integer queryType,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsDeliveryPlan> list = logisticsDeliveryPlanDao.findList(customer,productCode, deliverTime,status,outHouseId,queryType);
        PageInfo<LogisticsDeliveryPlan> DeliveryPlanPageInfo = new PageInfo<>(list);
        return ResultUtils.success(DeliveryPlanPageInfo);
    }

    @Override
    public Result findByOutHouseId(Integer outHouseId) {
        return ResultUtils.success(logisticsDeliveryPlanDao.findByOutHouseId(outHouseId));
    }
}
