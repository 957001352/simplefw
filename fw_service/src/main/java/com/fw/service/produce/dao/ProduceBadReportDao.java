package com.fw.service.produce.dao;

import com.fw.entity.produce.ProduceBadReport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 不良上报 Dao 接口
 *
 * @author xkliu
 * @since 2020-12-08
 */
@Repository
public interface ProduceBadReportDao {

    /**
     * 新增
     *
     * @param produceBadReport
     * @return
     */
    Integer insert(ProduceBadReport produceBadReport);

    /**
     * 修改
     *
     * @param produceBadReport
     * @return
     */
    Integer update(ProduceBadReport produceBadReport);

    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);

    /**
     * 根据id查询ProduceBadReport对象
     *
     * @param id
     * @return
     */
    ProduceBadReport selectById(Integer id);

    /**
     * 列表查询
     *
     * @return
     */
    List<ProduceBadReport> findList(Integer moldingId);
}
