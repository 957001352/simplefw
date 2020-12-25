package com.fw.service.basic.dao;

import com.fw.domain.BaseFile;
import com.fw.entity.e2c.PdaLoginLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author gchen
 * @Date 2020/3/31
 */
@Repository
public interface PdaLoginLogDao {
    /**
     * 新增
     */
    Integer insert(PdaLoginLog pdaLoginLog);

    /**
     * 修改
     */
    Integer update(PdaLoginLog pdaLoginLog);

    /**
     * 查询
     */
    List<PdaLoginLog> findList();

    /**
     * 根据用户和设备查询未退出的记录
     */
    PdaLoginLog findUnFinishLog(@Param("userId") Integer userId,
                                @Param("productDevicesId") Integer productDevicesId);
}
