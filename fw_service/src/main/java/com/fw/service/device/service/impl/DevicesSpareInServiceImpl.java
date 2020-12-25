package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetail;
import com.fw.entity.device.DevicesSpareIn;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.enums.SystemEnums;
import com.fw.exceptions.MyException;
import com.fw.service.device.dao.DevicesOutInDetailDao;
import com.fw.service.device.dao.DevicesSpareInDao;
import com.fw.service.device.dao.ProductDevicesSpareDao;
import com.fw.service.device.service.DevicesSpareInService;
import com.fw.service.enums.CodeEnum;
import com.fw.util.AuthUserUtil;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/27
 * Time:8:58
 * @Description:
 */
@Service
public class DevicesSpareInServiceImpl implements DevicesSpareInService {

    @Autowired
    private DevicesSpareInDao devicesSpareInDao;

    @Autowired
    private DevicesOutInDetailDao devicesOutInDetailDao;

    @Autowired
    private ProductDevicesSpareDao productDevicesSpareDao;

    @Autowired
    private AuthUserUtil authUserUtil;

    @Override
    @Transactional
    public Result insert(DevicesSpareIn devicesSpareIn) {
        //第1步：获取出库ID ---array
        // 所有要入库的备品
        List<ProductDevicesSpare> list = devicesSpareIn.getProductDevicesSparesAndOutInDetailList();

        Integer userId = authUserUtil.userId();
        devicesSpareIn.setCreateUser(userId);
        // 入库单号
        devicesSpareIn.setInNo(devicesSpareInDao.findCode(CodeEnum.DEVICES_03.getCode()));
        devicesSpareInDao.insert(devicesSpareIn);
        // 获取备品出库表的出库ID     该id对应出入库明细表治中出入库对象   一个id对应多个出入库对象
        Integer outIn = devicesSpareIn.getId();
        for (ProductDevicesSpare devicesSpare : list) {

            ProductDevicesSpare productDevicesSpare = productDevicesSpareDao.selectById(devicesSpare.getId());
            DevicesOutinDetail devicesOutinDetail = devicesSpare.getDevicesOutinDetail();

            Integer storeCount = productDevicesSpare.getStoreCount(); // 库存
            Integer useCount = devicesOutinDetail.getUseCount(); // 入库数量

            //第2步：判断是否具备出库条件
            //    2.1 库中是否包含此样品 true or flase
            if (productDevicesSpare == null) {
                // 非法参数异常
                throw new MyException(SystemEnums.PARMS_ILLEGAL);
            } else {

                // 从备品备件表中查询部分数据传入出入库明细表
                devicesOutinDetail.setOutIn(outIn);
                devicesOutinDetail.setLocation(productDevicesSpare.getLocation());
                devicesOutinDetail.setOperate(0);
                devicesOutinDetail.setDevicesSpareId(productDevicesSpare.getId());
                devicesOutInDetailDao.insert(devicesOutinDetail);


                int i = storeCount + useCount;
                // 第3步 ，库存修改库存数量
                productDevicesSpare.setStoreCount(i);
                //  改数据库库存 入库
                productDevicesSpareDao.update(productDevicesSpare);
            }
        }
        //第4步：返回前端结果
        return ResultUtils.success();
    }

    /**
     * 根据id查看入库详细信息
     *
     * @param id 入库单id，和出入库详情的outIn相同，一个id对应多个outIn
     * @return
     */
    @Override
    public Result selectByIn(Integer id) {
        ArrayList<ProductDevicesSpare> list = new ArrayList<>();
        // 通过id查询入库单信息
        DevicesSpareIn devicesSpareIn = devicesSpareInDao.selectById(id);
        // 一个入库单包含多个备件（出入库详情表）
        List<DevicesOutinDetail> devicesOutinDetails = devicesOutInDetailDao.selectByOutin(id, "0");
        if (!CollectionUtils.isEmpty(devicesOutinDetails)) {
            // 获取一个入库单对应的多个备件详情
            for (DevicesOutinDetail outinDetail : devicesOutinDetails) {
                // 获取备品备件id
                Integer devicesSpareId = outinDetail.getDevicesSpareId();
                // 通过备品备件id获取入库单的备件详情
                ProductDevicesSpare productDevicesSpare = productDevicesSpareDao.selectById(devicesSpareId);
                if (productDevicesSpare != null){
                    // 每个备件对应的入库信息
                    productDevicesSpare.setDevicesOutinDetail(outinDetail);
                    list.add(productDevicesSpare);
                }
            }
        }
        // 补全入库信息
        devicesSpareIn.setProductDevicesSparesAndOutInDetailList(list);
        return ResultUtils.success(devicesSpareIn);


    }

    @Override
    public Result findAll(Integer pageNum, Integer pageSize) {
        return null;
    }
}
