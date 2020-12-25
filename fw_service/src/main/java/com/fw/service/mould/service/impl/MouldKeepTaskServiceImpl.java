package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.*;
import com.fw.entity.plan.InjectionMolding;
import com.fw.enums.ResultEnum;
import com.fw.exceptions.MyException;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.*;
import com.fw.service.mould.service.MouldKeepTaskService;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.GenerateDateUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 模具任务下发 服务实现类
 *
 * @author xkliu
 * @date 2020/10/28
 */
@Service
public class MouldKeepTaskServiceImpl implements MouldKeepTaskService {

    @Autowired
    private MouldKeepTaskDao mouldKeepTaskDao;

    @Autowired
    private MouldKeepTeamDao mouldKeepTeamDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private JbpmUtil jbpmUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private MouldPlanExecuteRecordDao mouldPlanExecuteRecordDao;

    @Autowired
    private MouldUseRecordDao mouldUseRecordDao;

    @Autowired
    private InjectionMoldingDao injectionMoldingDao;

    @Autowired
    private MouldHouseDao mouldHouseDao;

    @Autowired
    private MouldDevicesDao mouldDevicesDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    @Override
    public Result findList(String keepOrder, String mouldCode, String taskStatus, Integer auditStatus, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldKeepTask> lists = mouldKeepTaskDao.findList(keepOrder, mouldCode, taskStatus, auditStatus);
        lists.forEach(item -> {
            //当任务在24小时之内未完成时为超时状态。
            if (item.getStatus().equals("0")) {
                Long hour = GenerateDateUtil.differHour(new Date(), item.getKeepTime());
                item.setIsPostpone(hour >= 24 ? "是" : "否");
            }
            //任务状态为延期执行且审批通过时,当前时间>任务生成时间+24小时+延期天数时,任务为超时
            if (item.getStatus().equals("1") && !CheckUtils.isNull(item.getCarryDay())) {
                //计算 任务生成时间+24小时+延期天数时
                String time = GenerateDateUtil.calDelayTime(item.getKeepTime(), 1, Integer.valueOf(item.getCarryDay()));
                Integer res = GenerateDateUtil.compareToTime(new Date(), time);
                item.setIsPostpone(res > 0 ? "是" : "否");
            }
            item.setPath(StringUtils.replace(item.getPath(), attachmentPath, "/attach/"));
        });
        PageInfo<MouldKeepTask> keepTasksPage = new PageInfo<>(lists);
        return ResultUtils.success(keepTasksPage);

    }

