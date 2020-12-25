package com.fw.service.enums;

/**
 * 编码枚举
 *
 * @author lpsong
 * @since 2020-12-01
 */
public enum CodeEnum {


    /**
     * 设备
     */
    DEVICES_01("EP01000001", "保养任务单号"),
    DEVICES_02("EP02000001", "设备维修"),
    DEVICES_03("EP03000001", "备件入库"),
    DEVICES_04("EP04000001", "备件出库"),
    DEVICES_05("EP05000001", "设备点检"),
    /**
     * 模具
     */
    MOULD_01("MJ01000001", "保养任务单号"),
    MOULD_02("MJ02000001", "模具维修"),
    MOULD_05("MJ05000001", "模具出库"),
    MOULD_06("MJ06000001", "模具入库"),
    MOULD_07("MJ07000001", "模具移库"),
    /**
     * 物流
     */
    LOGISTICS_01("WL01000001", "确认收货"),
    LOGISTICS_02("WL02000001", "外购入库"),
    LOGISTICS_03("WL03000001", "生产出库"),
    LOGISTICS_04("WL04000001", "外部出库"),
    LOGISTICS_05("WL05000001", "盘库"),
    LOGISTICS_06("WL06000001", "移库"),
    LOGISTICS_07("WL07000001", "退库"),
    LOGISTICS_08("WL08000001", "生产入库"),

    /**
     * 生产
     */
    PRODUCE_02("SC02000001", "投料"),

    /**
     * 审批
     */
    JBPM_SP("SP01000001", "流程审核"),


    /**
     * 分层审核
     */
    AUDIT_01("FC01000001", "流程审核"),

    /**
     * 质量检验
     */
    QUALITY_01("ZL01000001", "首件质检"),
    QUALITY_02("ZL02000001", "巡检质检"),
    QUALITY_03("ZL03000001", "尾件质检"),
    QUALITY_04("ZL04000001", "来料质检"),
    QUALITY_05("ZL05000001", "入库质检"),
    QUALITY_06("ZL06000001", "出库质检");


    private String code;
    private String stateInfo;

    CodeEnum(String code, String stateInfo) {
        this.code = code;
        this.stateInfo = stateInfo;
    }

    public String getCode() {
        return code;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
