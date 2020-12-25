package com.fw.service.device.dao;


import com.fw.entity.device.DevicesProblem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevicesProblemDao {

    Integer insert(DevicesProblem devicesProblem);

    Integer update(DevicesProblem devicesProblem);

    Integer delete(List<String> list);

    List<DevicesProblem> findList(String name,String content,Integer status,Integer type);

    List<DevicesProblem> findListById(List<String> list);

    DevicesProblem findProblemById(@Param("id") Integer id);
}
