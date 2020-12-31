package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditPlan;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.mould.MouldKeepPlan;
import com.fw.entity.mould.MouldKeepTask;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditPlanDao;
import com.fw.service.audit.dao.AuditTaskDao;
import com.fw.service.config.ScheduledConf;
import com.fw.service.device.dao.DevicesKeepPlanDao;
import com.fw.service.device.dao.DevicesKeepTaskDao;
import com.fw.service.device.dao.DevicesKeepTeamDao;
import com.fw.service.device.service.DevicesKeepPlanService;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.MouldKeepPlanDao;
import com.fw.service.mould.dao.MouldKeepTaskDao;
import com.fw.service.task.ToAuditTask;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.GenerateDateUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 * 设备保养计划制定 服务实现类
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Service
@EnableScheduling
public class DevicesKeepPlanServiceImpl implements DevicesKeepPlanService {

    @Autowired
    private DevicesKeepPlanDao devicesKeepPlanDao;

    @Autowired
    private DevicesKeepTeamDao devicesKeepTeamDao;

    @Autowired
    private DevicesKeepTaskDao devicesKeepTaskDao;

    @Autowired
    private MouldKeepPlanDao mouldKeepPlanDao;

    @Autowired
    private MouldKeepTaskDao mouldKeepTaskDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private AuditPlanDao auditPlanDao;

    @Autowired
    private AuditTaskDao auditTaskDao;

    private ScheduledFuture future;

    /**
     * 项目启动时,初始化设备/模具计划定制,避免服务挂调重启后,可自动生成已经计划定制好的任务
     */
    @PostConstruct
    public void init() {
        //设备保养下发
        List<DevicesKeepPlan> keepPlans = devicesKeepPlanDao.findAllfail();
        if (!CollectionUtils.isEmpty(keepPlans)) {
            keepPlans.forEach(plan -> {
                //获取计划中要保养的设备,下发多条任务
                String ids = plan.getProductDevicesIds();
                String[] split = StringUtils.split(ids, ",");
                for (String devicesId : split) {
                    String keepOrder = devicesKeepTaskDao.findCode(CodeEnum.DEVICES_01.getCode());
                    DevicesKeepTask keepTask = new DevicesKeepTask("0", plan.getId(), keepOrder, plan.getLastTime(), devicesId);
                    devicesKeepTaskDao.insert(keepTask);
                }
                String nextLastTime = GenerateDateUtil.generateLastTime(plan.getLastTime(), null, null, plan.getKeepCycle());
                //下次保养时间
                plan.setLastTime(nextLastTime);
                plan.setStatus(1);
                devicesKeepPlanDao.update(plan);
            });
        }
        //模具保养下发
        List<MouldKeepPlan> allfail = mouldKeepPlanDao.findAllfail();
        if (!CollectionUtils.isEmpty(allfail)) {
            allfail.forEach(item -> {
                //获取计划中要保养的模具,下发多条任务
                String ids = item.getMouldDevicesIds();
                String[] split = StringUtils.split(ids, ",");
                for (String mouldId : split) {
                    String mouldKeepOrder = mouldKeepTaskDao.findCode(CodeEnum.MOULD_01.getCode());
                    MouldKeepTask keepTask = new MouldKeepTask("0", item.getId(), mouldKeepOrder, item.getLastTime(), mouldId);
                    //保养类型
                    keepTask.setKeepType(item.getKeepType());
                    mouldKeepTaskDao.insert(keepTask);
                }
                String nextLastTime = GenerateDateUtil.generateLastTime(item.getLastTime(), null, null, item.getKeepCycle());
                //下次保养时间
                item.setLastTime(nextLastTime);
                item.setStatus(1);
                mouldKeepPlanDao.update(item);
            });
        }
    }

