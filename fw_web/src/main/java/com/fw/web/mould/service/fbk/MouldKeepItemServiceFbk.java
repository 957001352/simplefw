package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldKeepItemService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 模具保养项目Feign接口调用失败
 * @date 2020/10/28
 */
@Service
public class MouldKeepItemServiceFbk implements MouldKeepItemService {

    @Override
    public Result save(MouldKeepItem mouldKeepItem) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getMouldKeepItem(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }


}
