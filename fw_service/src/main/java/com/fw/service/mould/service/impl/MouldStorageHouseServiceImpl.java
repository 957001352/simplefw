package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldStorageHouse;
import com.fw.entity.mould.MouldStorageLocation;
import com.fw.entity.mould.MouldUseRecord;
import com.fw.enums.ResultEnum;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldStorageHouseDao;
import com.fw.service.mould.dao.MouldUseRecordDao;
import com.fw.service.mould.service.MouldStorageHouseService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 模具仓库管理
 * @author lpsong
 * @since 2020-10-26
 */
@Service
public class MouldStorageHouseServiceImpl implements MouldStorageHouseService {

    @Autowired
    private MouldStorageHouseDao mouldStorageHouseDao;
    @Autowired
    private MouldDevicesDao mouldDevicesDao;

    @Override
    public Result saveStorage(MouldStorageHouse mouldStorageHouse) {
        Integer flag = 0;
        if(mouldStorageHouseDao.isRepeatStorageName(mouldStorageHouse)>0){
            return ResultUtils.error("仓库名称重复");
        }
        //id为空新增
        if (CheckUtils.isNull(mouldStorageHouse.getId())) {
            flag = mouldStorageHouseDao.insertStorage(mouldStorageHouse);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            MouldStorageHouse entrty = mouldStorageHouseDao.selectStorageById(mouldStorageHouse.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = mouldStorageHouseDao.updateStorage(mouldStorageHouse);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result deleteStorage(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        if(mouldStorageHouseDao.findStorageIsEableDelete(list)>0){
            return ResultUtils.error("仓库不为空，不能删除");
        }
        flag = mouldStorageHouseDao.deleteStorage(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findStorageList(String name,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldStorageHouse> lists = mouldStorageHouseDao.findStorageList(name);
        return ResultUtils.success( new PageInfo<>(lists));
    }


    @Override
    public Result saveLocation(MouldStorageLocation mouldStorageLocation) {
        Integer flag = 0;
        if(mouldStorageHouseDao.isRepeatLocationName(mouldStorageLocation)>0){
            return ResultUtils.error("库位名称重复");
        }
        //id为空新增
        if (CheckUtils.isNull(mouldStorageLocation.getId())) {
            flag = mouldStorageHouseDao.insertLocation(mouldStorageLocation);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            MouldStorageLocation entrty = mouldStorageHouseDao.selectLocationById(mouldStorageLocation.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = mouldStorageHouseDao.updateLocation(mouldStorageLocation);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result deleteLocation(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        if(mouldStorageHouseDao.findLocationIsEableDelete(list)>0){
            return ResultUtils.error("库位不为空，不能删除");
        }
        flag = mouldStorageHouseDao.deleteLocation(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findLocationList(Integer storageHouseId, String location, String mould, String mouldCode,
                                   Integer status, Integer isBand,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldStorageLocation> lists = mouldStorageHouseDao.findLocationList(storageHouseId,location,mould,mouldCode,status,isBand);
        return ResultUtils.success( new PageInfo<>(lists));
    }

    @Override
    public Result boundMould(MouldDevices mouldDevices) {
        return mouldDevicesDao.update(mouldDevices) >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result unbundMoule(MouldDevices mouldDevices) {
        MouldDevices entrty= mouldDevicesDao.findMouldDevicesById(mouldDevices.getId());
        if(entrty==null){
            return   ResultUtils.error("模具不存在");
        }
        //解除绑定后，设置库位为初始状态
        MouldStorageLocation mouldStorageLocation=new MouldStorageLocation();
        mouldStorageLocation.setId(entrty.getNowLocationId());
        mouldStorageLocation.setStatus(0);
        mouldStorageHouseDao.updateLocation(mouldStorageLocation);
        //将当前库位设置为旧库位
        mouldDevices.setOldLocationId(mouldDevices.getNowLocationId());
        //将当前库位置为0
        mouldDevices.setNowLocationId(0);
        return mouldDevicesDao.update(mouldDevices) >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findEmptyLocationList(String name) {
        //仓库查询
        List<MouldStorageHouse> storageList = mouldStorageHouseDao.findStorageList(null);
        List<MouldStorageHouse> resList=new ArrayList<>();
        //库位查询
        List<MouldStorageLocation> locationList = mouldStorageHouseDao.findEmptyLocationList(name);
        if(locationList!=null&&locationList.size()>0){
            //按仓库分组，比对过滤仓位为空的返回
            Map<Integer, List<MouldStorageLocation>> groupLocation =locationList.stream().collect(Collectors.groupingBy(MouldStorageLocation::getStorageHouseId,Collectors.toList()));
            storageList.forEach(store -> {
                if(groupLocation.get(store.getId())!=null){
                    store.setLocaltionList(groupLocation.get(store.getId()));
                    resList.add(store);
                }
            });
        }
        return ResultUtils.success(resList);
    }
}
