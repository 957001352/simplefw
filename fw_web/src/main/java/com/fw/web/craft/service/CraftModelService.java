package com.fw.web.craft.service;


import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import com.fw.web.craft.service.fbk.CraftModelServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/craftModel", fallback = CraftModelServiceFbk.class)
public interface CraftModelService {

    @PostMapping(value = "/save")
    public Result save(@RequestBody CraftModel craftModel);


    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("ids") String ids);


    @GetMapping(value = "/findList")
    public Result findList(
            @RequestParam(value = "productCode", required = false) String productCode,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "modelType", required = false) Integer modelType,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
    /**
     * 根据物料长代码查询工艺卡模型
     * @param productCode
     * @return
     */
    @GetMapping(value = "/findByProductCode")
    Result findByProductCode(@RequestParam(value = "productCode", required = false) String productCode);

}
