package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetail;
import com.fw.entity.device.DevicesSpareOut;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.enums.ResultEnum;
import com.fw.enums.SystemEnums;
import com.fw.exceptions.MyException;
import com.fw.service.device.dao.DevicesOutInDetailDao;
import com.fw.service.device.dao.DevicesSpareOutDao;
import com.fw.service.device.dao.ProductDevicesSpareDao;
import com.fw.service.device.service.DevicesSpareOutService;
import com.fw.service.enums.CodeEnum;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * Time:14:52
 * @Description: 备品备件出库实现类
 */
@Service
public class DevicesSpareOutServiceImpl implements DevicesSpareOutService {

    @Autowired
    private DevicesSpareOutDao devicesSpareOutDao;

    @Autowired
    private DevicesOutInDetailDao devicesOutInDetailDao;

    @Autowired
    private ProductDevicesSpareDao productDevicesSpareDao;

    @Autowired
    private AuthUserUtil authUserUtil;

    @Override
    @Transactional
    public Result insert(DevicesSpareOut devicesSpareOut) throws Exception {
        //第1步：获取出库ID ---array
        // 所有要出库的备品
        List<ProductDevicesSpare> list = devicesSpareOut.getProductDevicesSparesAndOutInDetailList();
        Integer userId = authUserUtil.userId();
        devicesSpareOut.setCreateUser(userId);
        devicesSpareOut.setOutNo(devicesSpareOutDao.findCode(CodeEnum.DEVICES_04.getCode()));

        devicesSpareOutDao.insert(devicesSpareOut);
        // 获取备品出库表的出库ID     该id对应出入库明细表治中出入库对象   一个id对应多个出入库对象
        Integer outIn = devicesSpareOut.getId();

        for (ProductDevicesSpare devicesSpare : list) {

            ProductDevicesSpare productDevicesSpare = productDevicesSpareDao.selectById(devicesSpare.getId());
            DevicesOutinDetail devicesOutinDetail = devicesSpare.getDevicesOutinDetail();

            Integer storeCount = productDevicesSpare.getStoreCount(); // 库存
            Integer useCount = devicesOutinDetail.getUseCount(); // 出库数量

            //第2步：判断是否具备出库条件
            //    2.1 库中是否包含此样品 true or flase
            if (productDevicesSpare == null) {
                throw new MyException(SystemEnums.PARMS_ILLEGAL);
            } else if (storeCount < useCount) {
                //    2.2 库存数量是否满足  true or flase
                throw new MyException(SystemEnums.PARMS_ILLEGAL);
            } else {

                // 从备品备件表中查询部分数据传入出入库明细表
                devicesOutinDetail.setOutIn(outIn);
                devicesOutinDetail.setLocation(productDevicesSpare.getLocation());
                devicesOutinDetail.setOperate(1);
                devicesOutinDetail.setDevicesSpareId(productDevicesSpare.getId());
                devicesOutInDetailDao.insert(devicesOutinDetail);

                // 第3步：出库
                int i = productDevicesSpare.getStoreCount() - devicesOutinDetail.getUseCount();
                //  如果库存修改库存数量
                productDevicesSpare.setStoreCount(i);
                //  改数据库库存 出库
                productDevicesSpareDao.update(productDevicesSpare);
            }
        }
        //第4步：返回前端结果
        return ResultUtils.success();
    }

    /**
     * selectById 出库信息
     *
     * @param id 出库单id，对应出入库详情outIn，一个id对应多个outIn
     * @return
     */
    @Override
    public Result selectByOut(Integer id) {

        ArrayList<ProductDevicesSpare> list = new ArrayList<>();
        // 通过id查询出库单信息
        DevicesSpareOut devicesSpareOut = devicesSpareOutDao.selectById(id);

        // 一个出库单包含多个备件（出入库详情表）
        List<DevicesOutinDetail> devicesOutinDetails = devicesOutInDetailDao.selectByOutin(id, "1");
        if (!CollectionUtils.isEmpty(devicesOutinDetails)) {
            // 获取一个出库单对应的多个备件详情
            for (DevicesOutinDetail outinDetail : devicesOutinDetails) {
                // 获取备品备件id
                Integer devicesSpareId = outinDetail.getDevicesSpareId();
                // 通过备品备件id获取出库单的备件详情
                ProductDevicesSpare productDevicesSpare = productDevicesSpareDao.selectById(devicesSpareId);
                if (productDevicesSpare != null) {
                    // 每个备件对应的出库信息
                    productDevicesSpare.setDevicesOutinDetail(outinDetail);
                    list.add(productDevicesSpare);
                }
            }
        }
        // 补全出库信息
        devicesSpareOut.setProductDevicesSparesAndOutInDetailList(list);
        return ResultUtils.success(devicesSpareOut);

    }

    /**
     * 出库信息列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result findAll(Integer pageNum, Integer pageSize) {


        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        // 1.获取所有的出库单列表
        List<DevicesSpareOut> spareOutList = devicesSpareOutDao.findAll(pageNum, pageSize);

        for (DevicesSpareOut spareOut : spareOutList) {
            // 获取出库单id，对应出入库详情的出入库对象outIn
            Integer id = spareOut.getId();
            // 获取每个出库id对应的所有的备品
            List<DevicesOutinDetail> outInDetails = devicesOutInDetailDao.selectByOutin(id, "1");
            ArrayList<ProductDevicesSpare> list = new ArrayList<>();
            for (DevicesOutinDetail outinDetail : outInDetails) {

                Integer devicesSpareId = outinDetail.getDevicesSpareId();
                ProductDevicesSpare productDevicesSpare = productDevicesSpareDao.selectById(devicesSpareId);

                productDevicesSpare.setDevicesOutinDetail(outinDetail);

                list.add(productDevicesSpare);
            }

            spareOut.setProductDevicesSparesAndOutInDetailList(list);
        }
        //  返回结果
        PageInfo<DevicesSpareOut> devicesSpareOutPage = new PageInfo<>(spareOutList);
        return ResultUtils.success(devicesSpareOutPage);
    }
}
