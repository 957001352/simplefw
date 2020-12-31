package com.fw.service.quality.service.impl;


import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldRepairItem;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.entity.quality.QualityInspection;
import com.fw.enums.ResultEnum;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.enums.CodeEnum;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.produce.dao.ProduceMoldingMonitorDao;
import com.fw.service.quality.dao.QualityFirstendCheckDao;
import com.fw.service.quality.dao.QualityInspectResultDao;
import com.fw.service.quality.dao.QualityInspectionDao;
import com.fw.service.quality.service.QualityFirstendCheckService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.GenerateDateUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.fw.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 首末检验管理 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Service
@Transactional
@EnableScheduling
public class QualityFirstendCheckServiceImpl implements QualityFirstendCheckService {

    @Autowired
    private QualityFirstendCheckDao qualityFirstendCheckDao;
    @Autowired
    private QualityInspectResultDao qualityInspectResultDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private QualityInspectionDao qualityInspectionDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;

    @Autowired
    private JbpmUtil jbpmUtil;

    @Autowired
    private ProduceMoldingMonitorDao produceMoldingMonitorDao;

    @Autowired
    private InjectionMoldingDao injectionMoldingDao;


    @Override
    public Result findList(Integer id, String productCode, String productDevicesCode, Integer checkType, String startTime, String stopTime, Integer status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QualityFirstendCheck> qualityFirstendChecklist = qualityFirstendCheckDao.findList(id, productCode, checkType, startTime, stopTime, status);

        //查询E2C生产设备
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(qualityFirstendChecklist)) {
            for (QualityFirstendCheck qualityFirstendCheck : qualityFirstendChecklist) {
                //比对设备信息
                if (!CollectionUtils.isEmpty(productList)) {
                    for (DevicesItemVo devicesItemVo : productList) {
                        if (qualityFirstendCheck.getProductDevicesId() == Integer.parseInt(devicesItemVo.getId())) {
                            qualityFirstendCheck.setProductDevicesName(devicesItemVo.getName());
                        }

                    }
                }
                // 0 首件 1巡检 2末件 计算巡检是否超时
                if (qualityFirstendCheck.getCheckType() == 1) {
                    //获取巡检时间
                    QualityInspection qualityInspection = qualityInspectionDao.selectByProductId(qualityFirstendCheck.getProductId());
                    if (qualityInspection != null) {
                        //当前时间-生成时间>=巡检设定时间即为超时 判断是否巡检是否延期
                        if (DateUtils.getHourDifference(DateUtils.getLongCurrentTimeStamp(), DateUtils.getNowTimeByStrTime(qualityFirstendCheck.getCreateTime())) >= qualityInspection.getInspection()) {
                            qualityFirstendCheck.setIsOut(1); //0未超时 1超时
                        } else {
                            qualityFirstendCheck.setIsOut(0);
                        }
                    } else {
                        qualityFirstendCheck.setIsOut(0);
                    }

                }
                //比对用户
                if (!CollectionUtils.isEmpty(userList)) {
                    for (User user : userList) {
                        if (qualityFirstendCheck.getCreateUser() == user.getId()) {
                            qualityFirstendCheck.setCreateUserName(user.getName());
                        }
                    }

                }
            }
        }
        if (!CheckUtils.isNull(productDevicesCode)) {
            List<QualityFirstendCheck> CheckList = qualityFirstendChecklist.stream().filter
                    (e -> e.getProductDevicesName().contains(productDevicesCode)).collect(Collectors.toList());
            PageInfo<QualityFirstendCheck> listPage = new PageInfo<>(CheckList);
            return ResultUtils.success(listPage);
        }

