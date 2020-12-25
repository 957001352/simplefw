package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsCheckHouse;
import com.fw.web.logistics.service.fbk.LogisticsCheckHouseServiceFbk;
import com.fw.web.logistics.service.fbk.LogisticsUpHouseServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 盘库
 *
 * @author lpsong
 * @since 2020-11-12
 */
@FeignClient(value = "fw-service/logisticsCheckHouse", fallback = LogisticsCheckHouseServiceFbk.class)
public interface LogisticsCheckHouseService {

    /**
     * 新增
     *
     * @param logisticsCheckHouse
     * @return
     */
    @PostMapping(value = "/insert")
    Result insert(@RequestBody LogisticsCheckHouse logisticsCheckHouse);

    /**
     * 盘库
     *
     * @param logisticsCheckHouse
     * @return
     */
    @PostMapping(value = "/update")
    Result update(@RequestBody LogisticsCheckHouse logisticsCheckHouse);

    /**
     * 关闭
     *
     * @param id
     * @return result
     */
    @GetMapping(value = "/updateStatus")
    Result updateStatus(@RequestParam(value = "id") Integer id);

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "houseNo", required = false) String houseNo,
                    @RequestParam(value = "checkTime", required = false) String checkTime,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "status", required = false) Integer checkResult,
                    @RequestParam(value = "checkUser", required = false) Integer checkUser,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 盘库明细列表查询
     *
     * @param checkHouseId
     * @return
     */
    @GetMapping("/findDetailList")
    Result findDetailList(@RequestParam(value = "checkHouseId", required = false) Integer checkHouseId);

    /**
     * 根据库位查询要盘库物料
     *
     * @param locationId
     * @return
     */
    @GetMapping("/findStoragePorductList")
    Result findStoragePorductList(@RequestParam(value = "locationId", required = false) Integer locationId);

    @GetMapping("/findTreeList")
    Result findTreeList(@RequestParam(value = "name", required = false) String name);
}
