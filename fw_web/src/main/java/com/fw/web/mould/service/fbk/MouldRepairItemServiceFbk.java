package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepairItem;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldRepairItemService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-22 15:35
 **/
@Service
public class MouldRepairItemServiceFbk implements MouldRepairItemService {
    @Override
    public Result save(MouldRepairItem mouldRepairItem) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
