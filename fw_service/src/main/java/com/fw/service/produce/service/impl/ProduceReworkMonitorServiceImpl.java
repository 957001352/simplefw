package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsProduct;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.entity.produce.ProduceReworkRecord;
import com.fw.entity.quality.QualityStoreCheck;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsProductDao;
import com.fw.service.plan.dao.ReworkInjectionDao;
import com.fw.service.produce.dao.ProduceReworkMonitorDao;
import com.fw.service.produce.dao.ProduceReworkRecordDao;
import com.fw.service.produce.service.ProduceReworkMonitorService;
import com.fw.service.quality.dao.QualityStoreCheckDao;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @des: 二次加工监控 服务实现类
 * @author: xkliu
 * @date: 2020/12/15
 */
@Service
public class ProduceReworkMonitorServiceImpl implements ProduceReworkMonitorService {

    @Autowired
    private ProduceReworkMonitorDao produceReworkMonitorDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private QualityStoreCheckDao qualityStoreCheckDao;

    @Autowired
    private LogisticsProductDao logisticsProductDao;

    @Autowired
    private ReworkInjectionDao reworkInjectionDao;

    @Autowired
    private ProduceReworkRecordDao produceReworkRecordDao;

    @Override
    @Transactional
    public Result startProduce(ProduceReworkMonitor produceReworkMonitor) {
        Integer flag = 0;
        Integer planMoldingId = produceReworkMonitor.getPlanMoldingId();
        if(CheckUtils.isNull(planMoldingId)){
            return ResultUtils.error("无二次加工排产计划ID");
        }
        String order = produceReworkMonitor.getProductOrder();
        if (CheckUtils.isNull(order)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        ProduceReworkMonitor reworkMonitor = produceReworkMonitorDao.getProduceReworkMonitor(order);
        //根据生产指令获取到ProduceReworkMonitor对象,如果存在说明这条生产指令的数据已经存在,不能进行开始
        if(reworkMonitor != null){
            return ResultUtils.error("生产指令已经生产过");
        }
        produceReworkMonitor.setProductStartUser(headerUtil.loginUser().getName());
        produceReworkMonitor.setProductStartTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        produceReworkMonitorDao.insert(produceReworkMonitor);
        //修改二次加工排产计划状态
        flag = reworkInjectionDao.updateStatus(planMoldingId,1);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result endProduce(ProduceReworkMonitor produceReworkMonitor) {
        Integer flag = 0;
        Integer planMoldingId = produceReworkMonitor.getPlanMoldingId();
        if(CheckUtils.isNull(planMoldingId)){
            return ResultUtils.error("无二次加工排产计划ID");
        }
        String order = produceReworkMonitor.getProductOrder();
        if (CheckUtils.isNull(order)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //修改二次加工过程监控完成时间完成人
        produceReworkMonitor.setProductEndUser(headerUtil.loginUser().getName());
        produceReworkMonitor.setProductEndTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        flag = produceReworkMonitorDao.update(produceReworkMonitor);
        //修改二次加工排产计划状态
        if(flag > 0){
            flag = reworkInjectionDao.updateStatus(planMoldingId,4);
        }
        return  flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result customsInspection(ProduceReworkMonitor produceReworkMonitor) {
        Integer flag = 0;
        String order = produceReworkMonitor.getProductOrder();
        if (CheckUtils.isNull(order)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        User user = headerUtil.loginUser();
        List<QualityStoreCheck> qualityStoreChecks = produceReworkMonitor.getQualityStoreChecks();
        //给质量的入库检验管理新增数据
        if (!CollectionUtils.isEmpty(qualityStoreChecks)) {
            for (QualityStoreCheck item : qualityStoreChecks) {
                //检验订单号
                item.setCheckNo(qualityStoreCheckDao.findCode(CodeEnum.QUALITY_05.getCode()));
                //物料id
                item.setProductId(produceReworkMonitor.getProductId());
                item.setCreateUser(user.getId());
                flag = qualityStoreCheckDao.save(item);
            }
        }
        List<ProduceReworkRecord> records = produceReworkMonitor.getProduceReworkRecords();
        //修改二次交工记录状态
        if(!CollectionUtils.isEmpty(records)){
            flag = produceReworkRecordDao.batchUpdate(records);
        }
        //入库检验数据添加成功,则进行对入库时间和入库人员进行修改
        if(flag > 0){
            produceReworkMonitor.setStoreUser(user.getName());
            produceReworkMonitor.setStoreTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            flag = produceReworkMonitorDao.update(produceReworkMonitor);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Integer checkUser(String productOrder) {
        Integer flag = 0;
        if(CheckUtils.isNull(productOrder)){
            return flag;
        }
        ProduceReworkMonitor produceReworkMonitor = produceReworkMonitorDao.getProduceReworkMonitor(productOrder);
        if(produceReworkMonitor == null){
            return flag;
        }
        User user = headerUtil.loginUser();
        produceReworkMonitor.setCheckUser(user.getName());
        produceReworkMonitor.setCheckTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        return produceReworkMonitorDao.update(produceReworkMonitor);
    }
}
