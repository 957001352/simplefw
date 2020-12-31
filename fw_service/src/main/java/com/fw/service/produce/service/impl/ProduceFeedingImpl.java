package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.produce.ProduceFeeding;
import com.fw.entity.produce.ProduceFeedingDetail;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsOutHouseDetailDao;
import com.fw.service.produce.dao.ProduceFeedingDao;
import com.fw.service.produce.dao.ProduceFeedingDetailDao;
import com.fw.service.produce.service.ProduceFeedingService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @des: 投料 服务实现类
 * @author: xkliu
 * @date: 2020/12/14
 */
@Service
public class ProduceFeedingImpl implements ProduceFeedingService {

    @Autowired
    private ProduceFeedingDao produceFeedingDao;

    @Autowired
    private ProduceFeedingDetailDao produceFeedingDetailDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private LogisticsOutHouseDetailDao logisticsOutHouseDetailDao;


    @Override
    @Transactional
    public Result save(ProduceFeeding produceFeeding) {
        Integer flag = 0;
        User user = headerUtil.loginUser();
        produceFeeding.setCreateUser(user.getId());
        String feeNo = produceFeedingDao.findCode(CodeEnum.PRODUCE_02.getCode());
        produceFeeding.setFeedNo(feeNo);
        flag = produceFeedingDao.insert(produceFeeding);

        if (flag > 0) {
            List<ProduceFeedingDetail> produceFeedingDetails = produceFeeding.getProduceFeedingDetails();
            if (!CollectionUtils.isEmpty(produceFeedingDetails)) {
                produceFeedingDetails.forEach(item -> {
                    List<LogisticsOutHouseDetail> houseDetail = logisticsOutHouseDetailDao.findByDetailId(item.getStorageDetailId());
                    if (!CollectionUtils.isEmpty(houseDetail)) {
                        Integer count = item.getStorageCount();
                        for (LogisticsOutHouseDetail detail : houseDetail) {
                            count = detail.getStorageCount() - count;
                            if (count >= 0) {
                                logisticsOutHouseDetailDao.updateFeedCount(detail.getId(), item.getStorageCount());
                                break;
                            } else {
                                logisticsOutHouseDetailDao.updateFeedCount(detail.getId(), item.getStorageCount());
                                count = Math.abs(count);
                                continue;
                            }
                        }
                    }
                    //设置
                    item.setFeedingId(produceFeeding.getId());
                });
                flag = produceFeedingDetailDao.batchInsert(produceFeedingDetails);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String productOrder, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        //查询E2C生产设备
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<ProduceFeeding> lists = produceFeedingDao.findList(productOrder);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //投料人名字
                    if (!CheckUtils.isNull(item.getCreateUser())) {
                        if (item.getCreateUser().equals(vo.getId())) {
                            item.setCreateUserName(vo.getName());
                        }
                    }
                });
                //生产设备
                productList.forEach(product -> {
                    if (!CheckUtils.isNull(item.getProductDevicesId())) {
                        if (item.getProductDevicesId().equals(Integer.valueOf(product.getId()))) {
                            item.setProductDevicesCode(product.getName());
                        }
                    }
                });
            });
        }
        return ResultUtils.success(new PageInfo<>(lists));
    }

    @Override
    public Result getStorageCount(Integer storageDetailId) {
        if (CheckUtils.isNull(storageDetailId)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        LogisticsOutHouseDetail detail = logisticsOutHouseDetailDao.getStorageCount(storageDetailId);
        return ResultUtils.success(detail);
    }


}
