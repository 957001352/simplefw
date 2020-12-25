package com.fw.service.device.service.impl;

import com.fw.domain.BaseFile;
import com.fw.domain.Result;
import com.fw.entity.device.*;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.device.dao.DevicesProblemDao;
import com.fw.service.device.dao.DevicesRepairDao;
import com.fw.service.device.dao.DevicesRepairItemDao;
import com.fw.service.device.dao.DevicesRepairSpareDao;
import com.fw.service.device.service.DevicesRepairService;
import com.fw.service.enums.CodeEnum;
import com.fw.service.util.E2CServicesUtil;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: dhlk_fw_plat
 * @description: 设备维修业务逻辑层
 * @author: wqiang
 * @create: 2020-10-21 16:22
 **/
@Service
@Transactional
public class DevicesRepairServiceImpl implements DevicesRepairService {

    @Autowired
    private DevicesRepairDao devicesRepairDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private DevicesRepairSpareDao devicesRepairSpareDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private JbpmUtil jbpmUtil;
    @Autowired
    private AttachmentDao attachmentDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    @Value("${E2C.webUrl}")
    private String webUrl;


    @Override
    public Result save(DevicesRepair devicesRepair) {
        int flag = 0;
        if (CheckUtils.isNull(devicesRepair.getId())) {
            devicesRepair.setCreateUser(authUserUtil.userId());
            devicesRepair.setCode(devicesRepairDao.findCode(CodeEnum.DEVICES_02.getCode()));
            flag = devicesRepairDao.insert(devicesRepair);
        } else {
            DevicesRepair dr = devicesRepairDao.findDevicesPairInfoById(devicesRepair.getId());
            if (dr == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = devicesRepairDao.update(devicesRepair);
        }
        return flag > 0 ? ResultUtils.success(devicesRepair.getId()) : ResultUtils.failure();
    }


    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        if (lists != null && lists.size() > 0) {
            devicesRepairDao.delete(lists);//删除维修单
            devicesRepairSpareDao.delete(lists); //删除维修单下所有的维修备品备件
            return ResultUtils.success();
        }
        return ResultUtils.failure();
    }

