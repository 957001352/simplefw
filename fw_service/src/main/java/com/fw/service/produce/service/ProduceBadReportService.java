package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBadReport;

/**
 * 不良上报 服务类
 *
 * @author xkliu
 * @since 2020-12-08
 */
public interface ProduceBadReportService {

    /**
     * 新增
     *
     * @param produceBadReport
     * @return
     */
    Result save(ProduceBadReport produceBadReport);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(Integer moldingId, Integer pageNum, Integer pageSize);
}
