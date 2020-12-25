package com.fw.service.quality.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.quality.QualityInspection;
import com.fw.entity.quality.QualityInspectionChangeLog;
import com.fw.entity.quality.QualityInspectionDetail;
import com.fw.enums.ResultEnum;
import com.fw.service.quality.dao.QualityInspectionChangeLogDao;
import com.fw.service.quality.dao.QualityInspectionDao;
import com.fw.service.quality.dao.QualityInspectionDetailDao;
import com.fw.service.quality.service.QualityInspectionService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.IDUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 检验规范 服务实现类
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Service
public class QualityInspectionServiceImpl implements QualityInspectionService {

    @Autowired
    private QualityInspectionDao qualityInspectionDao;

    @Autowired
    private QualityInspectionDetailDao qualityInspectionDetailDao;

    @Autowired
    private QualityInspectionChangeLogDao qualityInspectionChangeLogDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private JbpmUtil jbpmUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Value("${attachment.path}")
    private String attachmentPath;

    @Override
    @Transactional
    public Result save(QualityInspection qualityInspection) {
        Integer flag = 0;
        flag = qualityInspectionDao.verifyCode(qualityInspection.getProductId(),qualityInspection.getClassify());
        if(flag > 0){
            return ResultUtils.error("零件重复");
        }
        User user = headerUtil.loginUser();
        qualityInspection.setCreateUser(user.getId());
        flag = qualityInspectionDao.insert(qualityInspection);
        List<QualityInspectionDetail> details = qualityInspection.getQualityInspectionDetails();
        if (flag > 0 && !CollectionUtils.isEmpty(details)) {
            details.forEach(item -> {
                if (CheckUtils.isNull(item.getId())) {
                    item.setId(IDUtils.getId());
                }
                item.setInspectionId(qualityInspection.getId());
            });
            flag = qualityInspectionDetailDao.batchInsert(details);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result change(QualityInspection qualityInspection) {
        Integer flag = 0;
        QualityInspection inspection = qualityInspectionDao.selectById(qualityInspection.getId());
        if (inspection == null) {
            return ResultUtils.error("变更失败");
        }
        User user = headerUtil.loginUser();
        Integer userId = user.getId();
        //生成版本号
        String version = inspection.getVersion();
        Integer classify = inspection.getClassify();
        if ((classify.equals(0) && CheckUtils.isNull(version)) ||
                (classify.equals(1) && CheckUtils.isNull(version)) ||
                (classify.equals(2) && CheckUtils.isNull(version)) ||
                (classify.equals(3) && CheckUtils.isNull(version))) {
            version = "A0";
        } else {
            version = "A" + String.valueOf(Integer.parseInt(version.substring(1, version.length())) + 1);
        }
        qualityInspection.setUpdateTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
        qualityInspection.setUpdateUser(userId);
        qualityInspection.setVersion(version);
        //更新修改人和修改时间
        flag = qualityInspectionDao.update(qualityInspection);
        if (flag > 0) {
            QualityInspectionChangeLog changeLog = qualityInspection.getQualityInspectionChangeLog();
            changeLog.setCreateUser(userId);
            changeLog.setInspectionId(qualityInspection.getId());
            changeLog.setVersion(version);
            flag = qualityInspectionChangeLogDao.insert(changeLog);
            //开启审核
            if (flag > 0) {
                jbpmUtil.startExecution(null, String.valueOf(changeLog.getId()),
                        userId, "inspectionChange");
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String code, String preciseCode, String productName, Integer classify, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        List<QualityInspection> lists = qualityInspectionDao.findList(code, preciseCode, productName, classify);
        if(!CollectionUtils.isEmpty(lists)){
            lists.forEach(item ->{
                List<QualityInspectionDetail> details = item.getQualityInspectionDetails();
                if (!CollectionUtils.isEmpty(details)) {
                    details.forEach(detail -> {
                        //替换变更详情上传文件路径
                        String path = StringUtils.replace(detail.getWebPath(), attachmentPath, "/attach/");
                        detail.setWebPath(path);
                    });
                }
                List<QualityInspectionChangeLog> changeLogs = item.getQualityInspectionChangeLogs();
                if(!CollectionUtils.isEmpty(changeLogs)){
                    changeLogs.forEach(log -> {
                        userList.forEach(vo -> {
                            //变更人名字
                            if (!CheckUtils.isNull(log.getCreateUser())) {
                                if (log.getCreateUser().equals(vo.getId())) {
                                    log.setCreateUserName(vo.getName());
                                }
                            }
                        });
                    });
                }
            });
        }
        PageInfo<QualityInspection> qualityInspection = new PageInfo<>(lists);
        return ResultUtils.success(qualityInspection);
    }

    @Override
    public Result getInspectionDetail(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        QualityInspection inspectionDetail = qualityInspectionDao.getInspectionDetail(id);
        if (inspectionDetail == null) {
            return ResultUtils.error(ResultEnum.NULL_DATA);
        }
        List<QualityInspectionDetail> details = inspectionDetail.getQualityInspectionDetails();
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(detail -> {
                String path = StringUtils.replace(detail.getWebPath(), attachmentPath, "/attach/");
                detail.setWebPath(path);
            });
        }
        List<QualityInspectionChangeLog> lists = inspectionDetail.getQualityInspectionChangeLogs();
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //变更人名字
                    if (!CheckUtils.isNull(item.getCreateUser())) {
                        if (item.getCreateUser().equals(vo.getId())) {
                            item.setCreateUserName(vo.getName());
                        }
                    }
                });
            });
        }
        return ResultUtils.success(inspectionDetail);
    }

    @Override
    public Result getInspectionChangeLog(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        QualityInspectionChangeLog changeLog = qualityInspectionChangeLogDao.getInspectionChangeLog(id);
        return ResultUtils.success(changeLog);
    }

    @Override
    @Transactional
    public Result inspectionChangePass(Integer id) {
        Integer flag = 0;
        QualityInspectionChangeLog changeLog = qualityInspectionChangeLogDao.getInspectionChangeLog(id);
        if (changeLog != null) {
            //获取检验规范内容的json字符串
            String inspectionContent = changeLog.getInspectionContent();
            if (CheckUtils.isNull(inspectionContent)) {
                return ResultUtils.failure();
            }
            //将json字符串转化成 List<QualityInspectionDetail> 对象
            List<QualityInspectionDetail> details =
                    JSON.parseObject(inspectionContent, new TypeReference<ArrayList<QualityInspectionDetail>>() {
                    });
            details.forEach(item -> {
                //规范分类是4,则修改检验规范主表中的备注
                if (item.getClassify().equals(4)) {
                    QualityInspection inspection = qualityInspectionDao.selectById(changeLog.getInspectionId());
                    inspection.setNote(item.getRemark());
                    qualityInspectionDao.update(inspection);
                }
                //规范分类是5,则修改检验规范主表中的巡检时间
                if (item.getClassify().equals(5)) {
                    QualityInspection inspection = qualityInspectionDao.selectById(changeLog.getInspectionId());
                    inspection.setInspection(item.getInspection());
                    qualityInspectionDao.update(inspection);
                }
                //id为空,uuid生成id
                if (CheckUtils.isNull(item.getId())) {
                    item.setId(IDUtils.getId());
                }
            });
            //根据检验规范id删除所有的检验规范明细
            qualityInspectionDetailDao.deleteByInspectionId(changeLog.getInspectionId());
            //过滤掉没有inspectionId的数据
            List<QualityInspectionDetail> inspectionDetail = details.stream().filter(i -> !CheckUtils.isNull(i.getInspectionId())).collect(Collectors.toList());
            //再新添加过滤好的 List<QualityInspectionDetail>
            flag = qualityInspectionDetailDao.batchInsert(inspectionDetail);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

}
