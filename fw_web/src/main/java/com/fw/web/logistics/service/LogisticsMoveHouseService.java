package com.fw.web.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsMoveHouse;
import com.fw.web.logistics.service.fbk.LogisticsMoveHouseServiceFbk;
import com.fw.web.logistics.service.fbk.LogisticsTakeOrderServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/logisticsMoveHouse", fallback = LogisticsMoveHouseServiceFbk.class)
public interface LogisticsMoveHouseService {

    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsMoveHouse logisticsMoveHouse);

    @GetMapping(value = "/updateMoveHouseStatus")
    Result updateMoveHouseStatus(@RequestParam(value = "id") Integer id);

    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "moveHouseNo", required = false) String moveHouseNo,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);
}
