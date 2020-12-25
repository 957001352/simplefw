package com.fw.service.craft.service;

import com.fw.domain.Result;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.e2c.DevicesItemVo;

public interface CraftCardService {
    /**
     * 新增
     * @param cardParams
     * @return
     */
    Result save(CardParams cardParams);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 工艺卡参数列表查询
     * @param
     * @return
     */
    Result findList(String partCode,String partName,Integer productId,Integer pageNum,Integer pageSize);


    /**
     * 工艺卡列表
     * @param
     * @return
     */
    Result findListCard();

    /**
     * 工艺卡变更
     * @param
     * @return
     */
    Result saveCardLog(CardLog cardLog);

    /**
     * 工艺卡的变更列表
     * @param
     * @return
     */
    Result findCardLog(Integer cardLogId,Integer paramId);

    /**
     * 工艺卡变更通过
     */
    Result cardChangePass(Integer id);

    /**
     * 根据零件编码查询相关设备
     */
    Result findDeviceByPartCode(String partCode);

    /**
     * 查询设备正在生产的生产指令所对应的工艺卡
     */
    Result findIngCardByDeviceId(DevicesItemVo devicesItemVo);
    /**
     * 设定值修改记录
     */
    Result findUpdateParamLog(String deviceCode, Integer pageNum, Integer pageSize);

    /**
     * 根据id查询工艺卡参数
     */
    Result findById(Integer id);

    /**
     * 根据id查询工艺卡变更记录参数
     */
    Result findCardLogById(Integer id);
}
