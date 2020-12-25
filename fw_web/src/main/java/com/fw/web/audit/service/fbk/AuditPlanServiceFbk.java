package com.fw.web.audit.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.audit.service.AuditItemService;
import com.fw.web.audit.service.AuditPlanService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 11:03
 **/

@Service
public class AuditPlanServiceFbk implements AuditPlanService {
    @Override
    public Result save(AuditPlan auditPlan) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name,String auditTeamName,String userName,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
