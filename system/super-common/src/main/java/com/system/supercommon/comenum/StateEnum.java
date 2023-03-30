package com.system.supercommon.comenum;

/**
 * @Description: 全局状态枚举
 * @Author: Mr. Dai
 * @Date: 2023/3/30 21:24
 **/
public enum StateEnum {


    ABNORMAL("异常的",1),
    NORMAL("正常的",0);

    StateEnum(String label, Integer code){
        this.label=label;
        this.code=code;
    }

    private Integer code;

    private String label;

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
