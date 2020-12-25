package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsBackHouse;
import com.fw.web.logistics.service.fbk.LogisticsBackHouseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 退库管理Feign接口
 *
 * @author xkliu
 * @date 2020/11/12
 */
@FeignClient(value = "fw-service/logisticsBackHouse", fallback = LogisticsBackHouseServiceFbk.class)
public interface LogisticsBackHouseService {

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(
            @RequestParam(value = "houseNo", required = false) String houseNo,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "productOrder", required = false) String productOrder,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 退库明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getBackHouseDetail")
    Result getBackHouseDetail(@RequestParam(value = "id") Integer id);

    /**
     * 执行退库
     *
     * @param logisticsBackHouse
     * @return
     */
    @PostMapping("/cancellingStocks")
    Result cancellingStocks(@RequestBody LogisticsBackHouse logisticsBackHouse);

    /**
     * 历史列表查询
     *
     * @param code
     * @param productCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findHistoryList")
    Result findHistoryList(@RequestParam(value = "houseNo", required = false) String houseNo,
                           @RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "startExecuteTime", required = false) String startExecuteTime,
                           @RequestParam(value = "endExecuteTime", required = false) String endExecuteTime,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 查看退库明细带分页
     *
     * @param id
     * @return
     */
    @GetMapping("/backHouseDetailPage")
    Result backHouseDetailPage(@RequestParam(value = "id") Integer id,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 生产新增退库任务
     *
     * @param logisticsBackHouse
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody LogisticsBackHouse logisticsBackHouse);

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);
}
