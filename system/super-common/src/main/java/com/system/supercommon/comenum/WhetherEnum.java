package com.system.supercommon.comenum;

public enum WhetherEnum {

    NO("否", 0),
    YES("是", 1);

    private String label;
    private Integer code;

    WhetherEnum(String label, Integer code){
        this.label = label;
        this.code = code;
    }

    public String getLabel(){
        return this.label;
    }

    public Integer getCode(){
        return this.code;
    }
}
