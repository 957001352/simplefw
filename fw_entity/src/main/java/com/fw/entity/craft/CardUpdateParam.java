package com.fw.entity.craft;

import lombok.Data;

/**
 * 工艺卡修改参数记录实体
 */
@Data
public class CardUpdateParam {
    private String id;
    private String tmUpdateOld;// 旧值
    private String tmUpdateNew;// 新值
    private String tmUpdateDateTime;// 修改时间
    private String tmCraftId;// 模具编号
    private String tmUpdateContent;// 修改参数
    private String deviceCode;// 设备编号
    private String createTime;// 创建时间
}
