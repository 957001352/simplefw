package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.service.device.dao.DevicesExtensionDao;
import com.fw.service.device.service.DevicesExtensionService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.systemconst.Const;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DevicesExtensionServiceImpl implements DevicesExtensionService {
    @Resource
    private DevicesExtensionDao devicesExtensionDao;
    @Resource
    private E2CServicesUtil e2CServicesUtil;
    @Resource
    private HeaderUtil headerUtil;
    @Override
    public Result save(DevicesExtension devicesExtension) {
        Integer con = -1;
        //检查传入id 为空就添加 不为空为修改
        if(CheckUtils.isNull(devicesExtension.getId())){
            con = devicesExtensionDao.insert(devicesExtension);
        }else{
            con = devicesExtensionDao.update(devicesExtension);
        }
        return con >= 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String devicesCode, String classifyId, Integer status, String startTime, String endTime) {
        Map<String,String> params = new HashMap<>();
        params.put("name",devicesCode);
        params.put("classifyId",classifyId);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(),params);
        List<DevicesExtension> extensions = devicesExtensionDao.findList(status, startTime, endTime, Const.JBPM_FORM_CODE_DEVICE_SCRAP);

        //对设备进行匹配履历参数
        List<DevicesItemVo> list = new ArrayList<>();
        for (int i = 0; i < productDevices.size(); i++) {
            boolean flag = true;
            DevicesItemVo devicesItemVo = productDevices.get(i);
            for (DevicesExtension devicesExtension:extensions) {
                if(devicesItemVo.getId().equals(devicesExtension.getProductDevicesId())){
                    devicesItemVo.setDevicesExtension(devicesExtension);
                    list.add(devicesItemVo);
                    flag = false;
                }
            }
            if(flag){
                if(CheckUtils.isNull(status) && CheckUtils.isNull(startTime) && CheckUtils.isNull(endTime)){
                    list.add(devicesItemVo);
                }
            }
        }
        return ResultUtils.success(list);
    }
}
