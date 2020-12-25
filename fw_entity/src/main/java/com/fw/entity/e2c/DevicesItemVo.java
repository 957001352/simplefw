package com.fw.entity.e2c;

import com.fw.entity.device.DevicesExtension;
import lombok.Data;

/**
 * @des 公共的设备对象
 * @author xkliu
 * @date 2020/10/20
 */
@Data
public class DevicesItemVo {

    /**
     * Id
     */
    private String id;

    /**
     * 设备类型名字
     */
    private String name;

    /**
     * 设备编码
     */
    private String code;

    /**
     * 设备编码状态 0正常 1禁用 2 删除
     */
    private Integer status;

    /**
     * 租户
     */
    private Integer tenantId;

    /**
     * 类型管理
     */
    private String classifyId;
    /**
     * 类型名称
     */
    private String classifyName;

    /**
     * 设备履历参数
     */
    private DevicesExtension devicesExtension;

    /**
     * 设备类型
     */
    private DevicesClassify classifySet;

}
