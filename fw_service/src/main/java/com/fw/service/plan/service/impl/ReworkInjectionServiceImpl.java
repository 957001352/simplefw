package com.fw.service.plan.service.impl;

import com.fw.domain.Result;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.ReworkStopList;
import com.fw.enums.ResultEnum;
import com.fw.service.plan.dao.ReworkInjectionDao;
import com.fw.service.plan.service.ReworkInjectionService;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 二次加工排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@Service
public class ReworkInjectionServiceImpl implements ReworkInjectionService {

    @Autowired
    private ReworkInjectionDao reworkInjectionDao;
    @Autowired
    private AuthUserUtil authUserUtil;

    @Override
    public Result insert(ReworkInjection reworkInjection) {
        Integer flag = 0;
        //id为空新增设备保养项目
        if (CheckUtils.isNull(reworkInjection.getId())) {
            //查询生产指令的开始时间
            Map<String, String> productCode = createProductCode(reworkInjection.getProductTime());
            String startTime = productCode.get("startTime");
            reworkInjection.setStartTime(startTime);
            reworkInjection.setCreateUser(authUserUtil.userId());
            reworkInjection.setCreateTime(DateUtils.getTodayTime());
            flag = reworkInjectionDao.insert(reworkInjection);
        }
        return flag >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result update(ReworkInjection reworkInjection) {
        Integer flag = 0;
        if (CheckUtils.isNumeric(reworkInjection.getId())) {
            //修改时先查询数据是否存在,多个窗口操作问题
            ReworkInjection entrty = reworkInjectionDao.selectById(reworkInjection.getId());
            if (entrty == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            String resultTime = getResultTime(reworkInjection);
            List<ReworkInjection> laters = reworkInjectionDao.findLater(reworkInjection);
            for (ReworkInjection rework:laters) {
                rework.setStartTime(resultTime);
                resultTime = getResultTime(rework);
            }
            reworkInjection.setUpdateUser(authUserUtil.userId());
            reworkInjection.setUpdateTime(DateUtils.getTodayTime());
            laters.add(reworkInjection);
            flag = reworkInjectionDao.updateListTime(laters);
        }
        return flag >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result moveUp(ReworkInjection reworkInjection) {
        ReworkInjection entrty = reworkInjectionDao.selectById(reworkInjection.getId());
        if (entrty == null) {
            return ResultUtils.error("移动失败,请刷新页面");
        }
        //查询需要重新排产的生产指令
        List<ReworkInjection> reworkInjections = reworkInjectionDao.findMoveUp(reworkInjection);
        if(reworkInjections.size() == 0){
            return ResultUtils.error("已经是第一位");
        }
        Collections.reverse(reworkInjections);
        String startTime = reworkInjections.get(0).getStartTime();//获取重新排序的基准时间
        reworkInjection.setStartTime(startTime);
        String endTime = getResultTime(reworkInjection);
        for (ReworkInjection rework:reworkInjections) {
            rework.setStartTime(endTime);
            endTime = getResultTime(rework);
        }
        reworkInjections.add(reworkInjection);
        //批量修改生产指令的时间
        Integer update = reworkInjectionDao.updateListTime(reworkInjections);
        return update>=0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result moveDown(ReworkInjection reworkInjection) {
        ReworkInjection entrty = reworkInjectionDao.selectById(reworkInjection.getId());
        if (entrty == null) {
            return ResultUtils.error("移动失败,请刷新页面");
        }
        //查询需要重新排产的生产指令
        List<ReworkInjection> reworkInjections = reworkInjectionDao.findMoveDown(reworkInjection);
        if(reworkInjections.size() == 0) {
            return ResultUtils.error("已经是最后一位");
        }
        reworkInjections.get(0).setStartTime(reworkInjection.getStartTime());
        String endTime = getResultTime(reworkInjections.get(0));
        for (int i = 1; i < reworkInjections.size(); i++) {
            reworkInjections.get(i).setStartTime(endTime);
            endTime = getResultTime(reworkInjections.get(i));
        }
        reworkInjection.setStartTime(endTime);
        reworkInjections.add(reworkInjection);
        //批量修改生产指令的时间
        Integer update = reworkInjectionDao.updateListTime(reworkInjections);
        return update>=0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result stop(ReworkStopList reworkStopList) {
        Integer flag = 0;
        if (!CheckUtils.isNumeric(reworkStopList.getInjectionId())) {
            //修改时先查询数据是否存在,多个窗口操作问题
            ReworkInjection entrty = reworkInjectionDao.selectById(reworkStopList.getInjectionId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("暂停失败,请刷新页面");
            }
            //修改生活指令的状态为暂停状态
            reworkInjectionDao.updateStatus(reworkStopList.getInjectionId(),2);
            flag = reworkInjectionDao.insertStopList(reworkStopList);
        }
        return flag >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result cancel(ReworkInjection reworkInjection) {
        Integer update = reworkInjectionDao.updateStatus(reworkInjection.getId(), 3);
        if(update > 0){
            List<ReworkInjection> cancels = reworkInjectionDao.findLater(reworkInjection);
            String endTime = reworkInjection.getStartTime(); //获取取消的生产指令，并把他当作下一个生产指令的开始时间
            for (ReworkInjection cancel : cancels) {
                cancel.setStartTime(endTime);
                endTime = getResultTime(cancel);
            }
            if(cancels.size() > 0){
                //批量修改生产指令的时间
                update = reworkInjectionDao.updateListTime(cancels);
            }
        }
        return update>=0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String productCode, String partsCode, String startTime, String endTime, String status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ReworkInjection> list = reworkInjectionDao.findList(productCode,partsCode,startTime,endTime,status);
        return ResultUtils.success( new PageInfo<>(list));
    }

    @Override
    public Map<String,String> createProductCode(Double productTime) {
        Map<String,String> map = new HashMap<>();
        //获取最后一次生产指令
        ReworkInjection reworkInjection = reworkInjectionDao.findLastProductCode();
        String startTime = DateUtils.getToday("yyyy-MM-dd HH:mm");
         if(reworkInjection != null){
             startTime = getResultTime(reworkInjection);
        }
        map.put("startTime", startTime);
        map.put("endTime",DateUtils.getAddTime(startTime, (int) Math.ceil(productTime)));
        map.put("productCode", UUID.randomUUID().toString());//获取生产指令
        return map;
    }

    private String getResultTime(ReworkInjection reworkInjection){
        String oldStartTime = reworkInjection.getStartTime(); //开始时间
        Double productTime = 0.0;  //预计耗时
        Double stopTime = 0.0;//暂停时长
        if(reworkInjection.getProductTime() != null){
            productTime = reworkInjection.getProductTime();
        }
        if(reworkInjection.getStopTime() != null){
            stopTime = reworkInjection.getStopTime();
        }
        int sumTime = (int) Math.ceil(productTime+stopTime);
        return DateUtils.getAddTime(oldStartTime,sumTime);
    }
}
