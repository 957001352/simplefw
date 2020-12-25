package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDownHouse;
import com.fw.entity.logistics.LogisticsUpHouse;
import com.fw.web.logistics.service.fbk.LogisticsUpHouseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 上架管理Feign接口
 *
 * @author xkliu
 * @date 2020/11/10
 */
@FeignClient(value = "fw-service/logisticsUpHouse", fallback = LogisticsUpHouseServiceFbk.class)
public interface LogisticsUpHouseService {


    /**
     * 新增上架
     *
     * @param logisticsUpHouse
     * @return
     */
    @PostMapping(value = "/upHouse")
    Result upHouse(@RequestBody LogisticsUpHouse logisticsUpHouse);


    /**
     * 新增下架
     *
     * @param logisticsDownHouse
     * @return
     */
    @PostMapping(value = "/downHouse")
    Result downHouse(@RequestBody LogisticsDownHouse logisticsDownHouse);


    /**
     * 上架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findUpList")
    Result findUpList(@RequestParam(value = "code", required = false) String code,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 下架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findDownList")
    Result findDownList(@RequestParam(value = "code", required = false) String code,
                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 查看上架明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getUpHouse")
    Result getUpHouse(@RequestParam(value = "id") Integer id);

    /**
     * 查看下架明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getDownHouse")
    Result getDownHouse(@RequestParam(value = "id") Integer id);


    /**
     * 上下架记录
     *
     * @param code
     * @param storageName
     * @param startCreateTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findAllList")
    Result findAllList(@RequestParam(value = "code", required = false) String code,
                       @RequestParam(value = "storageName", required = false) String storageName,
                       @RequestParam(value = "startCreateTime", required = false) String startCreateTime,
                       @RequestParam(value = "endCreateTime", required = false) String endCreateTime,
                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}