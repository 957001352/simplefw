package com.fw.service.plan.service.impl;

import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldKeepTask;
import com.fw.entity.mould.MouldRepair;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanList;
import com.fw.entity.plan.PlanSwap;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesKeepTaskDao;
import com.fw.service.device.dao.DevicesRepairDao;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldKeepTaskDao;
import com.fw.service.mould.dao.MouldRepairDao;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.plan.service.InjectionMoldingService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.PlanUtils;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 注塑排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@Service
@Slf4j
public class InjectionMoldingServiceImpl implements InjectionMoldingService {

    @Autowired
    private InjectionMoldingDao injectionMoldingDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Resource
    private MouldDevicesDao mouldDevicesDao;
    @Resource
    private MouldRepairDao mouldRepairDao;
    @Resource
    private MouldKeepTaskDao mouldKeepTaskDao;
    @Resource
    private DevicesRepairDao devicesRepairDao;
    @Resource
    private DevicesKeepTaskDao devicesKeepTaskDao;
    
    @Override
    public Result insert(InjectionMolding injectionMolding) {
        Integer flag = 0;
        //id为空新增设备保养项目
        if (CheckUtils.isNull(injectionMolding.getId())) {
            injectionMolding.setCreateUser(authUserUtil.userId());
            injectionMolding.setCreateTime(DateUtils.getTodayTime());
            flag = injectionMoldingDao.insert(injectionMolding);
        }
        return flag >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result update(InjectionMolding injectionMolding) {
        InjectionMolding oldInjectionMolding = injectionMoldingDao.findById(injectionMolding.getId());
        if (oldInjectionMolding == null) {
            return ResultUtils.error("修改失败,请刷新页面");
        }
        oldInjectionMolding.setPlanCount(injectionMolding.getPlanCount());
        CraftModel craftModel = oldInjectionMolding.getCraftMode();
        //计算变化生产数量后的生产时间
        Integer productTime = PlanUtils.getProductTime(craftModel.getBeat(), oldInjectionMolding.getPlanCount(), oldInjectionMolding.getCavity(),
                craftModel.getFirstCheck(), craftModel.getFirstDebug(), 30);
        oldInjectionMolding.setProductTime((double)productTime);
        oldInjectionMolding.setUpdateUser(headerUtil.loginUser().getId());
        String endTime = DateUtils.getToday("yyyy-MM-dd HH:mm");//获取当前时间
        oldInjectionMolding.setUpdateTime(endTime);
        Integer update = -1;

        //判断是否更换生产设备
        if(oldInjectionMolding.getProductDevicesId().equals(injectionMolding.getProductDevicesId())){
            update = injectionMoldingDao.update(oldInjectionMolding);
            if(update > 0){
                updatePlanCount(oldInjectionMolding);
            }
        }else{
            Integer oldProductDevicesId = oldInjectionMolding.getProductDevicesId();
            String oldStartTime = oldInjectionMolding.getStartTime();

            oldInjectionMolding.setProductDevicesId(injectionMolding.getProductDevicesId());
            //根据设备id获取设备最后一次生产指令信息
            InjectionMolding lastInjectionDevice =injectionMoldingDao.findByDeviceId(oldInjectionMolding.getProductDevicesId());
            //获取修改生产指令的开始时间
            if(lastInjectionDevice != null){
                endTime = PlanUtils.getEndTime(lastInjectionDevice);
            }
            oldInjectionMolding.setStartTime(endTime);
            update = injectionMoldingDao.update(oldInjectionMolding);

            //将原设备的生产指令重新排列
            injectionMolding.setProductDevicesId(oldProductDevicesId);
            injectionMolding.setStartTime(oldStartTime);
            updatePlanCount(injectionMolding);
        }
        return update > 0?ResultUtils.success():ResultUtils.failure();
    }
    //修改生产数量重新进行生产计划时间排序
    public void updatePlanCount(InjectionMolding injectionMolding){
        List<InjectionMolding> byTime = injectionMoldingDao.findByTime(injectionMolding.getStartTime(), null, injectionMolding.getProductDevicesId());
        int size = byTime.size();
        List<InjectionMolding> injm = new ArrayList<>();
        if(size >= 1){
            String time = injectionMolding.getStartTime();
            for (int i = 0; i < size; i++) {
                InjectionMolding im = byTime.get(i);
                im.setStartTime(time);
                //计算每一个生产指令的生产结束时间-->开始时间+生产时间+暂停时间
                int pt = im.getProductTime().intValue();
                if(im.getStopTime() != null){
                    pt += im.getStopTime();
                }
                time = DateUtils.getAddTime(im.getStartTime(), pt);
                injm.add(im);
            }
        }
        if(injm.size() != 0){
            //批量修改生产指令的开始时间
            injectionMoldingDao.updateList(injm);
        }
    }

    @Override
    @Transactional
    public Result stop(InjectionStopList injectionStopList) {
        Integer flag = 0;
        if (CheckUtils.isNumeric(injectionStopList.getInjectionId())) {
            //修改时先查询数据是否存在,多个窗口操作问题
            InjectionMolding entrty = injectionMoldingDao.selectById(injectionStopList.getInjectionId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("暂停失败,请刷新页面");
            }
            //修改生产指令的状态为暂停状态
            InjectionMolding injectionMolding = new InjectionMolding();
            injectionMolding.setId(injectionStopList.getInjectionId());
            injectionMolding.setStatus(2);
            injectionMoldingDao.updateStatus(injectionMolding);
            flag = injectionMoldingDao.insertStopList(injectionStopList);
        }
        return flag >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(Integer productDevicesId,String productDeviceCode, String productCode, String partsCode, String startTime, String endTime, String status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<InjectionMolding> list = injectionMoldingDao.findList(productDevicesId,productCode,partsCode,startTime,endTime,status);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(productList)) {
            //E2C设备名称设置
            list.forEach(item -> {
                String startTime1 = item.getStartTime();
                item.setStartTime(startTime1.substring(0,startTime1.lastIndexOf(":")));
                String endTime1 = item.getEndTime();
                item.setEndTime(endTime1.substring(0,endTime1.lastIndexOf(":")));
                productList.forEach(vo -> {
                    if (item.getProductDevicesId().toString().equals(vo.getId())) {
                        item.setProductDevicesName(vo.getName());
                    }
                });
            });
        }
        //根据设备编码过滤
        if(!CollectionUtils.isEmpty(list) && !CheckUtils.isNull(productDeviceCode)){
            List<InjectionMolding> filterList = list.stream().filter(e -> productDeviceCode.equals(e.getProductDevicesName())).collect(Collectors.toList());
            return ResultUtils.success( new PageInfo<>(filterList));
        }

        return ResultUtils.success( new PageInfo<>(list));
    }

    @Override
    public Result findPlanList(InjectionMolding injectionMolding) {
        List<PlanList> planLists = new ArrayList<>();
        Integer mouldId = injectionMolding.getMouldId();
        String nowTime = DateUtils.getToday("yyyy-MM-dd HH:mm:ss");
        String cavity = null;
        if(!CheckUtils.isNull(mouldId)){
            PlanList mould = new PlanList();
            //查询模具的信息
            MouldDevices mouldDevice = mouldDevicesDao.findMouldDevicesById(mouldId);
            if(mouldDevice == null) return ResultUtils.error("模具不存在");
            cavity = mouldDevice.getCavity();
            mould.setDeviceId(mouldId);
            mould.setDeviceType(0);//设置设备类型为模具
            mould.setDeviceCode(mouldDevice.getCode());
            if(mouldDevice.getStatus() == 0){ //判断模具是否可用
                //根据模具id获取模具最后一次生产指令信息
                InjectionMolding injectionMold = injectionMoldingDao.findByMouldId(mouldId);
                //查询距现在最近的模具维修订单
                MouldRepair mouldRepair = mouldRepairDao.findByNearNow(mouldId,nowTime);
                String mouldRepairTime = nowTime;
                if(mouldRepair != null){
                    mouldRepairTime = mouldRepair.getPredictTime();
                }
                //查询距现在最近的模具保养订单
                MouldKeepTask mouldKeepTask = mouldKeepTaskDao.findByNearNow(mouldId, nowTime);
                String mouldKeepTaskTime = nowTime;
                if(mouldKeepTask != null){
                    mouldKeepTaskTime = mouldKeepTask.getEndTime();
                }

                //计算预排产的时间
                predictTime(injectionMolding, injectionMold, mould, nowTime, mouldRepairTime, mouldKeepTaskTime,cavity);
            }
            planLists.add(mould);
        }
        String productIds = injectionMolding.getProductIds();
        if(!CheckUtils.isNull(productIds)){
            String[] productId = productIds.split(",");
            //获取所有设备
            List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
            for (String pid:productId) {
                PlanList device = new PlanList();
                Integer id = Integer.parseInt(pid);
                device.setDeviceId(id);
                device.setDeviceType(1);//设置设备类型为设备
                //根据设备id获取设备最后一次生产指令信息
                InjectionMolding injectionDevice =injectionMoldingDao.findByDeviceId(id);
                for (DevicesItemVo devicesItemVo:productDevices) {
                    if(pid.equals(devicesItemVo.getId())){
                        device.setDeviceCode(devicesItemVo.getName());
                        //查询距现在最近的设备维修订单
                        DevicesRepair devicesRepair = devicesRepairDao.findByNearNow(id, nowTime);
                        String devicesRepairTime = nowTime;
                        if(devicesRepair != null){
                            devicesRepairTime = devicesRepair.getPredictTime();
                        }
                        //查询距现在最近的设备保养订单
                        DevicesKeepTask devicesKeepTask = devicesKeepTaskDao.findByNearNow(id, nowTime);
                        String devicesKeepTaskTime = nowTime;
                        if(devicesKeepTask != null){
                            devicesKeepTaskTime = devicesKeepTask.getEndTime();
                        }
                        //计算预排产的时间
                        predictTime(injectionMolding,injectionDevice,device,nowTime,devicesRepairTime,devicesKeepTaskTime,cavity);
                    }
                }
                planLists.add(device);
            }
        }
        return ResultUtils.success(planLists);
    }

    @Override
    @Transactional
    public Result moveUpDownCancel(PlanSwap planSwap) {
        InjectionMolding upPlan = planSwap.getUpInjectionMolding();
        if(upPlan.getStatus() == 3){
            injectionMoldingDao.updateStatus(upPlan);
        }
        InjectionMolding downPlan = planSwap.getDownInjectionMolding();
        String endTime = null;
        boolean flag = false;
        if(downPlan != null){
            endTime = downPlan.getStartTime();
            flag = true;
        }
        List<InjectionMolding> injectionMoldings = injectionMoldingDao.findByTime(
                upPlan.getStartTime(), endTime,upPlan.getProductDevicesId());
        int size = injectionMoldings.size();
        List<InjectionMolding> injm = new ArrayList<>();
        if(size >= 1){
            int start = 1;
            int len = size;
            int init = 0;
            if(flag){
                start = 0;
                len = size - 1;
                init = size - 1;
            }
            InjectionMolding lastOne = injectionMoldings.get(init);
            lastOne.setStartTime(upPlan.getStartTime());
            int productTime = lastOne.getProductTime().intValue();
            if(lastOne.getStopTime() != null){
                productTime += lastOne.getStopTime();
            }
            String time =  DateUtils.getAddTime(lastOne.getStartTime(), productTime);
            injm.add(lastOne);
            for (int i = start; i < len; i++) {
                InjectionMolding im = injectionMoldings.get(i);
                im.setStartTime(time);
                //计算每一个生产指令的生产结束时间-->开始时间+生产时间+暂停时间
                int pt = im.getProductTime().intValue();
                if(im.getStopTime() != null){
                    pt += im.getStopTime();
                }
                time = DateUtils.getAddTime(im.getStartTime(), pt);
                injm.add(im);
            }
        }
        if(injm.size() != 0){
            //批量修改生产指令的开始时间
            Integer update = injectionMoldingDao.updateList(injm);
            return update > 0?ResultUtils.success():ResultUtils.failure();
        }
        return ResultUtils.success();
    }

    public Result reloadSort(InjectionMolding injectionMolding) {
        //获取设备所有未完成和暂停的生产指令
        List<InjectionMolding> unFinishByDeviceId = injectionMoldingDao.findUnFinishByDeviceId(injectionMolding.getProductDevicesId());
        //将生产完成的生产指令的实际完成时间作为第一个生产指令的开始时间,然后进行排序
        String startTime = injectionMolding.getActualEnd();
        for (InjectionMolding injection:unFinishByDeviceId) {
            injection.setStartTime(startTime);
            //计算每一个生产指令的生产结束时间-->开始时间+生产时间+暂停时间
            int pt = injection.getProductTime().intValue();
            if(injection.getStopTime() != null){
                pt += injection.getStopTime();
            }
            startTime = DateUtils.getAddTime(injection.getStartTime(), pt);
        }
        Integer integer = 0;
        if(unFinishByDeviceId.size() > 0){
            integer = injectionMoldingDao.updateList(unFinishByDeviceId);
        }
        return integer >= 0?ResultUtils.success():ResultUtils.failure();
    }

    private void predictTime(InjectionMolding nowInjectionMold, InjectionMolding lastInjectionMold,
                             PlanList planList, String endTime, String predictTime, String keepTaskTime,String cavity){
        CraftModel craftMode = nowInjectionMold.getCraftMode(); //工艺模型
        double firstDebugTime = 0;
        double firstCheckTime = 0;
        int cycle = 1;
        if(craftMode != null){
            //获取首测、首检时间
            firstDebugTime = craftMode.getFirstDebug();
            firstCheckTime = craftMode.getFirstCheck();
            cycle = craftMode.getBeat();
        }
        if(lastInjectionMold != null){
            endTime = PlanUtils.getEndTime(lastInjectionMold); //获取最后一次生产指令完成时间
        }
        //最后一次生产指令完成时间、保养预计完成时间、维修预计完成时间中选择最晚的时间
        String lastTime = compareDate(predictTime, keepTaskTime, endTime);
        //上面的最晚时间+当前生产指令已发生待机时间+当前生产指令已发生停机时间
        String addTime = DateUtils.getAddTime(lastTime, 0);
        planList.setUseTime(addTime);
        //获取生产指令的生产时间
        Integer productTime = PlanUtils.getProductTime(cycle,nowInjectionMold.getPlanCount(),cavity,firstDebugTime,firstCheckTime,30);
        planList.setProductTime(productTime);
        planList.setEndTime(DateUtils.getAddTime(planList.getUseTime(),productTime));
    }

    //比较三个时间
    private String compareDate(String predictTime, String keepTaskTime, String endTime){
        endTime = endTime +":00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar predict = Calendar.getInstance();
        Calendar keepTask = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            predict.setTime(df.parse(predictTime));
            keepTask.setTime(df.parse(keepTaskTime));
            end.setTime(df.parse(endTime));
        } catch (ParseException e) {
            log.error("时间格式不正确");
        }
        String time = endTime;
        int result = predict.compareTo(keepTask);
        if(result == 0){
            int pke = predict.compareTo(end);
            if(pke > 0){
                time = predictTime;
            }
        }else if(result < 0){
            int ke = keepTask.compareTo(end);
            if(ke > 0){
                time = keepTaskTime;
            }
        }else {
            int pe = predict.compareTo(end);
            if(pe > 0){
                time = predictTime;
            }
        }
        return time;
    }

}
