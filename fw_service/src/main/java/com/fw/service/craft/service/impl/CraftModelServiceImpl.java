package com.fw.service.craft.service.impl;

import com.fw.domain.BaseFile;
import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import com.fw.entity.craft.CraftModelProcess;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.enums.ResultEnum;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.craft.dao.CraftModelDao;
import com.fw.service.craft.dao.CraftModelProcessDao;
import com.fw.service.craft.service.CraftModelService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-06 10:56
 **/
@Service
@Transactional
public class CraftModelServiceImpl implements CraftModelService {

    @Autowired
    private CraftModelDao craftModelDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private CraftModelProcessDao craftModelProcessDao;

    @Autowired
    private AuthUserUtil authUserUtil;

    @Autowired
    private AttachmentDao attachmentDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    @Override
    public Result save(CraftModel craftModel) {
        int flag = 0;
        if (CheckUtils.isNull(craftModel.getId())) {
            Integer isExist = craftModelDao.excludeExist(null, craftModel.getProductCode());
            if(isExist > 0 ){
                return ResultUtils.error("产品编码已存在在，请重新选择！");
            }
            craftModel.setCreateUser(authUserUtil.userId());
            flag = craftModelDao.insert(craftModel);
            if (flag > 0) {
                if (!CollectionUtils.isEmpty(craftModel.getCaftModelProcesscList())) {
                    List<CraftModelProcess> caftModelProcesscList = craftModel.getCaftModelProcesscList();
                    for(CraftModelProcess craftModelProcess:caftModelProcesscList){
                        craftModelProcess.setCraftModelId(craftModel.getId());
                    }
                    craftModelProcessDao.insertAll(caftModelProcesscList);
                }
            }
        } else {
            if (craftModelDao.findCaftModelById(craftModel.getId()) == 0) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            Integer isExist = craftModelDao.excludeExist(craftModel.getId(), craftModel.getProductCode());
            if(isExist > 0 ){
                return ResultUtils.error("产品编码已存在在，请重新选择！");
            }
            flag = craftModelDao.update(craftModel);
            if (flag > 0) {
                if (!CollectionUtils.isEmpty(craftModel.getCaftModelProcesscList())) {
                    //清除注塑设备列表
                    craftModelProcessDao.deleteByCraftModelId(craftModel.getId());
                    //批量插入
                    craftModelProcessDao.insertAll(craftModel.getCaftModelProcesscList());
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String productCode, String productName, Integer modelType, Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);

        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<CraftModel> lists = craftModelDao.findList(productCode, productName, modelType);

        //获取设备名称
        if (!CollectionUtils.isEmpty(lists) && !CollectionUtils.isEmpty(productDevices)) {
            for (CraftModel craftModel : lists) {
                List<CraftModelProcess> craftModelProcessList = craftModel.getCaftModelProcesscList();
                if (!CollectionUtils.isEmpty(craftModelProcessList)) {
                    for (CraftModelProcess craftModelProcess : craftModelProcessList) {
                        for (DevicesItemVo devicesItemVo : productDevices) {
                            if (craftModelProcess.getDevicesId() == Integer.parseInt(devicesItemVo.getId())) {
                                craftModelProcess.setDevicesCode(devicesItemVo.getCode());
                                craftModelProcess.setDevicesName(devicesItemVo.getName());
                            }
                        }
                    }
                }
                //作业指导书附件
                craftModel.setFileList(getBaseFileList(craftModel.getFileIds()));
                //检验规范附件
                craftModel.setInspectionSpecList(getBaseFileList(craftModel.getInspectionSpecIds()));
                //不合格封样指导书
                craftModel.setNoneSampleList(getBaseFileList(craftModel.getNoneSampleIds()));
            }

        }
        PageInfo<CraftModel> devicesRepairPage = new PageInfo<>(lists);
        return ResultUtils.success(devicesRepairPage);
    }

    @Override
    public Result delete(String ids) {
        int flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        flag = craftModelDao.delete(Arrays.asList(ids.split(",")));
        if (flag > 0) {
            //删除工艺模型下的注塑设备列表
            craftModelProcessDao.deleteByCraftModelIds(Arrays.asList(ids.split(",")));
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findByProductCode(String productCode) {
        return ResultUtils.success(craftModelDao.findByProductCode(productCode));
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
