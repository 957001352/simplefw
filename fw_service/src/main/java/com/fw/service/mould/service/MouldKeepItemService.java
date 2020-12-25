package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepItem;

/**
 * 模具保养项目 服务类
 *
 * @author xkliu
 * @date 2020/10/27
 */
public interface MouldKeepItemService {


    /**
     * 查看模具保养项目
     *
     * @param id
     * @return
     */
    Result getMouldKeepItem(Integer id);

    /**
     * 新增
     *
     * @param mouldKeepItem
     * @return
     */
    Result save(MouldKeepItem mouldKeepItem);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, Integer pageNum, Integer pageSize);


}
