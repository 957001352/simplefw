package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepPlan;
import com.fw.entity.mould.MouldKeepTask;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.mould.dao.MouldKeepPlanDao;
import com.fw.service.mould.dao.MouldKeepTaskDao;
import com.fw.service.mould.dao.MouldKeepTeamDao;
import com.fw.service.mould.service.MouldKeepPlanService;
import com.fw.service.util.GenerateDateUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 模具保养计划制定 服务实现类
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Service
public class MouldKeepPlanServiceImpl implements MouldKeepPlanService {

    @Autowired
    private MouldKeepPlanDao mouldKeepPlanDao;

    @Autowired
    private MouldKeepTeamDao mouldKeepTeamDao;

    @Autowired
    private MouldKeepTaskDao mouldKeepTaskDao;

    @Override
    @Transactional
    public Result save(MouldKeepPlan mouldKeepPlan) {
        Integer flag = 0;
        //id为空新增设备保养计划制定
        if (CheckUtils.isNull(mouldKeepPlan.getId())) {
            //名称不为空校验是否重复
            if (!CheckUtils.isNull(mouldKeepPlan.getName())) {
                boolean res = mouldKeepPlanDao.verifyName(mouldKeepPlan.getName());
                if (res) {
                    return ResultUtils.error("保养计划定制名称重复");
                }
            }
            if (mouldKeepPlan.getKeepFre().equals(1)) {
                String lastTime = GenerateDateUtil.generateLastTime(mouldKeepPlan.getStartTime(), null, null, mouldKeepPlan.getKeepCycle());
                mouldKeepPlan.setLastTime(lastTime);
            }
            flag = mouldKeepPlanDao.insert(mouldKeepPlan);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            MouldKeepPlan keepPlan = mouldKeepPlanDao.selectById(mouldKeepPlan.getId());
            if (keepPlan == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            //修改时表单名称与传过来名称不通的时候进行校验
            if (!mouldKeepPlan.getName().equals(keepPlan.getName())) {
                //校验是否有重复的表单名称
                boolean res = mouldKeepPlanDao.verifyName(mouldKeepPlan.getName());
                if (res) {
                    return ResultUtils.error("保养计划定制名称重复");
                }
            }
            //是固定保养周期才计算预计保养时间
            if (mouldKeepPlan.getKeepFre().equals(1)) {
                String lastTime = GenerateDateUtil.generateLastTime(mouldKeepPlan.getStartTime(), null, null, mouldKeepPlan.getKeepCycle());
                mouldKeepPlan.setLastTime(lastTime);
            }
            mouldKeepPlan.setStatus(0);
            flag = mouldKeepPlanDao.update(mouldKeepPlan);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getMouldKeepPlan(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepPlan mouldKeepPlan = mouldKeepPlanDao.selectById(id);
        return ResultUtils.success(mouldKeepPlan);
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        if (mouldKeepTaskDao.isExistPlan(lists) > 0) {
            return ResultUtils.error("已经有任务下发,不能删除");
        }
        for (String str : lists) {
            MouldKeepPlan keepPlan = mouldKeepPlanDao.selectById(Integer.valueOf(str));
            if (keepPlan == null) {
                return ResultUtils.error("删除失败,请刷新页面");
            }
        }
        flag = mouldKeepPlanDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getAllKeepTeam() {
        return ResultUtils.success(mouldKeepTeamDao.getAllKeepTeam());
    }

    @Override
    public Result findList(String name, String keepTeamName, String mouldCode, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldKeepPlan> lists = mouldKeepPlanDao.findList(name, keepTeamName, mouldCode);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                if (item.getKeepFre().equals(1)) {
                    //提前通知时间
                    String advanceTime = GenerateDateUtil.generateLastTime(item.getLastTime(), item.getKeepCycle(), null, null);
                    item.setAdvanceTime(advanceTime);
                } else {
                    item.setLastTime(null);
                }

            });
        }
        PageInfo<MouldKeepPlan> keepPlanPage = new PageInfo<>(lists);
        return ResultUtils.success(keepPlanPage);
    }

    @Override
    @Transactional
    public Integer findNonOpening() {
        Integer flag = 0;
        List<MouldKeepPlan> lists = mouldKeepPlanDao.findNonOpening();
        if (!CollectionUtils.isEmpty(lists)) {
            for (MouldKeepPlan plan : lists) {
                if (!CheckUtils.isNull(plan.getTotalCount())) {
                    if (plan.getTotalCount() % (plan.getKeepCycle() * 10000) == 0) {
                        String keepOrder = mouldKeepTaskDao.findCode(CodeEnum.MOULD_01.getCode());
                        MouldKeepTask keepTask = new MouldKeepTask("0", plan.getId(), keepOrder,plan.getLastTime(),plan.getMouldId());
                        mouldKeepTaskDao.insert(keepTask);
                        plan.setStatus(1);
                        flag = mouldKeepPlanDao.update(plan);
                    }
                }
            }
        }
        return flag;
    }

}
