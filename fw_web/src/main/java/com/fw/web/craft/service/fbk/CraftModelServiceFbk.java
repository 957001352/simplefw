package com.fw.web.craft.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.craft.service.CraftModelService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-06 11:47
 **/
@Service
public class CraftModelServiceFbk implements CraftModelService {
    @Override
    public Result save(CraftModel craftModel) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String productCode, String productName, Integer modelType, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByProductCode(String productCode) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
