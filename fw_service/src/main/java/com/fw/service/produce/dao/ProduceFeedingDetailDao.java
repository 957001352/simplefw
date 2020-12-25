package com.fw.service.produce.dao;

import com.fw.entity.produce.ProduceFeedingDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 投料明细 Dao 接口
 *
 * @author xkliu
 * @since 2020-12-14
 */
@Repository
public interface ProduceFeedingDetailDao {

    /**
     * 批量插入
     *
     * @param produceFeedingDetail
     * @return
     */
    Integer batchInsert(@Param("produceFeedingDetail")List<ProduceFeedingDetail> produceFeedingDetail);

    /**
     * 根据投料id获取明细
     *
     * @param id
     * @return
     */
    List<ProduceFeedingDetail> findProduceFeedingDetails(Integer id);
}
