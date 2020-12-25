package com.fw.service.basic.service.impl;

import com.fw.domain.BaseFile;
import com.fw.domain.Result;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.basic.service.AttachmentService;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/3/31
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    /**
     * 保存附件记录
     * @param baseFile
     * @return
     */
    @Override
    public Result saveRecord(BaseFile baseFile) {
        int num = attachmentDao.insert(baseFile);
        return num>0? ResultUtils.success():ResultUtils.failure();
    }

    /**
     * 根据关联数据查询附件
     * @param dataId
     * @return
     */
    public List<BaseFile> findByDataId(String dataId){
        return attachmentDao.findByDataId(dataId, attachmentPath);
    }

    /**
     * 根据附件ID查询附件
     * @param id
     * @return
     */
    public BaseFile findById(Integer id){
        return attachmentDao.findById(id, attachmentPath);
    }

    /**
     * 根据附件ID删除附件
     * @param id
     * @return
     */
    public Result deleteById(Integer id){
        return attachmentDao.deleteById(id)>0?ResultUtils.success():ResultUtils.failure();
    }

    /**
     * 根据关联数据删除附件
     * @param dataId
     * @return
     */
    public  Result deleteByDataId(String dataId){
        return attachmentDao.deleteByDataId(dataId)>0?ResultUtils.success():ResultUtils.failure();
    }
}
