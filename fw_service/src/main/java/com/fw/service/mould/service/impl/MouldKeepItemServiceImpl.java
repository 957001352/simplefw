package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.enums.ResultEnum;
import com.fw.service.mould.dao.MouldKeepItemDao;
import com.fw.service.mould.service.MouldKeepItemService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 模具保养项目 实现类
 *
 * @author xkliu
 * @date 2020/10/27
 */
@Service
public class MouldKeepItemServiceImpl implements MouldKeepItemService {

    @Autowired
    private MouldKeepItemDao mouldKeepItemDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    @Override
    public Result getMouldKeepItem(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepItem keepItem = mouldKeepItemDao.selectById(id);
        return ResultUtils.success(keepItem);
    }

    @Override
    @Transactional
    public Result save(MouldKeepItem mouldKeepItem) {
        Integer flag = 0;
        //id为空新增设备保养项目
        if (CheckUtils.isNull(mouldKeepItem.getId())) {
            //名称不为空校验是否重复
            if (!CheckUtils.isNull(mouldKeepItem.getName())) {
                boolean res = mouldKeepItemDao.verifyName(mouldKeepItem.getName());
                if(res){
                    return ResultUtils.error("保养项目名称重复");
                }
            }
            flag = mouldKeepItemDao.insert(mouldKeepItem);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            MouldKeepItem keepItem = mouldKeepItemDao.selectById(mouldKeepItem.getId());
            if (keepItem == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            //修改时表单名称与传过来名称不通的时候进行校验
            if(!mouldKeepItem.getName().equals(keepItem.getName())){
                //校验是否有重复的表单名称
                boolean res = mouldKeepItemDao.verifyName(mouldKeepItem.getName());
                if(res){
                    return ResultUtils.error("保养项目名称重复");
                }
            }
            flag = mouldKeepItemDao.update(mouldKeepItem);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        flag = mouldKeepItemDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldKeepItem> lists = mouldKeepItemDao.findList(name);
        PageInfo<MouldKeepItem> keepItemPage = new PageInfo<>(lists);
        return ResultUtils.success(keepItemPage);
    }
}
