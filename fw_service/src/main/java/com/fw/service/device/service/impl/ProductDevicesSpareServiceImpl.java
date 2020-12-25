package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.ProductDevicesSpareDao;
import com.fw.service.device.service.ProductDevicesSpareService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件 service实现类
 */

@Service
public class ProductDevicesSpareServiceImpl implements ProductDevicesSpareService {

    @Autowired
    private ProductDevicesSpareDao productDevicesSpareDao;

    @Autowired
    private AuthUserUtil authUserUtil;

    /**
     * 新增和修改
     *
     * @param productDevicesSpare
     * @return
     */
    @Override
    public Result save(ProductDevicesSpare productDevicesSpare) {
        Integer flag = 0;

        String name = productDevicesSpare.getName();
        String code = productDevicesSpare.getCode();

        // 若id为null 新增备品备件
        if (CheckUtils.isNull(productDevicesSpare.getId())) {

            List<ProductDevicesSpare> list = productDevicesSpareDao.codeOrName(code, name);

            if (list.size() > 0) {
                return ResultUtils.error("备件的编码或名称已存在");
            }

            Integer user = authUserUtil.userId();
            productDevicesSpare.setCreateUser(user);
            productDevicesSpare.setUpdateUser(user);
            flag = productDevicesSpareDao.insert(productDevicesSpare);
        } else {

            // 修改
            ProductDevicesSpare spare = productDevicesSpareDao.selectById(productDevicesSpare.getId());
            if (spare == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }

            List<ProductDevicesSpare> list = productDevicesSpareDao.selectByNotInId(productDevicesSpare.getId());
            // 判断code和name有没有重复
            if (list.size() > 0) {
                for (ProductDevicesSpare devicesSpare : list) {
                    if (devicesSpare.getName().equals(productDevicesSpare.getName()) || devicesSpare.getCode().equals(productDevicesSpare.getCode())) {
                        return ResultUtils.error("备品备件编码或名称已存在！！！");
                    }
                }
            }

            Integer user = authUserUtil.userId();
            productDevicesSpare.setCreateUser(user);
            flag = productDevicesSpareDao.update(productDevicesSpare);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 单查 根据id查询信息
     *
     * @param id
     * @return
     */
    @Override
    public Result getProductDevicesSpare(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        ProductDevicesSpare devicesSpare = productDevicesSpareDao.selectById(id);
        if (devicesSpare != null) {
            devicesSpare = productDevicesSpareDao.selectById(id);
        } else {
            ResultUtils.error("结果不存在");
        }
        return ResultUtils.success(devicesSpare);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        for (String s : list) {
            ProductDevicesSpare devicesSpare = productDevicesSpareDao.selectById(Integer.valueOf(s));
            if (devicesSpare != null) {
                return ResultUtils.error("删除失败，请刷新页面");
            } else {
                flag = productDevicesSpareDao.delete(Integer.parseInt(s));
            }
        }

        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 分页全查
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result findList(String code, String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDevicesSpare> lists = productDevicesSpareDao.findList(code, name);

        PageInfo<ProductDevicesSpare> pageInfo = new PageInfo<>(lists);
        return ResultUtils.success(pageInfo);

    }
}