        PageInfo<QualityFirstendCheck> listPage = new PageInfo<>(qualityFirstendChecklist);
        return ResultUtils.success(listPage);
    }

    /**
     * 首末检、巡检信息查询
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
        return ResultUtils.success(qualityInspectResultDao.findResultByDataIdAndClassify(id, 1));
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
     * 更新质检结果
     *
     * @return
     */
    @Override
    public Result update(QualityInspectResult qualityInspectResult) {
        int flag = 0;
        if (qualityInspectResult == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        flag = qualityInspectResultDao.update(qualityInspectResult);
        String checkClassify = qualityInspectResult.getCheckClassify();
        // length:0-1 首件外观 0-2首件尺寸 1-1末件外观 1-2 末检尺寸 2-1巡检外观 2-2巡检尺寸
        //checkClassify = "0-1,0-2"
        if (!CheckUtils.isNull(checkClassify) && checkClassify.split(",").length > 1) {
            //更新任务状态
            if (flag > 0) {
                QualityFirstendCheck qualityFirstendCheck = new QualityFirstendCheck();
                //0待执行 1申请延后 2 强制关闭 3 执行完成
                qualityFirstendCheck.setId(qualityInspectResult.getDataId());
                qualityFirstendCheck.setStatus(3);
                qualityFirstendCheck.setCheckTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                qualityFirstendCheckDao.update(qualityFirstendCheck);

                String name = headerUtil.loginUser().getName();
                String currentTime = DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss");
                QualityFirstendCheck firstendCheck = qualityFirstendCheckDao.findFirstendCheckByid(qualityInspectResult.getDataId());
                ProduceMoldingMonitor monitor = produceMoldingMonitorDao.getMoldingMonitor(firstendCheck.getProductCode());
                if (checkClassify.contains("0-1")) { //首件
                    //修改注塑过程监控,首检
                    if (monitor != null) {
                        monitor.setFirstCheckTime(currentTime);
                        monitor.setFirstCheckUser(name);
                        produceMoldingMonitorDao.update(monitor);
                    }
                }
                if (checkClassify.contains("1-1")) { //末件
                    if (monitor != null) {
                        monitor.setLastCheckTime(currentTime);
                        monitor.setLastCheckUser(name);
                        produceMoldingMonitorDao.update(monitor);
                    }
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    /**
     * 强制关闭
     *
     * @param qualityFirstendCheck
     * @return
     */
    @Override
    public Result coerceClose(QualityFirstendCheck qualityFirstendCheck) {
        if (qualityFirstendCheck == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //只更新不执行原因和不执行描述、不更新状态
        Integer count = qualityFirstendCheckDao.update(qualityFirstendCheck);
        if (count > 0) {
            //发起强制关闭审核
            jbpmUtil.startExecution(null, String.valueOf(qualityFirstendCheck.getId()), authUserUtil.userId(), "pollingCoerceClose");
        }
        return count > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 延期执行
     *
     * @param qualityFirstendCheck
     * @return
     */
    @Override
    public Result postponeExe(QualityFirstendCheck qualityFirstendCheck) {
        if (qualityFirstendCheck == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //只更新不执行原因和不执行描述
        Integer count = qualityFirstendCheckDao.update(qualityFirstendCheck);
        if (count > 0) {
            //发起审核
            jbpmUtil.startExecution(null, String.valueOf(qualityFirstendCheck.getId()), authUserUtil.userId(), "pollingPostponeExe");
        }
        return count > 0 ? ResultUtils.success() : ResultUtils.failure();

    }


    /**
     * 更新强制关闭审核结果
     *
     * @param id
     * @return
     */
    @Override
    public Result updateCoerceCloseStatus(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //更新任务状态 //0待执行 1申请延后 2 强制关闭 3 执行完成
        QualityFirstendCheck qualityFirstendCheck = new QualityFirstendCheck();
        qualityFirstendCheck.setId(id);
        qualityFirstendCheck.setStatus(2);
        return qualityFirstendCheckDao.update(qualityFirstendCheck) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 更新延期执行审核结果
     *
     * @param id
     * @return
     */
    @Override
    public Result updatepostponeExeStatus(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //更新任务状态 //0待执行 1申请延后 2 强制关闭 3 执行完成
        QualityFirstendCheck qualityFirstendCheck = new QualityFirstendCheck();
        qualityFirstendCheck.setId(id);
        qualityFirstendCheck.setStatus(1);
        return qualityFirstendCheckDao.update(qualityFirstendCheck) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Scheduled(cron = "0 */5 * * * ?") //每5分钟扫描计划，生成巡检任务
    public void savePollingTask() {
        //查询生产中的计划
        List<InjectionMolding> startedPlan = injectionMoldingDao.findStartedPlan();
        if (!CollectionUtils.isEmpty(startedPlan)) {
            for (InjectionMolding injectionMolding : startedPlan) {
                String planStartTime = "";//计划开始时间
                //查询巡检任务的最后一条的创建时间 ---->最后一条的创建时间 = 下次创建任务时间的依据
                QualityFirstendCheck QualityFirstendCheck = qualityFirstendCheckDao.findCheckByProductOrder(injectionMolding.getProductCode());
                if (QualityFirstendCheck != null) {
                    planStartTime = QualityFirstendCheck.getCreateTime();
                } else {
                    planStartTime = injectionMolding.getActualStart();
                }
                //查询检验规范的巡检时间
                QualityInspection qualityInspection = qualityInspectionDao.selectByProductId(injectionMolding.getProductId());
                if (qualityInspection != null && qualityInspection.getInspection() != null && qualityInspection.getInspection() > 0) {
                    //插入巡检任务(当前时间-计划生产时间  >= 检验规范的巡检时间(小时))
                    System.out.println("分钟差------------>"+ GenerateDateUtil.getMinutesSub(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"), planStartTime));
                    if (Math.abs(GenerateDateUtil.getMinutesSub(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"), planStartTime)) >= qualityInspection.getInspection() * 60) {
                        QualityFirstendCheck qualityFirstendCheck = new QualityFirstendCheck();
                        qualityFirstendCheck.setCheckNo(qualityFirstendCheckDao.findCode(CodeEnum.QUALITY_02.getCode()));
                        qualityFirstendCheck.setProductCode(injectionMolding.getProductCode());
                        qualityFirstendCheck.setProductId(injectionMolding.getProductId());
                        qualityFirstendCheck.setProductDevicesId(injectionMolding.getProductDevicesId());
                        qualityFirstendCheck.setCheckType(1); //CheckType：0 首件 1巡检 2末件
                        //qualityFirstendCheck.setCreateUser(authUserUtil.userId());
                        qualityFirstendCheckDao.save(qualityFirstendCheck);
                    }

                }
            }
        }


    }

}
