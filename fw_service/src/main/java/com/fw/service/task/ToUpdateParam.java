package com.fw.service.task;

import com.fw.entity.craft.CardUpdateParam;
import com.fw.entity.craft.CollectDevice;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldHouse;
import com.fw.entity.plan.InjectionMolding;
import com.fw.service.craft.dao.CardUpdateParamsDao;
import com.fw.service.craft.dao.CollectDeviceDao;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldHouseDao;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.util.CardUtil;
import com.fw.service.util.FwConst;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableScheduling
@Slf4j
public class ToUpdateParam {
    @Autowired
    private CardUpdateParamsDao cardUpdateParamsDao;
    @Autowired
    private CollectDeviceDao collectDeviceDao;
    @Autowired
    private InjectionMoldingDao injectionMoldingDao;
    @Autowired
    private CardUtil cardUtil;
    @Autowired
    private MouldHouseDao mouldHouseDao;
    @Autowired
    private MouldDevicesDao mouldDevicesDao;
    @Scheduled(cron = "0 */3 * * * ?")
    public void saveChangeParamLog(){
        log.info("进入定时");
        //获取所有设备最新更新的数据
        List<CardUpdateParam> newLog = cardUpdateParamsDao.findNewLog();
        //获取所有设备当前采集的更新的数据
        List<CollectDevice> collectDevices = collectDeviceDao.findByParamKey(FwConst.UPDATEPARAMKEY);
        Map<String,CardUpdateParam> map = new HashMap<>();
        //将每一个设备采集的数据包装成一个对象Map
        for (CollectDevice collectDevice:collectDevices) {
            if(!map.containsKey(collectDevice.getDeviceId())){
                CardUpdateParam cardUpdateParam = new CardUpdateParam();
                cardUtil.setFieldValueByName(cardUpdateParam,collectDevice.getParamKey(),collectDevice.getParamValue());
                cardUpdateParam.setDeviceCode(collectDevice.getDeviceId());
                map.put(collectDevice.getDeviceId(),cardUpdateParam);
            }else {
                CardUpdateParam cardUpdateParam = map.get(collectDevice.getDeviceId());
                cardUtil.setFieldValueByName(cardUpdateParam,collectDevice.getParamKey(),collectDevice.getParamValue());
            }
        }
        Set<String> deviceCodes = map.keySet();
        for (String deviceCode:deviceCodes) {
            boolean flag = true;
            CardUpdateParam param = map.get(deviceCode);
            //判断获取到的参数是否为空
            if(!CheckUtils.isNull(param.getTmUpdateDateTime().replace("\"", ""))){
                for (CardUpdateParam cardUpdateParam:newLog) {
                    if(deviceCode.equals(cardUpdateParam.getDeviceCode())){
                        //判断修改的参数是否更新，如果更新了就插入一条数据
                        if(cardUpdateParam.getTmUpdateContent() != null){
                            if(!(cardUpdateParam.getTmUpdateContent().equals(param.getTmUpdateContent()) && cardUpdateParam.getTmUpdateDateTime().equals(param.getTmUpdateDateTime()))){
                                cardUpdateParamsDao.insert(param);
                            }
                        }
                        flag = false;
                    }
                }
                //如果表里没有记录设备变更参数的信息就直接插入一条记录
                if(flag){
                    cardUpdateParamsDao.insert(param);
                }
            }
        }
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void makeMouldOut(){
        //查询未来24小时内要开始的生产指令
        List<InjectionMolding> injectionMoldings = injectionMoldingDao.findStartOneDay();
        List<MouldHouse> mouldHouses = new ArrayList<>();
        injectionMoldings.forEach(injectionMolding -> {
            if(!injectionMolding.getChecked()){
                MouldDevices mouldDevices = mouldDevicesDao.findMouldDevicesById(injectionMolding.getMouldId());
                MouldHouse mouldHouse = buildMouldHouseOut();
                mouldHouse.setMouldId(injectionMolding.getMouldId());
                mouldHouse.setInjectionMoldingId(injectionMolding.getId());
                mouldHouse.setNowLocationId(mouldDevices.getNowLocationId());
                mouldHouses.add(mouldHouse);
            }
        });
        if(mouldHouses.size() > 0){
            //批量插入模具出库单
            mouldHouseDao.insertList(mouldHouses);
        }
    }

    //生成模具出库任务
    public MouldHouse buildMouldHouseOut(){
        MouldHouse mouldHouse = new MouldHouse();
        mouldHouse.setCreateTime(DateUtils.getTodayTime());
        mouldHouse.setHouseNo(mouldHouseDao.findCode(CodeEnum.MOULD_05.getCode()));
        mouldHouse.setOperate(1);
        mouldHouse.setStatus(0);
        return mouldHouse;
    }
}
