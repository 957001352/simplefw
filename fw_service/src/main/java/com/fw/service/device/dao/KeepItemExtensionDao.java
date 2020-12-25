package com.fw.service.device.dao;

import com.fw.entity.device.KeepItemExtension;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 保养项目扩展 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Repository
public interface KeepItemExtensionDao {

    /**
     * 批量插入保养项目扩展
     *
     * @param keepItemExtensions
     * @return
     */
    Integer batchInset(@Param("keepItemExtensions") List<KeepItemExtension> keepItemExtensions);

    /**
     * 批量修改保养项目扩展
     *
     * @param list
     * @return
     */
    Integer batchUpdate(@Param("list") List<KeepItemExtension> list);

    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);

    /**
     * 根据保养项目获取一条扩展信息
     * @param id
     * @return
     */
    List<KeepItemExtension> selectByKeepItemId(Integer id);
}
