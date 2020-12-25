package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.entity.device.DevicesPlanExecuteRecord;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesKeepTaskDao;
import com.fw.service.device.dao.DevicesPlanExecuteRecordDao;
import com.fw.service.device.service.DevicesKeepTaskService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.GenerateDateUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 设备保养计划下发任务 服务实现类
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Service
public class DevicesKeepTaskServiceImpl implements DevicesKeepTaskService {

    @Autowired
    private DevicesKeepTaskDao devicesKeepTaskDao;

    @Autowired
    private DevicesPlanExecuteRecordDao devicesPlanExecuteRecordDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private JbpmUtil jbpmUtil;

    @Value("${attachment.path}")
    private String attachmentPath;


    @Override
    public Result findList(String keepOrder, String devicesCode, String taskStatus, Integer auditStatus, Integer pageNum, Integer pageSize) {
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
        List<DevicesKeepTask> lists = devicesKeepTaskDao.findList(keepOrder, productDevicesIds, taskStatus, auditStatus);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                //生产设备
               /* if (!CheckUtils.isNull(item.getProductDevicesIds()) && !CollectionUtils.isEmpty(productList)) {
                    List<String> productName = new ArrayList<>();
                    for (String productId : item.getProductDevicesIds().split(",")) {
                        productList.forEach(vo -> {
                            if (productId.equals(vo.getId())) {
                                productName.add(vo.getName());
                            }
                        });
                    }
                    item.setDevicesName(StringUtils.join(productName.toArray(), ","));
                }*/

                productList.forEach(vo -> {
                    if(!CheckUtils.isNull(item.getDevicesId()) && !CheckUtils.isNull(vo.getId())){
                        if (item.getDevicesId().equals(vo.getId())) {
                            item.setDevicesName(vo.getName());
                        }
                    }
                });

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
        }
        PageInfo<DevicesKeepTask> keepTasksPage = new PageInfo<>(lists);
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
            DevicesKeepTask keepTask = devicesKeepTaskDao.getKeepTask(Integer.valueOf(str));
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
                        keepTask.setFormCode("devicesKeepRelay");
                    } else {
                        //强制关闭的formcode
                        keepTask.setFormCode("devicesKeepClose");
                        //强制关闭的时候未执行原因描述置空
                        keepTask.setNonExecutCause("");
                    }
                    keepTask.setDelayUser(user.getName());
                    keepTask.setDelayTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                    keepTask.setNonExecution(nonExecution);
                    flag = devicesKeepTaskDao.update(keepTask);
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
                        flag = devicesKeepTaskDao.update(keepTask);
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
                        flag = devicesKeepTaskDao.update(keepTask);
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
    public Result findHistoryList(String keepOrder, String devicesCode, String executeUserName, String executeTime, String taskStatus, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //查询E2C生产设备
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> userParams = new HashMap<String, String>();
        params.put("name", devicesCode);
        userParams.put("name", executeUserName);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(), params);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), userParams);
        List<String> productDevicesIds = null;
        List<Integer> userIds = null;
        if (!CheckUtils.isNull(devicesCode)) {
            //如果设备编号查询条件不为空，根据设备编号查询结果为空，则直接返回空，如果不为空，则根据设备id进行数据查询
            if (productDevices == null || productDevices.size() == 0) {
                return ResultUtils.success(new PageInfo<>());
            } else {
                productDevicesIds = productDevices.stream().map(DevicesItemVo::getId).collect(Collectors.toList());
            }
        }
        if (!CheckUtils.isNull(executeUserName)) {
            if (userList == null || userList.size() == 0) {
                return ResultUtils.success(new PageInfo<>());
            } else {
                userIds = userList.stream().map(User::getId).collect(Collectors.toList());
            }
        }
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesKeepTask> lists = devicesKeepTaskDao.findHistoryList(keepOrder, productDevicesIds, userIds, executeTime, taskStatus);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                //生产设备
               /* if (!CheckUtils.isNull(item.getProductDevicesIds()) && !CollectionUtils.isEmpty(productList)) {
                    List<String> productName = new ArrayList<>();
                    for (String productId : item.getProductDevicesIds().split(",")) {
                        productList.forEach(vo -> {
                            if (productId.equals(vo.getId())) {
                                productName.add(vo.getName());
                            }
                        });
                    }
                    item.setDevicesName(StringUtils.join(productName.toArray(), ","));
                }*/

                productList.forEach(vo -> {
                    if(!CheckUtils.isNull(item.getDevicesId()) && !CheckUtils.isNull(vo.getId())){
                        if (item.getDevicesId().equals(vo.getId())) {
                            item.setDevicesName(vo.getName());
                        }
                    }
                });

                userList.forEach(vo -> {
                    //审批人名字
                    if (!CheckUtils.isNull(item.getAuditUser())) {
                        if (item.getAuditUser().equals(vo.getId())) {
                            item.setAuditUserName(vo.getName());
                        }
                    }
                    //执行人名字
                    if (!CheckUtils.isNull(item.getExecuteUser())) {
                        if (item.getExecuteUser().equals(vo.getId())) {
                            item.setExecuteUserName(vo.getName());
                        }
                    }
                });
            });
        }
        PageInfo<DevicesKeepTask> keepTasksPage = new PageInfo<>(lists);
        return ResultUtils.success(keepTasksPage);
    }

    @Override
    public Result getDevicesTask(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<DevicesKeepItem> keepItem = devicesKeepTaskDao.getDevicesTask(id);
        return ResultUtils.success(keepItem);
    }

    @Override
    public Result explainTask(DevicesKeepTask devicesKeepTask) {
        Integer flag = 0;
        if (CheckUtils.isNull(devicesKeepTask.getId())) {
            return ResultUtils.failure();
        }
        User user = headerUtil.loginUser();
        if (CheckUtils.isNull(user)) {
            return ResultUtils.failure();
        }
        devicesKeepTask.setExecuteUser(user.getId());
        //devicesKeepTask.setStatus("3");
        //3是已执行状态生成执行时间
        if(devicesKeepTask.getStatus().equals("3")){
            devicesKeepTask.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        }
        flag = devicesKeepTaskDao.update(devicesKeepTask);
        if (flag > 0) {
            List<DevicesPlanExecuteRecord> planExecuteRecords = devicesKeepTask.getPlanExecuteRecords();
            if (!CollectionUtils.isEmpty(planExecuteRecords)) {
                flag = devicesPlanExecuteRecordDao.insertBatch(planExecuteRecords);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result getUnfinishedTask(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepTask keepTask = devicesKeepTaskDao.getUnfinishedTask(id);
        if (keepTask == null) {
            return ResultUtils.error(ResultEnum.NULL_DATA);
        }
        List<DevicesKeepItem> keepItems = keepTask.getDevicesKeepItem();
        if (!CollectionUtils.isEmpty(keepItems)) {
            keepItems.forEach(item -> {
                String path = StringUtils.replace(item.getWebPath(), attachmentPath, "/attach/");
                item.setWebPath(path);
            });
        }
        return ResultUtils.success(keepTask);
    }

    @Override
    public Result getDevicesKeepTask(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepTask devicesKeepTask = devicesKeepTaskDao.selectById(id);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(), null);
        if (CheckUtils.isNull(devicesKeepTask)) {
            return ResultUtils.failure();
        }
        if (!CheckUtils.isNull(devicesKeepTask.getProductDevicesIds())) {
            List<String> productName = new ArrayList<>();
            for (String productId : devicesKeepTask.getProductDevicesIds().split(",")) {
                productDevices.forEach(vo -> {
                    if (productId.equals(vo.getId())) {
                        productName.add(vo.getName());
                    }
                });
            }
            devicesKeepTask.setDevicesName(StringUtils.join(productName.toArray(), ","));
        }
        return ResultUtils.success(devicesKeepTask);
    }

    @Override
    public Result postPonedPass(Integer id) {
        Integer flag = 0;
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepTask keepTask = devicesKeepTaskDao.getKeepTask(id);
        if (keepTask != null) {
            keepTask.setStatus("1");
            flag = devicesKeepTaskDao.update(keepTask);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result closedPass(Integer id) {
        Integer flag = 0;
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepTask keepTask = devicesKeepTaskDao.getKeepTask(id);
        if (keepTask != null) {
            keepTask.setStatus("2");
            flag = devicesKeepTaskDao.update(keepTask);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
