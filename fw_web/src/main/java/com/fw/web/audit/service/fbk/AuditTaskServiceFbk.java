package com.fw.web.audit.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTask;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.audit.service.AuditItemService;
import com.fw.web.audit.service.AuditTaskService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 11:03
 **/

@Service
public class AuditTaskServiceFbk implements AuditTaskService {

    @Override
    public Result findList(String taskNo, String exeUser, Integer status, String executeTime,Integer pageNum,Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findTaskDetails(Integer id,Integer status) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result exeTask(AuditTask auditTask) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
