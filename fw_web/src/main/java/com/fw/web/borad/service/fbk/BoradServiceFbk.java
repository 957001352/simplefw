package com.fw.web.borad.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.board.InfoBox;
import com.fw.enums.ResultEnum;
import com.fw.web.borad.service.BoradService;
import com.fw.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class BoradServiceFbk implements BoradService {
    @Override
    public Result overView(InfoBox infoBox) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
