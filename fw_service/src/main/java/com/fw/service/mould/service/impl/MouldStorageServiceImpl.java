package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldSpareIn;
import com.fw.entity.mould.MouldSpareOut;
import com.fw.entity.mould.MouldStorageInfo;
import com.fw.entity.mould.MouldStorageOutInDetail;
import com.fw.enums.ResultEnum;
import com.fw.enums.SystemEnums;
import com.fw.exceptions.MyException;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.MouldStorageDao;
import com.fw.service.mould.service.MouldStorageService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.OAuth2Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description:模具备品备件出入库管理
 * @Author: fchai
 * @Date: 2020/10/26 15:22
 */
@Service
@Transactional
public class MouldStorageServiceImpl implements MouldStorageService {

    @Autowired
    private MouldStorageDao mouldStorageDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;


    @Override
    public Result insertSpareInfo(MouldStorageInfo mouldStorageInfo) {

        int flag = 0;
        if (CheckUtils.isNull(mouldStorageInfo.getId())) {
            if(mouldStorageDao.selectSpareByCodeAndName(mouldStorageInfo.getCode(),mouldStorageInfo.getName()) > 0 ){
                return ResultUtils.error("备品备件编码或名称已存在！！！");
            }
            mouldStorageInfo.setCreateUser(authUserUtil.userId());
            flag = mouldStorageDao.insertSpareInfo(mouldStorageInfo);
        } else {
            MouldStorageInfo ms = mouldStorageDao.findStorageById(mouldStorageInfo.getId());
            if (ms == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            List<MouldStorageInfo> storageList = mouldStorageDao.findStorageByNotInId(mouldStorageInfo.getId(), mouldStorageInfo.getName(),mouldStorageInfo.getCode());
            if(!CollectionUtils.isEmpty(storageList) && storageList.size() > 0){
                 return ResultUtils.error("备品备件编码或名称已存在！！！");
            }
            mouldStorageInfo.setUpdateUser(authUserUtil.userId());
            flag = mouldStorageDao.updateSpareInfo(mouldStorageInfo);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result deleteSpareInfo(String ids) {
        int flag = 0;
        if (!CheckUtils.isNull(ids)) {
            flag = mouldStorageDao.deleteSpareInfo(Arrays.asList(ids.split(",")));
        } else {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findListSpareInfo(String code, String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldStorageInfo> listSpareInfo = mouldStorageDao.findListSpareInfo(code, name);
        PageInfo<MouldStorageInfo> devicesRepairPage = new PageInfo<>(listSpareInfo);
        return ResultUtils.success(devicesRepairPage);
    }


    /**
     * 模具入库
     *
     * @param mouldSpareIn
     * @return
     */
    @Override
    public Result mouldIn(MouldSpareIn mouldSpareIn) {
        mouldSpareIn.setCreateUser(authUserUtil.userId());
        //订单号
        //mouldSpareIn.setOrderNo("FW" + DateUtils.getNowTime());
        mouldSpareIn.setInNo(mouldStorageDao.findInCode(CodeEnum.MOULD_06.getCode()));
        //创建入库单
        Integer inCount = mouldStorageDao.insertIn(mouldSpareIn);
        if (inCount > 0) {
            List<MouldStorageOutInDetail> storageOutInDetailList = mouldSpareIn.getStorageOutInDetailList();
            if (!CollectionUtils.isEmpty(storageOutInDetailList)) {
                storageOutInDetailList.forEach(item -> {
                    item.setOutIn(mouldSpareIn.getId());
                    item.setOperate(0); //0入库 1出库
                });
                //插入入库明细
                Integer detailCount = mouldStorageDao.insertInOrOutDetail(storageOutInDetailList);
                if (detailCount > 0) {
                    storageOutInDetailList.forEach(item -> {
                        //更新库存数量
                        mouldStorageDao.updateIn(item.getUseCount(), item.getDevicesSpareId());
                    });
                }
            }
            return ResultUtils.success();
        }

        return ResultUtils.error("模具备品备件入库失败！！");
    }

    /**
     * 模具出库
     *
     * @param mouldSpareOut
     * @return
     */
    @Override
    public Result mouldOut(MouldSpareOut mouldSpareOut) {
        mouldSpareOut.setCreateUser(authUserUtil.userId());
        //订单号
       // mouldSpareOut.setOrderNo("FW" + DateUtils.getNowTime());
        mouldSpareOut.setOutNo(mouldStorageDao.findOutCode(CodeEnum.MOULD_05.getCode()));
        //创建出库单
        Integer outCount = mouldStorageDao.insertOut(mouldSpareOut);
        if (outCount > 0) {
            List<MouldStorageOutInDetail> storageOutInDetailList = mouldSpareOut.getStorageOutInDetailList();

            //查询库存数量是否小于出库数量
            if (!CollectionUtils.isEmpty(storageOutInDetailList)) {
                storageOutInDetailList.forEach(item -> {
                    Integer storeCount = mouldStorageDao.selectMouldStoreCount(item.getDevicesSpareId());
                    if (item.getUseCount() > storeCount) {
                        throw new MyException(SystemEnums.PARMS_ILLEGAL);
                    }
                    item.setOutIn(mouldSpareOut.getId()); //出库ID
                    item.setOperate(1);//0入库 1出库
                });
                //插入出库明细
                Integer detailCount = mouldStorageDao.insertInOrOutDetail(storageOutInDetailList);
                if (detailCount > 0) {
                    storageOutInDetailList.forEach(item -> {
                        //更新库存数量
                        mouldStorageDao.updateOut(item.getUseCount(), item.getDevicesSpareId());
                    });
                }
                return ResultUtils.success();
            }
        }
        return ResultUtils.error("模具备品备件出库失败！！");
    }

    /**
     * 模具出入库详情
     *
     * @param outInNo
     * @param operate
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Result getInOutInfo(String outInNo, Integer operate, String startDate, String endDate, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LinkedHashMap<String, Object>> linkedHashMaps = mouldStorageDao.selectInOutInfo(outInNo, operate, startDate, endDate);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CheckUtils.isNull(linkedHashMaps)) {
            for (int i = 0; i < linkedHashMaps.size(); i++) {
                for (User user : userList) {
                    if (user.getId() == Integer.parseInt(linkedHashMaps.get(i).get("createUser").toString())) {
                        linkedHashMaps.get(i).put("createUserName", user.getName());
                    }
                }
            }
        }
        PageInfo<LinkedHashMap<String, Object>> devicesRepairPage = new PageInfo<>(linkedHashMaps);
        return ResultUtils.success(devicesRepairPage);
    }

    /**
     * 模具库存信息
     *
     * @param name
     * @return
     */
    @Override
    public Result findMouldSpareStoreList(String name) {
        return ResultUtils.success(mouldStorageDao.findMouldSpareStoreList(name));
    }

    @Override
    public Result findMouldSpareStoreAndUse(String code) {
        return ResultUtils.success(mouldStorageDao.findMouldSpareStoreAndUse(code));
    }

    @Override
    public Result findOutInDetail(Integer id, Integer operate) {

        //operate 0入1出
        if (operate == 0) {
            MouldSpareIn mouldSpareIn = mouldStorageDao.selectInDetailById(id);
            List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
            if (!CollectionUtils.isEmpty(userList)) {
                for (User user : userList) {
                    if (user.getId() == mouldSpareIn.getCreateUser()) {
                        mouldSpareIn.setCreateUserName(user.getName());
                    }
                }
            }
            return ResultUtils.success(mouldSpareIn);
        } else {
            MouldSpareOut mouldSpareOut = mouldStorageDao.selectOutDetailById(id);
            List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
            if (!CollectionUtils.isEmpty(userList)) {
                for (User user : userList) {
                    if (user.getId() == mouldSpareOut.getCreateUser()) {
                        mouldSpareOut.setCreateUserName(user.getName());
                    }
                }
            }
            return ResultUtils.success(mouldSpareOut);
        }
    }
}
