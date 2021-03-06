package com.fw.web.basic.service.fbk;

import com.fw.domain.BaseFile;
import com.fw.domain.Result;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.basic.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/3/31
 */
@Service
public class AttachmentServiceFbk implements AttachmentService {

    @Override
    public Result upload(MultipartFile file, boolean isAdd, String dataId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveRecord(BaseFile baseFile) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public List<BaseFile> findByDataId(String dataId) {
        return null;
    }

    @Override
    public BaseFile findById(Integer id) {
        return null;
    }

    @Override
    public Result deleteById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result deleteByDataId(String dataId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}