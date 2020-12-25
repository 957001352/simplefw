package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesCheckTeamDao;
import com.fw.service.device.service.DevicesCheckTeamService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class DevicesCheckTeamServiceImpl implements DevicesCheckTeamService {

    @Autowired
    private DevicesCheckTeamDao devicesCheckTeamDao;


    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Override
    @Transactional
    public Result save(DevicesCheckTeam devicesCheckTeam) {
        Integer flag = 0;
        if (devicesCheckTeamDao.isRepeatName(devicesCheckTeam)>0) {
            return ResultUtils.error("点检表单名称重复");
        }
        if(!CheckUtils.isNull(devicesCheckTeam.getProductDevicesIds())){
            if (devicesCheckTeamDao.isRepeatProduct(devicesCheckTeam.getId(),Arrays.asList(devicesCheckTeam.getProductDevicesIds().split(",")))>0) {
                return ResultUtils.error("点检设备重复");
            }
        }
        if (CheckUtils.isNull(devicesCheckTeam.getId())) {
            flag = devicesCheckTeamDao.insert(devicesCheckTeam);
        } else {
            DevicesCheckTeam checkTeam = devicesCheckTeamDao.selectById(devicesCheckTeam.getId());
            if (CheckUtils.isNull(checkTeam)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = devicesCheckTeamDao.update(devicesCheckTeam);
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
        List<String> list = Arrays.asList(ids.split(","));
        flag = devicesCheckTeamDao.delete(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, String devicesClassify, String devicesCode,String checkItem,Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //查询E2C生产设备
        Map<String, String> params=new HashMap<String, String>();
        params.put("name",devicesCode);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(),params);
        List<String> productDevicesIds=null;
        if(!CheckUtils.isNull(devicesCode)){
            //如果设备编号查询条件不为空，根据设备编号查询结果为空，则直接返回空，如果不为空，则根据设备id进行数据查询
            if(productDevices==null||productDevices.size()==0){
                return ResultUtils.success(new PageInfo<>());
            }else{
                productDevicesIds=  productDevices.stream().map(DevicesItemVo::getId).collect(Collectors.toList());
            }
        }
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        //查询E2C设备类型
        List<DevicesClassify> devicesClassifies = e2CServicesUtil.getdevicesClassify(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesCheckTeam> lists = devicesCheckTeamDao.findList(name,devicesClassify, productDevicesIds,checkItem);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                //设备类型
                if(devicesClassifies!=null&&devicesClassifies.size()>0){
                    devicesClassifies.forEach(vo -> {
                        if (!CheckUtils.isNull(item.getDevicesClassify())&&item.getDevicesClassify().equals(vo.getId())) {
                            item.setClassifyName(vo.getClassifyName());
                        }
                    });
                }
                //生产设备
                if(!CheckUtils.isNull(item.getProductDevicesIds())&&productList!=null&&productList.size()>0){
                    List<String> productName=new ArrayList<>();
                    for(String productId:item.getProductDevicesIds().split(",")){
                        productList.forEach(vo -> {
                            if (productId.equals(vo.getId())) {
                                productName.add(vo.getName());
                            }
                        });
                    }
                    item.setProductDevices(StringUtils.join(productName.toArray(), ","));
                }
            });
        }
        PageInfo<DevicesCheckTeam> CheckItemPage = new PageInfo<>(lists);
        return ResultUtils.success(CheckItemPage);
    }

    @Override
    public Result findCheckItemListByDevicve(Integer devicesId) {
        return ResultUtils.success(devicesCheckTeamDao.findCheckItemListByDevicve(devicesId));
    }
}
