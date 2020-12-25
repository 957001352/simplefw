package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBadReport;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.dao.ProduceBadReportDao;
import com.fw.service.produce.service.ProduceBadReportService;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 不良上报 服务实现类
 *
 * @author xkliu
 * @since 2020-12-08
 */
@Service
public class ProduceBadReportServiceImpl implements ProduceBadReportService {

    @Autowired
    private ProduceBadReportDao produceBadReportDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Override
    @Transactional
    public Result save(ProduceBadReport produceBadReport) {
        Integer flag = 0;
        if (CheckUtils.isNull(produceBadReport.getId())) {
            produceBadReport.setCreateUser(headerUtil.loginUser().getId());
            flag = produceBadReportDao.insert(produceBadReport);
        } else {
            ProduceBadReport report = produceBadReportDao.selectById(produceBadReport.getId());
            if (report == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = produceBadReportDao.update(produceBadReport);
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
        flag = produceBadReportDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer moldingId, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProduceBadReport> list = produceBadReportDao.findList(moldingId);
        return ResultUtils.success(new PageInfo<>(list));
    }
}
