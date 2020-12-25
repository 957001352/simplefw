package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceBake;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface ProduceBakeDao {

    Integer insert(ProduceBake produceBake);

    List<LinkedHashMap<String, Object>> findList();
}