    @Override
    @Transactional
    public Result postPoned(String ids, String carryDay, String type, String nonExecution) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids) || CheckUtils.isNull(type)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        User user = headerUtil.loginUser();
        List<String> lists = Arrays.asList(ids.split(","));
        for (String str : lists) {
            MouldKeepTask keepTask = mouldKeepTaskDao.getKeepTask(Integer.valueOf(str));
            if (keepTask != null) {
                //延期操作/强制关闭
                //任务状态为未执行时,可进行延期执行操作
                if (keepTask.getStatus().equals("0")) {
                    //keepTask.setStatus(type);
                    //延期操作的时候设置延期天数
                    if (type.equals("1")) {
                        keepTask.setCarryDay(carryDay);
                        keepTask.setNonExecutCause("延期" + carryDay + "天");
                        //延期保养的formcode
                        keepTask.setFormCode("mouldKeepRelay");
                    } else {
                        //强制关闭的formcode
                        keepTask.setFormCode("mouldKeepClose");
                        //强制关闭的时候未执行原因描述置空
                        keepTask.setNonExecutCause("");
                    }
                    keepTask.setDelayUser(user.getName());
                    keepTask.setDelayTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                    keepTask.setNonExecution(nonExecution);
                    flag = mouldKeepTaskDao.update(keepTask);
                }
                //审核结果不为空的时候才有跟审核有关的业务
                if (keepTask.getAuditResult() != null) {
                    //任务状态为延期执行且审批状态为审批通过时,可再次延期执行关闭/强制关闭
                    if (keepTask.getStatus().equals("1") && keepTask.getAuditResult().equals(1)) {
                        if (type.equals("1")) {
                            keepTask.setCarryDay(carryDay);
                            keepTask.setNonExecutCause("延期" + carryDay + "天");
                        } else {
                            keepTask.setCarryDay("");
                            keepTask.setNonExecutCause("");
                        }
                        keepTask.setDelayUser(user.getName());
                        keepTask.setDelayTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                        keepTask.setNonExecution(nonExecution);
                        flag = mouldKeepTaskDao.update(keepTask);
                    }
                    //任务状态为延期执行、强制关闭且审批状态为审批不通过时,可再次进行延期执行/强制关闭
                    if ((keepTask.getStatus().equals("1") || keepTask.getStatus().equals("2"))
                            && keepTask.getAuditResult().equals(2)) {
                        if (type.equals("1")) {
                            keepTask.setCarryDay(carryDay);
                            keepTask.setNonExecutCause("延期" + carryDay + "天");
                        } else {
                            keepTask.setCarryDay("");
                            keepTask.setNonExecutCause("");
                        }
                        keepTask.setDelayUser(user.getName());
                        keepTask.setDelayTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                        keepTask.setNonExecution(nonExecution);
                        flag = mouldKeepTaskDao.update(keepTask);
                    }
                }

                if (flag > 0) {
                    //开启审核
                    jbpmUtil.startExecution(keepTask.getExecutionId(), String.valueOf(keepTask.getId()),
                            user.getId(), keepTask.getFormCode());
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findHistoryList(String keepOrder, String mouldCode, String executeUserName, String executeTime, String taskStatus, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        Map<String, String> userParams = new HashMap<String, String>();
        userParams.put("name", executeUserName);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), userParams);
        List<Integer> userIds = null;
        if (!CheckUtils.isNull(executeUserName)) {
            if (userList == null || userList.size() == 0) {
                return ResultUtils.success(new PageInfo<>());
            } else {
                userIds = userList.stream().map(User::getId).collect(Collectors.toList());
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldKeepTask> lists = mouldKeepTaskDao.findHistoryList(keepOrder, mouldCode, userIds, executeTime, taskStatus);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //执行人名字
                    if (!CheckUtils.isNull(item.getExecuteUser())) {
                        if (item.getExecuteUser().equals(vo.getId())) {
                            item.setExecuteUserName(vo.getName());
                        }
                    }
                    //审批人名字
                    if (!CheckUtils.isNull(item.getAuditUser())) {
                        if (item.getAuditUser().equals(vo.getId())) {
                            item.setAuditUserName(vo.getName());
                        }
                    }
                });
            });
        }
        PageInfo<MouldKeepTask> keepTasksPage = new PageInfo<>(lists);
        return ResultUtils.success(keepTasksPage);
    }

    @Override
    public Result getUnfinishedTask(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepTask keepTask = mouldKeepTaskDao.getUnfinishedTask(id);
        if (keepTask == null) {
            return ResultUtils.error(ResultEnum.NULL_DATA);
        }
        List<MouldKeepItem> keepItems = keepTask.getMouldKeepItem();
        if (!CollectionUtils.isEmpty(keepItems)) {
            keepItems.forEach(item -> {
                String path = StringUtils.replace(item.getWebPath(), attachmentPath, "/attach/");
                item.setWebPath(path);
            });
        }
        return ResultUtils.success(keepTask);
    }

    @Override
    public Result getMouldTask(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<MouldKeepItem> keepItem = mouldKeepTaskDao.getMouldTask(id);
        return ResultUtils.success(keepItem);
    }

    @Override
    @Transactional
    public Result explainTask(MouldKeepTask mouldKeepTask) {
        Integer flag = 0;
        if (CheckUtils.isNull(mouldKeepTask.getId())) {
            return ResultUtils.failure();
        }
        User user = headerUtil.loginUser();
        if (user == null) {
            return ResultUtils.failure();
        }
        mouldKeepTask.setExecuteUser(user.getId());
        //mouldKeepTask.setStatus("3");
        //3是已执行状态生成执行时间
        if(mouldKeepTask.getStatus().equals("3")){
            mouldKeepTask.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        }
        flag = mouldKeepTaskDao.update(mouldKeepTask);
        if (flag > 0) {
            List<MouldPlanExecuteRecord> planExecuteRecords = mouldKeepTask.getPlanExecuteRecords();
            if (!CollectionUtils.isEmpty(planExecuteRecords)) {
                flag = mouldPlanExecuteRecordDao.insertBatch(planExecuteRecords);
            }

            if(mouldKeepTask.getStatus().equals("3") && mouldKeepTask.getKeepType() != null){
                if(mouldKeepTask.getKeepType() == 2){//判断是否为模具产前保养任务
                    //查询保养任务关联的生产指令
                    if(mouldKeepTask.getInjectionMoldingId() != null){
                        InjectionMolding injectionMolding = injectionMoldingDao.findById(mouldKeepTask.getInjectionMoldingId());
                        //插入一条模具上模任务
                        MouldUseRecord mouldUseRecord = new MouldUseRecord();
                        mouldUseRecord.setMouldId(Integer.parseInt(mouldKeepTask.getMouldId()));
                        mouldUseRecord.setCreateTime(DateUtils.getTodayTime());
                        mouldUseRecord.setOpreate(0);
                        mouldUseRecord.setStatus(0);
                        mouldUseRecord.setTaskStatus(0);
                        mouldUseRecord.setProductCode(injectionMolding.getProductCode());
                        mouldUseRecord.setMouldDevicesId(injectionMolding.getMouldId());
                        mouldUseRecord.setProductDevicesId(injectionMolding.getProductDevicesId());
                        mouldUseRecord.setInjectionMoldingId(injectionMolding.getId());
                        mouldUseRecordDao.insert(mouldUseRecord);
                    }
                }else if(mouldKeepTask.getKeepType() == 4){//判断是否为模具产后保养任务
                    int mouldId = Integer.parseInt(mouldKeepTask.getMouldId());
                    MouldDevices mouldDevices = mouldDevicesDao.findMouldDevicesById(mouldId);
                    //插入一条模具入库任务
                    MouldHouse mouldHouse = new MouldHouse();
                    mouldHouse.setCreateTime(DateUtils.getTodayTime());
                    mouldHouse.setHouseNo(mouldHouseDao.findCode(CodeEnum.MOULD_05.getCode()));
                    mouldHouse.setOperate(0);
                    mouldHouse.setStatus(0);
                    mouldHouse.setMouldId(mouldId);
                    mouldHouse.setInjectionMoldingId(mouldKeepTask.getInjectionMoldingId());
                    mouldHouse.setNowLocationId(mouldDevices.getNowLocationId());
                    mouldHouseDao.insert(mouldHouse);
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getMouldKeepTask(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepTask mouldKeepTask = mouldKeepTaskDao.selectById(id);
        return ResultUtils.success(mouldKeepTask);
    }

    @Override
    public Result postPonedPass(Integer id) {
        Integer flag = 0;
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepTask keepTask = mouldKeepTaskDao.getKeepTask(id);
        if (keepTask != null) {
            keepTask.setStatus("1");
            flag = mouldKeepTaskDao.update(keepTask);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result closedPass(Integer id) {
        Integer flag = 0;
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepTask keepTask = mouldKeepTaskDao.getKeepTask(id);
        if (keepTask != null) {
            keepTask.setStatus("2");
            flag = mouldKeepTaskDao.update(keepTask);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Integer issuedTask(Integer keepTpye,Integer mouldId,Integer injectionMoldingId) {
        Integer flag = 0;
        if(CheckUtils.isNull(keepTpye) || CheckUtils.isNull(mouldId)){
            return flag;
        }
        MouldKeepTeam keepTeam = mouldKeepTeamDao.getKeepTeamByType(keepTpye);
        if(keepTeam == null){
            throw new MyException("500","无产前保养的数据");
        }
        String keepOrder = mouldKeepTaskDao.findCode(CodeEnum.MOULD_01.getCode());
        MouldKeepTask keepTask = new MouldKeepTask("0", null, keepOrder,DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"),String.valueOf(mouldId));
        keepTask.setKeepType(keepTpye);
        keepTask.setInjectionMoldingId(injectionMoldingId);
        return mouldKeepTaskDao.insert(keepTask);
    }
}
