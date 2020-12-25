package com.fw.service.quality.service.impl;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.service.quality.dao.QualityFirstendCheckDao;
import com.fw.service.quality.dao.QualityInspectResultDao;
import com.fw.service.quality.service.QualityFirstendCheckService;
import com.fw.service.quality.service.QualityInspectResultService;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 检验结果 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Service
@Transactional
public class QualityInspectResultServiceImpl implements QualityInspectResultService {

    @Autowired
    private QualityInspectResultDao qualityInspectResultDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired

    private AuthUserUtil authUserUtil;


    @Override
    public Result findHistoryResultByOfon(String ofNo) {
        if(CheckUtils.isNull(ofNo)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return ResultUtils.success(qualityInspectResultDao.findHistoryResultByOfon(ofNo));
    }
}
