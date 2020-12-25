package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldUseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    模具使用Dao层
* @Author:         gchen
* @CreateDate:     2020/10/27 16:52
*/
@Repository
public interface MouldUseRecordDao {


    /**
     * 新增
     * @param mouldUseRecord
     * @return
     */
    Integer insert(MouldUseRecord mouldUseRecord);

    /**
     * 更新
     * @param mouldUseRecord
     */
    Integer update(MouldUseRecord mouldUseRecord);

    /**
     * 查询
     * @param status  模具编码
     */
    List<MouldUseRecord> findList(@Param("mouldCode") String mouldCode,
                                  @Param("status") Integer status);

    /**
     *  根据id查询
     */
    MouldUseRecord findOne(Integer id);
}
