package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.domain.Tree;
import com.fw.entity.device.DevicesCheck;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsCheckHouse;
import com.fw.entity.logistics.LogisticsCheckHouseDetail;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldHouse;
import com.fw.entity.mould.MouldStorageLocation;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsCheckHouseDao;
import com.fw.service.logistics.dao.LogisticsStorageHouseDao;
import com.fw.service.logistics.service.LogisticsCheckHouseService;
import com.fw.service.mould.dao.MouldHouseDao;
import com.fw.service.mould.dao.MouldStorageHouseDao;
import com.fw.service.mould.service.MouldHouseService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.fw.utils.TreeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 盘库
 * @author lpsong
 * @since 2020-11-12
 */
@Service
public class LogisticsCheckHouseServiceImpl implements LogisticsCheckHouseService {
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private LogisticsCheckHouseDao logisticsCheckHouseDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private LogisticsStorageHouseDao logisticsStorageHouseDao;
    @Override
    public Result insert(LogisticsCheckHouse logisticsCheckHouse) {
        Integer flag = 0;
        if (CheckUtils.isNull(logisticsCheckHouse.getId())) {
            //设置编码
            logisticsCheckHouse.setHouseNo(logisticsCheckHouseDao.findCode(CodeEnum.LOGISTICS_05.getCode()));
            logisticsCheckHouse.setCreateUser(authUserUtil.userId());
            flag = logisticsCheckHouseDao.insert(logisticsCheckHouse);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result update(LogisticsCheckHouse logisticsCheckHouse) {
        Integer flag = 0;
        if (!CheckUtils.isNull(logisticsCheckHouse.getId())) {
            LogisticsCheckHouse entrty = logisticsCheckHouseDao.selectById(logisticsCheckHouse.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            logisticsCheckHouse.setExecuteUser(authUserUtil.userId());
            logisticsCheckHouse.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            if(!CheckUtils.isNull(logisticsCheckHouse.getStatus())&&logisticsCheckHouse.getStatus()==1){
                logisticsCheckHouse.setStatus(1);//已执行
            }
            flag = logisticsCheckHouseDao.update(logisticsCheckHouse);
            if(flag>0){
                List<LogisticsCheckHouseDetail> checkHouseDetailList=logisticsCheckHouse.getCheckHouseDetailList();
                if(!CollectionUtils.isEmpty(checkHouseDetailList)){
                    //先删除，后增加
                    if(!CollectionUtils.isEmpty(logisticsCheckHouseDao.findDetailList(logisticsCheckHouse.getId()))){
                        logisticsCheckHouseDao.deleteDetail(logisticsCheckHouse.getId());
                    }
                    flag=logisticsCheckHouseDao.insertDetail(checkHouseDetailList,logisticsCheckHouse.getId());
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result updateStatus(Integer id) {
        Integer flag = 0;
        if (!CheckUtils.isNull(id)) {
            flag = logisticsCheckHouseDao.updateStatus(id,2);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String houseNo, String checkTime, Integer status, Integer checkResult,Integer checkUser, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsCheckHouse> list = logisticsCheckHouseDao.findList(houseNo,checkTime, status,checkResult,checkUser);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(userList)) {
            list.forEach(item -> {
                userList.forEach(vo -> {
                    if (!CheckUtils.isNull(item.getCheckUser()) && item.getCheckUser().equals(vo.getId())) {
                        item.setCheckUserName(vo.getName());
                    }
                });
            });
        }
        PageInfo<LogisticsCheckHouse> checkHousePageInfo = new PageInfo<>(list);
        return ResultUtils.success(checkHousePageInfo);
    }

    @Override
    public Result findDetailList(Integer checkHouseId) {
        return ResultUtils.success(logisticsCheckHouseDao.findDetailList(checkHouseId));
    }

    @Override
    public Result findStoragePorductList(Integer locationId) {
        return ResultUtils.success(logisticsCheckHouseDao.findStoragePorductList(locationId));
    }

    @Override
    public Result findTreeList(String name) {
       List<LogisticsStorageHouse> storageHouseList=logisticsStorageHouseDao.findStorageList(name);
       List<LinkedHashMap> treeList=new ArrayList<>();
        if (!CollectionUtils.isEmpty(storageHouseList)){
            storageHouseList.forEach(storageHouse -> {
                LinkedHashMap tree=new LinkedHashMap();
                tree.put("id",storageHouse.getId());
                tree.put("label",storageHouse.getName());
                tree.put("pid","0");
                List<LogisticsStorageLocation> locationList=logisticsStorageHouseDao.findLocationListByHouseId(storageHouse.getId(),name);
                if (!CollectionUtils.isEmpty(locationList)){
                    List<LinkedHashMap> childList=new ArrayList<>();
                    locationList.forEach(location -> {
                        LinkedHashMap child=new LinkedHashMap();
                        child.put("id",location.getId());
                        child.put("label",location.getName());
                        child.put("pid",storageHouse.getId());
                        childList.add(child);
                    });
                    tree.put("children",childList);
                }
                treeList.add(tree);
            });
        }
        return ResultUtils.success(treeList);
    }
}