    /**
     * @param id
     * @param code                维修单编码
     * @param productDevicesId    设备编码
     * @param devicesClassify     设备类型
     * @param priority            优先级
     * @param repairProjectStatus 方案制作状态
     * @param pageNum
     * @param pageSize
     * @return
     * @paramrepairExeStatus 方案执行
     */
    @Override
    public Result findList(Integer id, String code, String productDevicesId, String devicesClassify, String priority, Integer repairProjectStatus, Integer repairExeStatus, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询E2C生产设备
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", productDevicesId);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(), params);

        List<String> productDevicesIds = null;
        if (!CheckUtils.isNull(productDevicesId)) {
            //如果设备编号查询条件不为空，根据设备编号查询结果为空，则直接返回空，如果不为空，则根据设备id进行数据查询
            if (productDevices == null || productDevices.size() == 0) {
                return ResultUtils.success(new PageInfo<>());
            } else {
                productDevicesIds = productDevices.stream().map(DevicesItemVo::getId).collect(Collectors.toList());
            }
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesRepair> lists = devicesRepairDao.findList(id, code, devicesClassify, productDevicesIds, attachmentPath, priority, repairProjectStatus, repairExeStatus);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                //生产设备
                if (!CheckUtils.isNull(item.getProductDevicesId())) {
                    if (productDevices != null && productDevices.size() > 0) {
                        productDevices.forEach(vo -> {
                            if (item.getProductDevicesId().equals(vo.getId())) {
                                item.setDevicesName(vo.getName());
                                item.setClassifyName(vo.getClassifyName());
                                if (vo.getClassifySet() != null && !CheckUtils.isNull(vo.getClassifySet().getImagePath())) {
                                    item.setImagePath(webUrl + vo.getClassifySet().getImagePath());
                                }
                            }
                        });
                    }
                }
                //用户设置
                if (userList != null && userList.size() > 0) {
                    userList.forEach(vo -> {
                        if (item.getCreateUser() == vo.getId()) { //设备维修创建人
                            item.setCreateUserName(vo.getName());
                        }
                        if (item.getProjectCreateUser() == vo.getId()) { //方案制定人
                            item.setProjectCreateUserName(vo.getName());
                        }
                        if (item.getRepairUser() == vo.getId()) { //维修执行人
                            item.setRepairUserName(vo.getName());
                        }
                    });
                }
                //设备备品备件
                item.setListSpare(devicesRepairSpareDao.findReparSpareList(item.getId()));
                //维修方案附件
                item.setPlanFileList(getBaseFileList(item.getPlanFile()));
            });
        }
        PageInfo<DevicesRepair> devicesRepairPage = new PageInfo<>(lists);
        return ResultUtils.success(devicesRepairPage);
    }

    /**
     * 制作方案
     *
     * @param devicesRepair
     * @return
     */
    @Override
    public Result makeProject(DevicesRepair devicesRepair) {
        int flag = 0;
        if (CheckUtils.isNull(devicesRepair.getId())) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesRepair dr = devicesRepairDao.findDevicesPairInfoById(devicesRepair.getId());
        if (dr.getRepairTaskExeStatus() == 1) { // 1表示任务已执行
            return ResultUtils.error("维修任务已执行，不能修改维修方案!");
        }
        devicesRepair.setRepairProjectStatus(1); // 1表示方案已制作
        devicesRepair.setProjectCreateTime(DateUtils.getCurrentTime());
        devicesRepair.setProjectCreateUser(authUserUtil.userId());
        flag = devicesRepairDao.update(devicesRepair);
        if (flag > 0) {
            List<DevicesRepairSpare> listSpare = devicesRepair.getListSpare();
            if (listSpare != null && listSpare.size() > 0) {
                //清空之前备品备件，并插入现有的
                devicesRepairSpareDao.deleteByDevicesId(devicesRepair.getId());
                devicesRepairSpareDao.insertAll(listSpare);
            }
            return ResultUtils.success();
        }
        return ResultUtils.error("方案制作失败,请重新制定!");
    }


    @Override
    public Result getTask(DevicesRepair devicesRepair) {

        int flag = 0;
        if (CheckUtils.isNull(devicesRepair.getId())) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        if (!CollectionUtils.isEmpty(devicesRepair.getListSpare())) {
            //清空维修单下的备品备件
            devicesRepairSpareDao.deleteByDevicesId(devicesRepair.getId());
            devicesRepairSpareDao.insertAll(devicesRepair.getListSpare());
        }
        devicesRepair.setTaskReceived(1);  //任务领取 0未领取  1已领取

        return devicesRepairDao.update(devicesRepair) > 0 ? ResultUtils.success() : ResultUtils.error("任务领取失败,请重新领取!");
    }

    /**
     * 执行维修任务
     *
     * @param devicesRepair
     * @return
     */
    @Override
    public Result repairTaskExecute(DevicesRepair devicesRepair) {
        int flag = 0;
        if (CheckUtils.isNull(devicesRepair.getId())) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesRepair dr = devicesRepairDao.findDevicesPairInfoById(devicesRepair.getId());
        if (dr.getRepairTaskExeStatus() == 1) { // 1表示任务已执行
            return ResultUtils.error("维修任务已执行，不能重复执行!");
        }
        devicesRepair.setRepairTime(DateUtils.getCurrentTime());
        devicesRepair.setRepairTaskExeStatus(1);
        devicesRepair.setRepairUser(authUserUtil.userId());
        //执行维修方案
        flag = devicesRepairDao.update(devicesRepair);
        //判断是否需要审核 0不审核 1需要审核
        if (devicesRepair.getIsReview() == 1) {
            jbpmUtil.startExecution(null, String.valueOf(devicesRepair.getId()), authUserUtil.userId(), "devicesRepair");
        }
        /*if (flag > 0) {
            //记录备品备件使用情况
            List<DevicesRepairSpare> listSpare = devicesRepair.getListSpare();
            if (listSpare != null && listSpare.size() > 0) {
                for (DevicesRepairSpare devicesRepairSpare : listSpare) {
                    //根据设备维修ID和备品备件ID查询是否存在，存在更新，不存在新增（制定方案时选择的备品备件）
                    DevicesRepairSpare reparSpare = devicesRepairSpareDao.findReparSpare(devicesRepairSpare.getDevicesRepairId(), devicesRepairSpare.getDevicesSpareId());
                    if (reparSpare != null) {
                        devicesRepairSpareDao.update(devicesRepairSpare);
                    } else {
                        devicesRepairSpareDao.insert(devicesRepairSpare);
                    }

                }
            }
            return ResultUtils.success();
        }*/
        return flag > 0 ? ResultUtils.success() : ResultUtils.error("执行维修任务失败，请重新执行！");
    }


    /**
     * 获取附件信息
     *
     * @param DataId
     * @return
     */
    public List<BaseFile> getBaseFileList(String DataId) {
        List<BaseFile> baseFileList = new ArrayList<>();
        if (!CheckUtils.isNull(DataId)) {
            List<String> dateIds = Arrays.asList(DataId.split(","));
            dateIds.forEach(item -> {
                baseFileList.addAll(attachmentDao.findByDataId(item, attachmentPath));
            });
        }
        return baseFileList;
    }

}
