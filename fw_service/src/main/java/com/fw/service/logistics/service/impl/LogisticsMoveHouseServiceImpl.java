package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsMoveHouse;
import com.fw.entity.logistics.LogisticsMoveHouseDetail;
import com.fw.entity.logistics.LogisticsStorageDetail;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsMoveHouseDao;
import com.fw.service.logistics.dao.LogisticsStorageDetailDao;
import com.fw.service.logistics.service.LogisticsMoveHouseService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-13 11:07
 **/
@Service
@Transactional
public class LogisticsMoveHouseServiceImpl implements LogisticsMoveHouseService {

    @Autowired
    private LogisticsMoveHouseDao logisticsMoveHouseDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private LogisticsStorageDetailDao logisticsStorageDetailDao;
    @Autowired
    private HeaderUtil headerUtil;


    /**
     * 任务状态 0未执行  1已执行
     */
    private static final Integer submitted = 1;


    /**
     * 创建任务
     *
     * @param logisticsMoveHouse
     * @return
     */
    @Override
    public Result save(LogisticsMoveHouse logisticsMoveHouse) {
        int flag = 0;
        if (logisticsMoveHouse != null) {
            logisticsMoveHouse.setMoveHouseNo(logisticsMoveHouseDao.findCode(CodeEnum.LOGISTICS_06.getCode()));
            logisticsMoveHouse.setCreateUser(authUserUtil.userId());
            flag = logisticsMoveHouseDao.insert(logisticsMoveHouse);
            if (flag > 0 && !CollectionUtils.isEmpty(logisticsMoveHouse.getLogisticsMoveHouseDetailsList())) {
                List<LogisticsMoveHouseDetail> logisticsMoveHouseDetailsList = logisticsMoveHouse.getLogisticsMoveHouseDetailsList();
                for(LogisticsMoveHouseDetail logisticsMoveHouseDetail :logisticsMoveHouseDetailsList){
                    logisticsMoveHouseDetail.setMoveHouseId(logisticsMoveHouse.getId());
                }
                flag = logisticsMoveHouseDao.insertDetail(logisticsMoveHouseDetailsList);
                //修改库存物料库位
                logisticsStorageDetailDao.updateMoveLocation(logisticsMoveHouseDetailsList);
            }
        }

        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 更新任务状态
     *
     * @param id
     * @return
     */
    @Override
    public Result updateMoveHouseStatus(Integer id) {
        int flag = 0;
        if (!CheckUtils.isNull(id)) {
            flag = logisticsMoveHouseDao.updateMoveHouseStatus(id, submitted, authUserUtil.userId());
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String moveHouseNo, Integer status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsMoveHouse> lists = logisticsMoveHouseDao.findList(moveHouseNo, status);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(lists) && !CollectionUtils.isEmpty(userList)) {
            for (LogisticsMoveHouse logisticsMoveHouse : lists) {
                for (User user : userList) {
                    if(logisticsMoveHouse.getCreateUser() == user.getId()){
                        logisticsMoveHouse.setCreateUserName(user.getName());
                    }
                }
            }
        }
        PageInfo<LogisticsMoveHouse> pageInfo = new PageInfo<>(lists);
        return ResultUtils.success(pageInfo);
    }



    @Override
    public Result delete(String ids) {
        int flag = 0;
        if(CheckUtils.isNull(ids)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<LogisticsMoveHouse> LogisticsMoveHouseList = logisticsMoveHouseDao.findMoveHouseById(Arrays.asList(ids.split(",")));
        if(!CollectionUtils.isEmpty(LogisticsMoveHouseList)){
            for(LogisticsMoveHouse logisticsMoveHouse :LogisticsMoveHouseList){
                if( logisticsMoveHouse != null && logisticsMoveHouse.getStatus() == 1){
                    return  ResultUtils.error("该列表中存在已完成的任务,删除失败,请重新选择！");
                }
            }
        }
        flag = logisticsMoveHouseDao.delete(Arrays.asList(ids.split(",")));
        if(flag > 0 ){
            for(String id :ids.split(",")){
                logisticsMoveHouseDao.deleteDetail(Integer.parseInt(id));
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
