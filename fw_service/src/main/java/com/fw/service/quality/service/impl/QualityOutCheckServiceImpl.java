package com.fw.service.quality.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.entity.quality.QualityOutCheck;
import com.fw.entity.quality.QualityStoreCheck;
import com.fw.enums.ResultEnum;
import com.fw.service.quality.dao.QualityInspectResultDao;
import com.fw.service.quality.dao.QualityOutCheckDao;
import com.fw.service.quality.service.QualityOutCheckService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 16:05
 **/

@Service
@Transactional
public class QualityOutCheckServiceImpl implements QualityOutCheckService {

    @Autowired
    private QualityOutCheckDao qualityOutCheckDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private QualityInspectResultDao qualityInspectResultDao;

    /**
     * 获取列表
     * @param checkNo
     * @param customer
     * @param materialCode
     * @param startTime
     * @param stopTime
     * @return
     */
    @Override
    public Result findList(String checkNo, String customer, String materialCode, String startTime, String stopTime,Integer status,Integer pageNum,Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QualityOutCheck> QualityOutCheckList = qualityOutCheckDao.findList(checkNo, customer, materialCode, startTime, stopTime,status);
        //所有用户信息
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(QualityOutCheckList)) {
            if (!CollectionUtils.isEmpty(userList)) {
                for (QualityOutCheck qualityOutCheck : QualityOutCheckList) {
                    for (User user : userList) {
                        if (qualityOutCheck.getCreateUser() == user.getId()) {
                            qualityOutCheck.setCreateUserName(user.getName());
                        }
                    }
                }
            }
        }

        PageInfo<QualityOutCheck> listPage = new PageInfo<>(QualityOutCheckList);
        return ResultUtils.success(listPage);
    }

    @Override
    public Result submit(List<QualityInspectResult> qualityInspectResultList) {

        int flag = 0;
        if(CollectionUtils.isEmpty(qualityInspectResultList)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        for(QualityInspectResult qualityInspectResult:qualityInspectResultList){
            flag += qualityInspectResultDao.save(qualityInspectResult);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    /**
     * 查询质检结果
     * @param dataId
     * @return
     */
    @Override
    public Result findResultByDataIdAndClassify(Integer dataId) {
        if(CheckUtils.isNull(dataId)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //0 来料 1首末检/巡检 2入库 3出库
        return ResultUtils.success(qualityInspectResultDao.findResultByDataIdAndClassify(dataId,3));
    }


    /**
     * 更新入库质检结果
     * @param qualityInspectResult
     * @return
     */
    @Override
    public Result update(QualityInspectResult qualityInspectResult) {
        int flag = 0;
        if(qualityInspectResult == null ){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        flag = qualityInspectResultDao.update(qualityInspectResult);
        String checkClassify = qualityInspectResult.getCheckClassify();
        if(!CheckUtils.isNull(checkClassify) && checkClassify.split(",").length > 1) {
            //更新任务状态
            if (flag > 0) {
                QualityOutCheck qualityOutCheck = new QualityOutCheck();
                qualityOutCheck.setId(qualityInspectResult.getDataId());
                qualityOutCheck.setStatus(1);//0待执行 1已执行
                qualityOutCheck.setCreateUser(authUserUtil.userId());
                qualityOutCheckDao.update(qualityOutCheck);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
