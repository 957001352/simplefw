package com.fw.entity.logistics;

import lombok.Data;

import java.util.List;

/**
 * 包装前端传传入参数
 * @param <T>
 */
@Data
public class InfoBox<T> {
    private Integer id;
    private List<T> data;
}
