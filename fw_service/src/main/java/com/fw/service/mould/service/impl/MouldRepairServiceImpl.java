package com.fw.service.mould.service.impl;

import com.fw.domain.BaseFile;
import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.device.DevicesRepairSpare;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldRepair;
import com.fw.entity.mould.MouldRepairSpare;
import com.fw.enums.ResultEnum;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.device.dao.DevicesRepairDao;
import com.fw.service.device.dao.DevicesRepairSpareDao;
import com.fw.service.device.service.DevicesRepairService;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.MouldRepairDao;
import com.fw.service.mould.dao.MouldRepairSpareDao;
import com.fw.service.mould.service.MouldDevicesService;
import com.fw.service.mould.service.MouldRepairService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.mail.FetchProfile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 模具维修业务逻辑层
 * @author: wqiang
 * @create: 2020-10-21 16:22
 **/
@Service
@Transactional
public class MouldRepairServiceImpl implements MouldRepairService {

    @Autowired
    private MouldRepairDao mouldRepairDao;
    @Autowired
    private MouldRepairSpareDao mouldRepairSpareDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private MouldDevicesService mouldDevicesService;

    @Autowired
    private AttachmentDao attachmentDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    @Value("${E2C.webUrl}")
    private String webUrl;

    @Autowired
    private JbpmUtil jbpmUtil;


