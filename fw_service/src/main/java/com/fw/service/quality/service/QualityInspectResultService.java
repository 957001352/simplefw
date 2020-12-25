package com.fw.service.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 检验结果 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
public interface QualityInspectResultService {


    Result findHistoryResultByOfon(String ofNo);

}
