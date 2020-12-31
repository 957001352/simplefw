package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceReworkRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 二次加工报工记录
 *
 * @author lpsong
 * @since 2020-12-15
 */
@Repository
public interface ProduceReworkRecordDao {


    Integer insert(ProduceReworkRecord produceReworkRecord);


    List<ProduceReworkRecord> findList(@Param(value = "planReworkId") Integer planReworkId);

    /**
     * 批量修改状态
     *
     * @param list
     * @return
     */
    Integer batchUpdate(@Param("list") List<ProduceReworkRecord> list);

    /**
     * 修改报工单的状态
     */
    Integer updateBankStatus(@Param("ids") String ids,
                        @Param("bankStatus") Integer bankStatus);

}
