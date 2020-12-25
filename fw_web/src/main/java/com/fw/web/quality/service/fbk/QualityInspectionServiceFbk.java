package com.fw.web.quality.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspection;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityInspectionService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @des: 检验规范Feign接口调用失败
 * @author: xkliu
 * @date: 2020/11/24
 */
@Service
public class QualityInspectionServiceFbk implements QualityInspectionService {

    @Override
    public Result save(@Valid QualityInspection qualityInspection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result change(@Valid QualityInspection qualityInspection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String code, String preciseCode, String productName, Integer classify, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getInspectionDetail(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getInspectionChangeLog(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
