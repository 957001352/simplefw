package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.web.device.service.fbk.ProductDevicesSpareServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件web service
 */
@FeignClient(value = "fw-service/productDevicesSpare", fallback = ProductDevicesSpareServiceFbk.class)
public interface ProductDevicesSpareService {

    /**
     * 新增和修改备品备件
     *
     * @param productDevicesSpare
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody ProductDevicesSpare productDevicesSpare);

    /**
     * 根据id查看备品备件信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getProductDevicesSpare")
    Result getProductDevicesSpare(@RequestParam(value = "id") Integer id);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 列表全查
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
