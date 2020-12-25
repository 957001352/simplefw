package com.fw.web.borad.service;

import com.fw.domain.Result;
import com.fw.entity.board.InfoBox;
import com.fw.web.borad.service.fbk.BoradServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fw-service/borad", fallback = BoradServiceFbk.class)
public interface BoradService {
    /**
     * 看板总览
     */
    @PostMapping("/overView")
    Result overView(@RequestBody InfoBox infoBox);
}
