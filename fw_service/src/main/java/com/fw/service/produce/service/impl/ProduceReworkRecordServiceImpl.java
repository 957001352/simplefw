package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.ProduceReworkRecord;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.dao.ProduceReworkRecordDao;
import com.fw.service.produce.service.ProduceDutyService;
import com.fw.service.produce.service.ProduceReworkRecordService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 二次加工报工记录
 * @author lpsong
 * @since 2020-12-15
 */
@Service
public class ProduceReworkRecordServiceImpl implements ProduceReworkRecordService {

    @Autowired
    private ProduceReworkRecordDao produceReworkRecordDao;
    @Autowired
    private AuthUserUtil authUserUtil;

    @Override
    public Result save(ProduceReworkRecord produceReworkRecord) {
        if (produceReworkRecord == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        produceReworkRecord.setCreateUser(authUserUtil.userId());
        return produceReworkRecordDao.insert(produceReworkRecord) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer planReworkId, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ProduceReworkRecord> listPage = new PageInfo<>(produceReworkRecordDao.findList(planReworkId));
        return ResultUtils.success(listPage);
    }
}
