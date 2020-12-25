package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldSpareIn;
import com.fw.entity.mould.MouldSpareOut;
import com.fw.entity.mould.MouldStorageInfo;
import com.fw.enums.ResultEnum;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: fchai
 * @Date: 2020/10/26 15:22
 */
@Service
public class MouldStorageServiceFbk implements MouldStorageService {

    @Override
    public Result insertSpareInfo(MouldStorageInfo mouldStorageInfo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result deleteSpareInfo(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findListSpareInfo(String code, String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result mouldIn(MouldSpareIn mouldSpareIn) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result mouldOut(MouldSpareOut mouldSpareOut) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getInOutInfo(String outInNo, Integer operate, String startDate, String endDate, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }


    @Override
    public Result findMouldSpareStoreList(String name) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findMouldSpareStoreAndUse(String code) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findOutInDetail(Integer id, Integer operate) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}