    @Override
    @Transactional
    public Result save(DevicesKeepPlan devicesKeepPlan) {
        Integer flag = 0;
        //id为空新增设备保养计划制定
        if (CheckUtils.isNull(devicesKeepPlan.getId())) {
            //名称不为空校验是否重复
            if (!CheckUtils.isNull(devicesKeepPlan.getName())) {
                boolean res = devicesKeepPlanDao.verifyName(devicesKeepPlan.getName());
                if (res) {
                    return ResultUtils.error("保养计划定制名称重复");
                }
            }
            //本次保养计划时间 = 初次保养时间 + 频率
            String lastTime = GenerateDateUtil.generateLastTime(devicesKeepPlan.getStartTime(), null, null, devicesKeepPlan.getKeepCycle());
            devicesKeepPlan.setLastTime(lastTime);
            flag = devicesKeepPlanDao.insert(devicesKeepPlan);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            DevicesKeepPlan keepPlan = devicesKeepPlanDao.selectById(devicesKeepPlan.getId());
            if (keepPlan == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            //修改时表单名称与传过来名称不通的时候进行校验
            if (!devicesKeepPlan.getName().equals(keepPlan.getName())) {
                //校验是否有重复的表单名称
                boolean res = devicesKeepPlanDao.verifyName(devicesKeepPlan.getName());
                if (res) {
                    return ResultUtils.error("保养计划定制名称重复");
                }
            }
            //如果修改了下发定制任务的时间周期,则重新计算执行时间,修改状态为未执行
            if (!keepPlan.getStartTime().equals(devicesKeepPlan.getStartTime())
                    || !keepPlan.getNoticeTime().equals(devicesKeepPlan.getNoticeTime())
                    || !keepPlan.getKeepCycle().equals(devicesKeepPlan.getKeepCycle())) {
                String lastTime = GenerateDateUtil.generateLastTime(devicesKeepPlan.getStartTime(), null, null, devicesKeepPlan.getKeepCycle());
                devicesKeepPlan.setLastTime(lastTime);
                devicesKeepPlan.setStatus(0);
            }
            flag = devicesKeepPlanDao.update(devicesKeepPlan);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getDevicesKeepPlan(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepPlan keepPlan = devicesKeepPlanDao.selectById(id);
        return ResultUtils.success(keepPlan);
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        if (devicesKeepTaskDao.isExistPlan(lists) > 0) {
            return ResultUtils.error("已经有任务下发,不能删除");
        }
        for (String str : lists) {
            DevicesKeepPlan keepPlan = devicesKeepPlanDao.selectById(Integer.valueOf(str));
            if (keepPlan == null) {
                return ResultUtils.error("删除失败,请刷新页面");
            }
        }
        flag = devicesKeepPlanDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getAllKeepTeam() {
        return ResultUtils.success(devicesKeepTeamDao.getAllKeepTeam());
    }

    @Override
    public Result findList(String name, String keepTeamName, String devicesCode, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //查询E2C生产设备
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", devicesCode);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(), params);
        List<String> productDevicesIds = null;
        if (!CheckUtils.isNull(devicesCode)) {
            //如果设备编号查询条件不为空，根据设备编号查询结果为空，则直接返回空，如果不为空，则根据设备id进行数据查询
            if (productDevices == null || productDevices.size() == 0) {
                return ResultUtils.success(new PageInfo<>());
            } else {
                productDevicesIds = productDevices.stream().map(DevicesItemVo::getId).collect(Collectors.toList());
            }
        }
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesKeepPlan> lists = devicesKeepPlanDao.findList(name, keepTeamName, productDevicesIds);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                //生产设备
                if (!CheckUtils.isNull(item.getProductDevicesIds()) && !CollectionUtils.isEmpty(productList)) {
                    List<String> productName = new ArrayList<>();
                    for (String productId : item.getProductDevicesIds().split(",")) {
                        productList.forEach(vo -> {
                            if (productId.equals(vo.getId())) {
                                productName.add(vo.getName());
                            }
                        });
                    }
                    item.setDevicesName(StringUtils.join(productName.toArray(), ","));
                    //提前通知时间
                    String advanceTime = GenerateDateUtil.generateLastTime(item.getLastTime(), item.getKeepCycle(), null, null);
                    item.setAdvanceTime(advanceTime);
                }
            });
        }
        PageInfo<DevicesKeepPlan> keepPlanPage = new PageInfo<>(lists);
        return ResultUtils.success(keepPlanPage);
    }


    /**
     * 每天00:00执行 //0 0 0 * * ?
     */
    @Scheduled(cron = "0 0 0 * * ?") //测试每60秒执行一次,用于下发任务数据 */10 * * * * ?
    public void issuedTask() {
        //设备保养查询需要生成执行计划
        List<DevicesKeepPlan> keepPlans = devicesKeepPlanDao.findAll();
        if (!CollectionUtils.isEmpty(keepPlans)) {
            String today = DateUtils.getToday();
            keepPlans.forEach(plan -> {
                //本次保养计划时间
                String lastTime = plan.getLastTime();
                //提前通知时间
                Integer noticeTime = plan.getNoticeTime();
                if (!StringUtils.isBlank(lastTime) && !CheckUtils.isNull(noticeTime)) {
                    //下发任务时间 = 本次保养计划时间 - 提前通知时间
                    String issueTime = GenerateDateUtil.generateLastTime(lastTime, noticeTime, null, null);
                    //当天的时间和下发任务时间比较
                    if (today.equals(issueTime)) {
                        //获取计划中要保养的设备,下发多条任务
                        String ids = plan.getProductDevicesIds();
                        String[] split = StringUtils.split(ids, ",");
                        for (String devicesId : split) {
                            String keepOrder = devicesKeepTaskDao.findCode(CodeEnum.DEVICES_01.getCode());
                            DevicesKeepTask keepTask = new DevicesKeepTask("0", plan.getId(), keepOrder, lastTime, devicesId);
                            devicesKeepTaskDao.insert(keepTask);
                        }
                        //用本次计划时间 + 频率 ,再次计算下次保养计划的时间
                        String nextLastTime = GenerateDateUtil.generateLastTime(plan.getLastTime(), null, null, plan.getKeepCycle());
                        //下次保养时间
                        plan.setLastTime(nextLastTime);
                        plan.setStatus(1);
                        devicesKeepPlanDao.update(plan);
                    }
                }
            });
        }

        //保养查询需要生成执行计划
        List<MouldKeepPlan> mouldKeepPlan = mouldKeepPlanDao.findAll();
        if (!CollectionUtils.isEmpty(mouldKeepPlan)) {
            String today = DateUtils.getToday();
            mouldKeepPlan.forEach(item -> {
                //本次保养计划时间
                String lastTime = item.getLastTime();
                //提前通知时间
                Integer noticeTime = item.getNoticeTime();
                if (!StringUtils.isBlank(lastTime) && !CheckUtils.isNull(noticeTime)) {
                    //下发任务时间 = 本次保养计划时间 - 提前通知时间
                    String issueTime = GenerateDateUtil.generateLastTime(lastTime, noticeTime, null, null);
                    //当天的时间和下发任务时间比较
                    if (today.equals(issueTime)) {
                        //获取计划中要保养的模具,下发多条任务
                        String ids = item.getMouldDevicesIds();
                        String[] split = StringUtils.split(ids, ",");
                        for (String mouldId : split) {
                            String keepOrder = mouldKeepTaskDao.findCode(CodeEnum.MOULD_01.getCode());
                            MouldKeepTask keepTask = new MouldKeepTask("0", item.getId(), keepOrder, lastTime, mouldId);
                            //保养类型
                            keepTask.setKeepType(item.getKeepType());
                            mouldKeepTaskDao.insert(keepTask);
                        }
                        //用本次计划时间,周期,频率再次计算下次保养计划的时间
                        String nextLastTime = GenerateDateUtil.generateLastTime(item.getLastTime(), null, null, item.getKeepCycle());
                        //下次保养时间
                        item.setLastTime(nextLastTime);
                        item.setStatus(1);
                        mouldKeepPlanDao.update(item);
                    }
                }
            });
        }
        //分层审核根据计划,创建相应任务
        //createAuditTask();

    }

    /**
     * 定时创建审核任务
     */
    @Scheduled(cron = "0 0 23 * * ?") //每天晚上11点执行
    public void createAuditTask() {
        //查询未禁用的计划
        List<AuditPlan> auditPlanList = auditPlanDao.findListByStatus();
        if (!CollectionUtils.isEmpty(auditPlanList)) {
            for (AuditPlan auditPlan : auditPlanList) {
                //无论线程中是否有该计划，都先将清空，防止用户更新周期
                ScheduledFuture scheduledFuture = ScheduledConf.map.get("auditPlan_" + auditPlan.getId());
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                    // 查看任务是否在正常执行之前结束,正常true
                    while (!scheduledFuture.isCancelled()) {
                        scheduledFuture.cancel(true);
                    }
                    ScheduledConf.map.remove("auditPlan_" + auditPlan.getId());
                }
                //线程池传入具体任务和cron触发器  new CronTrigger()方法的参数是cron表达式
                future = threadPoolTaskScheduler.schedule(new ToAuditTask(auditPlan, auditTaskDao), new CronTrigger(auditPlan.getCron()));
                //把任务放入map中
                ScheduledConf.map.put("auditPlan_" + auditPlan.getId(), future);

            }

        }
    }

}
