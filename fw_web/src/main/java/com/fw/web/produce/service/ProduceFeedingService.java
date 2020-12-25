package com.fw.web.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceFeeding;
import com.fw.web.produce.service.fbk.ProduceFeedingServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des: 投料Feign接口
 * @author: xkliu
 * @date: 2020/12/14
 */
@FeignClient(value = "fw-service/produceFeeding", fallback = ProduceFeedingServiceFbk.class)
public interface ProduceFeedingService {

    /**
     * 新增投料
     *
     * @param produceFeeding
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody ProduceFeeding produceFeeding);

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "productOrder", required = false) String productOrder,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 扫码获取出库数量
     *
     * @return
     */
    @GetMapping(value = "/getStorageCount")
    Result getStorageCount(@RequestParam(value = "storageDetailId") Integer storageDetailId);

}