    @Override
    public Result save(MouldRepair mouldRepair) {
        int flag = 0;
        if (CheckUtils.isNull(mouldRepair.getId())) {
            mouldRepair.setCreateUser(authUserUtil.userId());
            mouldRepair.setCode(mouldRepairDao.findCode(CodeEnum.MOULD_02.getCode()));
            flag = mouldRepairDao.insert(mouldRepair);
        } else {
            MouldRepair mr = mouldRepairDao.findMouldRePairInfoById(mouldRepair.getId());
            if (mr == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = mouldRepairDao.update(mouldRepair);
        }
        return flag > 0 ? ResultUtils.success(mouldRepair.getId()) : ResultUtils.failure();
    }


    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        return mouldRepairDao.delete(lists) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer id, String code, String mouldId, String mouldName, String priority, Integer repairProjectStatus, Integer repairExeStatus, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<MouldRepair> list = mouldRepairDao.findList(id, code, mouldId, mouldName, attachmentPath, priority, repairProjectStatus, repairExeStatus);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                if(!CollectionUtils.isEmpty(userList)){
                    userList.forEach(vo -> {
                        if (item.getCreateUser() == vo.getId()) {
                            item.setCreateUserName(vo.getName());
                        }
                        if (item.getProjectCreateUser() == vo.getId()) {
                            item.setProjectCreateUserName(vo.getName());
                        }
                        if (item.getRepairUser() == vo.getId()) {
                            item.setRepairUserName(vo.getName());
                        }
                    });
                }
                item.setMouldRepairSpareList(mouldRepairSpareDao.findMouldSpareByMouldRepaiID(item.getId())); //备品备件
                //维修方案附件
                item.setPlanFileList(getBaseFileList(item.getPlanFile()));
            });

        }

        PageInfo<MouldRepair> mouldRepairPage = new PageInfo<>(list);
        return ResultUtils.success(mouldRepairPage);
    }


    /**
     * 制作方案
     *
     * @param mouldRepair
     * @return
     */
    @Override
    public Result makeProject(MouldRepair mouldRepair) {
        int flag = 0;
        if (CheckUtils.isNull(mouldRepair.getId())) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldRepair mr = mouldRepairDao.findMouldRePairInfoById(mouldRepair.getId());
        if (mr.getRepairTaskExeStatus() == 1) { // 1表示任务已执行
            return ResultUtils.error("维修任务已执行，不能修改维修方案!");
        }
        mouldRepair.setProjectCreateUser(authUserUtil.userId());
        mouldRepair.setRepairProjectStatus(1); // 1表示方案已制作
        mouldRepair.setProjectCreateTime(DateUtils.getCurrentTime());
        flag = mouldRepairDao.update(mouldRepair);
        //记录制作方案备品备件使用情况
        if (flag > 0) {
            List<MouldRepairSpare> mouldRepairSpareList = mouldRepair.getMouldRepairSpareList();
            if (!CollectionUtils.isEmpty(mouldRepairSpareList)) {
                mouldRepairSpareDao.deleteByMouldRepairId(mouldRepair.getId());
                mouldRepairSpareDao.insertAll(mouldRepairSpareList);
            }
            return ResultUtils.success();
        }
        return ResultUtils.failure();
    }

    @Override
    public Result getTask(MouldRepair mouldRepair) {
        int flag = 0;
        if (CheckUtils.isNull(mouldRepair.getId())) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }

        if (!CollectionUtils.isEmpty(mouldRepair.getMouldRepairSpareList())) {
            //清空维修单下的备品备件
            mouldRepairSpareDao.deleteByMouldRepairId(mouldRepair.getId());
            mouldRepairSpareDao.insertAll(mouldRepair.getMouldRepairSpareList());
        }
        mouldRepair.setTaskReceived(1);  //任务领取 0未领取  1已领取

        return mouldRepairDao.update(mouldRepair) > 0 ? ResultUtils.success() : ResultUtils.error("任务领取失败,请重新领取!");
    }

    /**
     * 执行维修任务
     *
     * @param mouldRepair
     * @return
     */
    @Override
    public Result repairTaskExecute(MouldRepair mouldRepair) {
        int flag = 0;
        if (CheckUtils.isNull(mouldRepair.getId())) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldRepair mr = mouldRepairDao.findMouldRePairInfoById(mouldRepair.getId());
        if (mr.getRepairTaskExeStatus() == 1) { // 1表示任务已执行
            return ResultUtils.error("维修任务已执行，不能重复执行!");
        }
        mouldRepair.setRepairTaskExeStatus(1);
        mouldRepair.setRepairUser(authUserUtil.userId());
        mouldRepair.setRepairTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        flag = mouldRepairDao.update(mouldRepair);
        //判断是否需要审核 0不审核 1需要审核
        if (mouldRepair.getIsReview() == 1) {
            jbpmUtil.startExecution(null, String.valueOf(mouldRepair.getId()), authUserUtil.userId(), "mouldRepair");
        }
        /*if (flag > 0) {
            //记录备品备件使用情况
            List<MouldRepairSpare> mouldRepairSpareList = mouldRepair.getMouldRepairSpareList();
            if (!CollectionUtils.isEmpty(mouldRepairSpareList)) {
                for (MouldRepairSpare mouldRepairSpare : mouldRepairSpareList) {
                    MouldRepairSpare mouldSpare = mouldRepairSpareDao.findMouldSpare(mouldRepairSpare.getMouldRepairId(), mouldRepairSpare.getMouldSpareId());
                    if (mouldSpare != null) {
                        mouldRepairSpareDao.updateMouldSpare(mouldRepairSpare);
                    } else {
                        mouldRepairSpareDao.insert(mouldRepairSpare);
                    }
                }
                return ResultUtils.success();
            }
        }*/
        return flag > 0 ? ResultUtils.success() : ResultUtils.error("执行维修任务失败，请重新执行！");
    }


    /**
     * 审核通过调用接口
     * @param id
     * @return
     */
    public Result auditPass(Integer id){

        int flag = 0;
        if(CheckUtils.isNull(id)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldRepair mouldRepair = new MouldRepair();
        mouldRepair.setId(id);
        mouldRepair.setStatus(1);//0未审核 1审核通过
        flag = mouldRepairDao.update(mouldRepair);
        if(flag > 0 ){  //修改模具履历的模腔数
            MouldRepair mr = mouldRepairDao.findMouldRePairInfoById(id);
            MouldDevices mouldDevices = new MouldDevices();
            mouldDevices.setId(Integer.parseInt(mr.getMouldId()));
            mouldDevices.setCavity(String.valueOf(mr.getCavityNumber()));
            mouldDevicesService.save(mouldDevices);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
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
