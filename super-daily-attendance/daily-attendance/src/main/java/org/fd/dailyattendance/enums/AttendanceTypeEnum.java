package org.fd.dailyattendance.enums;

public enum AttendanceTypeEnum {

    STUDY("学习打卡", 0),
    EXERCISE("健身打卡", 1);

    private String label;

    private Integer code;

    AttendanceTypeEnum(String label, Integer code) {
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
