package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.collect.Device;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.craft.CraftCard;
import com.fw.entity.craft.CraftModel;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.mould.MouldHouse;
import com.fw.entity.mould.MouldUseRecord;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.produce.ProduceFeeding;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.enums.ResultEnum;
import com.fw.service.RedisService;
import com.fw.service.collect.dao.DeviceDao;
import com.fw.service.craft.dao.CardLogDao;
import com.fw.service.craft.dao.CardParamsDao;
import com.fw.service.craft.dao.CraftCardDao;
import com.fw.service.craft.dao.CraftModelDao;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsPickingDao;
import com.fw.service.mould.dao.MouldHouseDao;
import com.fw.service.mould.dao.MouldUseRecordDao;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.produce.dao.ProduceFeedingDao;
import com.fw.service.produce.dao.ProduceMoldingMonitorDao;
import com.fw.service.produce.service.ProduceMoldingMonitorService;
import com.fw.service.quality.dao.QualityFirstendCheckDao;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @des: 注塑过程监控 服务实现类
 * @author: xkliu
 * @date: 2020/12/16
 */
@Service
public class ProduceMoldingMonitorServiceImpl implements ProduceMoldingMonitorService {

    @Autowired
    private ProduceMoldingMonitorDao produceMoldingMonitorDao;

    @Autowired
    private CraftModelDao craftModelDao;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private CardParamsDao cardParamsDao;

    @Autowired
    private LogisticsPickingDao logisticsPickingDao;

    @Autowired
    private ProduceFeedingDao produceFeedingDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private QualityFirstendCheckDao qualityFirstendCheckDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private InjectionMoldingDao injectionMoldingDao;

    @Autowired
    private MouldUseRecordDao mouldUseRecordDao;
    @Autowired
    private CardLogDao cardLogDao;

