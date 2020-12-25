package com.fw.web.audit.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.audit.service.AuditItemService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 11:03
 **/

@Service
public class AuditItemServiceFbk implements AuditItemService {
    @Override
    public Result save(AuditItem auditItem) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name,Integer pageNum,Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
