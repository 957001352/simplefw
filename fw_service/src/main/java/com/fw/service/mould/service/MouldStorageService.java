package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldSpareIn;
import com.fw.entity.mould.MouldSpareOut;
import com.fw.entity.mould.MouldStorageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description: 模具库存管理
 * @Author: fchai
 * @Date: 2020/10/26 14:55
 */
public interface MouldStorageService {


    /**
     *新建模具备品备件基础信息
     * @param mouldStorageInfo
     * @return
     */
    Result insertSpareInfo(MouldStorageInfo mouldStorageInfo);


    /**
     * 删除模具备品备件基础信息
     * @param ids
     * @return
     */
    Result deleteSpareInfo(String ids);

    /**
     * 获取模具备品备件基础信息
     * @param code
     * @param name
     * @return
     */
    Result findListSpareInfo(String code,String name, Integer pageNum, Integer pageSize);

    /**
     * 模具入库
     *
     * @param mouldSpareIn
     * @return
     */
    Result mouldIn(MouldSpareIn mouldSpareIn);


    /**
     * 模具出库
     *
     * @param mouldSpareOut
     * @return
     */
    Result mouldOut(MouldSpareOut mouldSpareOut);


    /**
     * 查询出入库信息
     *
     * @param outInNo
     * @param operate
     * @param startDate
     * @param endDate
     * @return
     */
    Result getInOutInfo(String outInNo, Integer operate, String startDate, String endDate,Integer pageNum,Integer pageSize);

    /**
     * 查询备品备件库存信息
     * @param name
     * @return
     */
    Result findMouldSpareStoreList(String name);


    /**
     * 根据模具维修编码查询备品备件使用情况
     * @param code
     * @return
     */
    Result findMouldSpareStoreAndUse(String code);

    /**
     * 根据出入库ID查询出库详情
     * @param id  id
     * @param operate 0入库 1出库
     * @return
     */
    Result findOutInDetail(Integer id,Integer operate);


}