    @Override
    @Transactional
    public Result startDebug(ProduceMoldingMonitor produceMoldingMonitor) {
        Integer flag = 0;
        String productOrder = produceMoldingMonitor.getProductOrder();
        if (CheckUtils.isNull(productOrder)) {
            return ResultUtils.error("没有生产指令");
        }
        ProduceMoldingMonitor moldingMonitor = produceMoldingMonitorDao.getMoldingMonitor(productOrder);
        //新增
        if (moldingMonitor == null) {
            produceMoldingMonitor.setDebugStartUser(headerUtil.loginUser().getName());
            produceMoldingMonitor.setDebugStartTime(DateUtils.getTodayTime());
            produceMoldingMonitor.setStatus(0);
            flag = produceMoldingMonitorDao.insert(produceMoldingMonitor);
        } else {//修改
            moldingMonitor.setDebugStartUser(headerUtil.loginUser().getName());
            moldingMonitor.setDebugStartTime(DateUtils.getTodayTime());
            //状态不是2的时候,才改变开始调试的状态
            if(!moldingMonitor.getStatus().equals(2)){
                moldingMonitor.setStatus(0);
            }
            flag = produceMoldingMonitorDao.update(moldingMonitor);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    @Transactional
    public Result debug(ProduceMoldingMonitor produceMoldingMonitor) {
        Integer flag = 0;
        String productOrder = produceMoldingMonitor.getProductOrder();
        String productCode = produceMoldingMonitor.getProductCode();
        if (CheckUtils.isNull(productOrder)) {
            return ResultUtils.error("没有生产指令");
        }
        ProduceMoldingMonitor moldingMonitor = produceMoldingMonitorDao.getMoldingMonitor(productOrder);
        //moldingMonitor不为空,而且状态不是2(已经开始生产过的数据不再捞数据赋值)
        if (moldingMonitor != null && !moldingMonitor.getStatus().equals(2)) {
            //根据生产指令,模具编码获取数据,赋值给ProduceMoldingMonitor对象
            setMoldingMonitor(produceMoldingMonitor, productOrder, productCode);
        }
        produceMoldingMonitor.setFirstDebugUser(headerUtil.loginUser().getName());
        produceMoldingMonitor.setFirstDebugTime(DateUtils.getTodayTime());
        //状态不是2的时候,才改变调试完成的状态
        if(!moldingMonitor.getStatus().equals(2)){
            produceMoldingMonitor.setStatus(1);
        }
        flag = produceMoldingMonitorDao.update(produceMoldingMonitor);
        //调试完给首末件管理新增数据
        if (flag > 0) {
            QualityFirstendCheck check = new QualityFirstendCheck();
            check.setCheckNo(qualityFirstendCheckDao.findCode(CodeEnum.QUALITY_01.getCode()));
            check.setProductDevicesId(produceMoldingMonitor.getProductDevicesId());
            check.setProductCode(produceMoldingMonitor.getProductOrder());
            check.setProductId(produceMoldingMonitor.getProductId());
            check.setCreateUser(headerUtil.loginUser().getId());
            check.setCheckType(0);
            flag = qualityFirstendCheckDao.save(check);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result startProduct(ProduceMoldingMonitor produceMoldingMonitor) {
        Integer flag = 0;
        String productOrder = produceMoldingMonitor.getProductOrder();
        String productCode = produceMoldingMonitor.getProductCode();
        if (CheckUtils.isNull(productOrder)) {
            return ResultUtils.error("没有生产指令");
        }
        ProduceMoldingMonitor moldingMonitor = produceMoldingMonitorDao.getMoldingMonitor(productOrder);
        //moldingMonitor为空,则新增
        if (moldingMonitor == null) {
            //根据生产指令,模具编码获取数据,赋值给ProduceMoldingMonitor对象
            setMoldingMonitor(produceMoldingMonitor, productOrder, productCode);
            produceMoldingMonitor.setProductStartTime(DateUtils.getTodayTime());
            produceMoldingMonitor.setProductStartUser(headerUtil.loginUser().getName());
            produceMoldingMonitor.setStatus(2);
            flag = produceMoldingMonitorDao.insert(produceMoldingMonitor);
        } else {//修改
            moldingMonitor.setProductStartTime(DateUtils.getTodayTime());
            moldingMonitor.setProductStartUser(headerUtil.loginUser().getName());
            moldingMonitor.setStatus(2);
            flag = produceMoldingMonitorDao.update(moldingMonitor);
        }
        //开始生产的时候修改 注塑排产计划 的状态和时间
        InjectionMolding injectionMolding = injectionMoldingDao.selectById(produceMoldingMonitor.getPlanMoldingId());
        if (injectionMolding != null) {
            injectionMolding.setStatus(1);
            injectionMolding.setActualStart(DateUtils.getTodayTime());
            injectionMoldingDao.updateStatus(injectionMolding);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result accomplishProduct(ProduceMoldingMonitor produceMoldingMonitor) {
        Integer flag = 0;
        String productOrder = produceMoldingMonitor.getProductOrder();
        if (CheckUtils.isNull(productOrder)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        ProduceMoldingMonitor monitor = produceMoldingMonitorDao.getMoldingMonitor(productOrder);
        if (monitor != null) {
            monitor.setProductEndTime(DateUtils.getTodayTime());
            monitor.setProductEndUser(headerUtil.loginUser().getName());
            monitor.setStatus(3);
            //得到设备编码
            String deviceCode = monitor.getProductDevicesCode();
            //根据设备编码查询清洗的数据
            Device device = deviceDao.findDevice(deviceCode);
            if (device != null) {
                monitor.setActuaProductCycle(device.getParamKey());
            }
            flag = produceMoldingMonitorDao.update(monitor);
        }

        if(flag > 0){
            //完成生产的时候修改 注塑排产计划 的状态和时间
            InjectionMolding injectionMolding = injectionMoldingDao.selectById(produceMoldingMonitor.getPlanMoldingId());
            if (injectionMolding != null) {
                injectionMolding.setStatus(4);
                injectionMolding.setActualEnd(DateUtils.getTodayTime());
                injectionMoldingDao.updateStatus(injectionMolding);
                //完成后给插入一条下模任务
                MouldUseRecord mouldUseRecord = new MouldUseRecord();
                mouldUseRecord.setMouldId(injectionMolding.getMouldId());
                mouldUseRecord.setCreateTime(DateUtils.getTodayTime());
                mouldUseRecord.setOpreate(1);
                mouldUseRecord.setStatus(0);
                mouldUseRecord.setTaskStatus(0);
                mouldUseRecord.setProductCode(injectionMolding.getProductCode());
                mouldUseRecord.setMouldDevicesId(injectionMolding.getMouldId());
                mouldUseRecord.setProductDevicesId(injectionMolding.getProductDevicesId());
                mouldUseRecord.setInjectionMoldingId(injectionMolding.getId());
                mouldUseRecordDao.insert(mouldUseRecord);
            }
            //完成生产后判断该设备、零件使用的工艺卡是否是临时变更的工艺卡,如果是就变回永久变更的工艺卡
            CardParams cardParams = cardParamsDao.findByDeviceProduct(produceMoldingMonitor.getProductDevicesId(), produceMoldingMonitor.getProductCode());
            if(cardParams.getUpdateType() == 0){ //判断是否是临时变更
                CardLog cardLog = cardLogDao.findIngByParamsId(cardParams.getId(),0);//查询该工艺卡最新的一次记录
                //将工艺卡参数值变回之前永久变更的数据,将变更类型变回永久变更
                cardParams.setCraftData(cardLog.getCraftPreData());
                cardParams.setUpdateType(1);
                cardParamsDao.update(cardParams);
            }
        }
        //完成后给首末件管理新增数据
        if (flag > 0) {
            QualityFirstendCheck check = new QualityFirstendCheck();
            check.setCheckNo(qualityFirstendCheckDao.findCode(CodeEnum.QUALITY_03.getCode()));
            check.setProductDevicesId(produceMoldingMonitor.getProductDevicesId());
            check.setProductCode(produceMoldingMonitor.getProductOrder());
            check.setProductId(produceMoldingMonitor.getProductId());
            check.setCreateUser(headerUtil.loginUser().getId());
            check.setCheckType(2);
            flag = qualityFirstendCheckDao.save(check);
        }

        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    private void setMoldingMonitor(ProduceMoldingMonitor produceMoldingMonitor, String productOrder, String productCode) {
        //根据生产指令获取模具数据,赋值
        InjectionMolding mouldDevices = injectionMoldingDao.findMouldDevices(productOrder);
        if (mouldDevices != null) {
            //模具code
            produceMoldingMonitor.setMouldCode(mouldDevices.getMouldCode());
            //模具id
            produceMoldingMonitor.setMouldId(mouldDevices.getMouldId());
            //模具名称
            produceMoldingMonitor.setMouldName(mouldDevices.getMouldName());
            Integer userId = mouldDevices.getCreateUser();
            //上模人员
            produceMoldingMonitor.setMouldUpUser(e2CServicesUtil.findUserNameById(String.valueOf(userId)));
            //上模时间
            produceMoldingMonitor.setMouldUpTime(mouldDevices.getCreateTime());
        }
        //根据产品code,设备id 获取工艺卡和工艺模型数据
        CraftModel craftModel = craftModelDao.findProductCodeAndDevices(productCode,produceMoldingMonitor.getProductDevicesId());
        if (craftModel != null) {
            //工艺卡模型id
            produceMoldingMonitor.setCraftModelId(craftModel.getId());
            //计划生产周期
            produceMoldingMonitor.setPlanProductCycle(String.valueOf(craftModel.getBeat()));
            //工艺卡id
            produceMoldingMonitor.setCraftCardId(craftModel.getCardId());
            //工艺卡code
            produceMoldingMonitor.setCraftCardName(craftModel.getCardCode());
            //工艺卡参数(图片)
            produceMoldingMonitor.setCraftCardParams(craftModel.getPicture());
        }
        //根据生产指令获取领料人
        LogisticsPicking logisticsPicking = logisticsPickingDao.getLogisticsPicking(productOrder);
        if (logisticsPicking != null) {
            produceMoldingMonitor.setPickTime(logisticsPicking.getCreateTime());
            Integer userId = logisticsPicking.getCreateUser();
            produceMoldingMonitor.setPickUser(e2CServicesUtil.findUserNameById(String.valueOf(userId)));
        }
//        //根据生产指令获取投料人
//        ProduceFeeding produceFeeding = produceFeedingDao.getProduceFeeding(productOrder);
//        if (produceFeeding != null) {
//            produceMoldingMonitor.setFeedTime(produceFeeding.getCreateTime());
//            Integer userId = produceFeeding.getCreateUser();
//            produceMoldingMonitor.setFeedUser(e2CServicesUtil.findUserNameById(String.valueOf(userId)));
//        }
    }



}

