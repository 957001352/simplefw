package com.fw.service.quality.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.entity.quality.QualityStoreCheck;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.service.ProduceReworkMonitorService;
import com.fw.service.quality.dao.QualityInspectResultDao;
import com.fw.service.quality.dao.QualityStoreCheckDao;
import com.fw.service.quality.service.QualityStoreCheckService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 10:11
 **/
@Service
@Transactional
public class QualityStoreCheckServiceImpl implements QualityStoreCheckService {

    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private QualityStoreCheckDao qualityStoreCheckDao;
    @Autowired
    private QualityInspectResultDao qualityInspectResultDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private ProduceReworkMonitorService produceReworkMonitorService;


    /**
     * 获取列表
     *
     * @param checkNo
     * @param productCode
     * @param materialCode
     * @param startTime
     * @param stopTime
     * @return
     */
    @Override
    public Result findList(String productDevicesCode,String checkNo, String productCode, String materialCode, String startTime, String stopTime,Integer status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }

        PageHelper.startPage(pageNum, pageSize);

        List<QualityStoreCheck> qualityStoreCheckList = qualityStoreCheckDao.findList(checkNo,productCode,materialCode, startTime, stopTime,status);
        //查询E2C生产设备
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        //所有用户信息
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(qualityStoreCheckList)) {
            if (!CollectionUtils.isEmpty(productList)) {
                for (QualityStoreCheck qualityStoreCheck : qualityStoreCheckList) {
                    for (DevicesItemVo devicesItemVo : productList) {
                        if (qualityStoreCheck.getProductDevicesId() == Integer.parseInt(devicesItemVo.getId())) {
                            qualityStoreCheck.setProductDevicesCode(devicesItemVo.getName());
                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(userList)) {
                for (QualityStoreCheck qualityStoreCheck : qualityStoreCheckList) {
                    for (User user : userList) {
                        if (qualityStoreCheck.getCreateUser() == user.getId()) {
                            qualityStoreCheck.setCreateUserName(user.getName());
                        }
                    }

                }
            }
        }

        if (!CheckUtils.isNull(productDevicesCode)) {
            List<QualityStoreCheck> qualityStoreList = qualityStoreCheckList.stream().filter
                    (e -> e.getProductDevicesCode().contains(productDevicesCode)).collect(Collectors.toList());
            PageInfo<QualityStoreCheck> listPage = new PageInfo<>(qualityStoreList);
            return ResultUtils.success(listPage);
        }
        PageInfo<QualityStoreCheck> listPage = new PageInfo<>(qualityStoreCheckList);
        return ResultUtils.success(listPage);
    }

    /**
     * 获取入库检外观尺寸检验信息
     *
     * @param id
     * @return
     */
    @Override
    public Result findAppearanceOrSizeInspectInfo(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        // classify:  质检结果表中类型：0 来料 1首末检/巡检 2入库 3出库
        return ResultUtils.success(qualityInspectResultDao.findResultByDataIdAndClassify(id, 2));
    }


    /**
     * 提交质检结果
     *
     * @param qualityInspectResultList
     * @return
     */
    @Override
    public Result submit(List<QualityInspectResult> qualityInspectResultList) {
        int flag = 0;
        if (CollectionUtils.isEmpty(qualityInspectResultList)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        for (QualityInspectResult qualityInspectResult : qualityInspectResultList) {
            qualityInspectResult.setUser(authUserUtil.userId());
            flag += qualityInspectResultDao.save(qualityInspectResult);

        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 更新入库质检结果
     * @param qualityInspectResult
     * @return
     */
    @Override
    public Result update(QualityInspectResult qualityInspectResult) {
        int flag = 0 ;
        if(qualityInspectResult == null ){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        flag = qualityInspectResultDao.update(qualityInspectResult);
        String checkClassify = qualityInspectResult.getCheckClassify();
        if(!CheckUtils.isNull(checkClassify) && checkClassify.split(",").length > 1){
            //修改任务状态
            if (flag > 0) {
                QualityStoreCheck qualityStoreCheck = new QualityStoreCheck();
                qualityStoreCheck.setId(qualityInspectResult.getDataId());
                qualityStoreCheck.setStatus(1); //0待执行 1已执行
                qualityStoreCheckDao.udpate(qualityStoreCheck);
            }

            //通知二次加工过程监控入库检验人、检验时间
            produceReworkMonitorService.checkUser(qualityInspectResult.getOfNo());
        }

        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
