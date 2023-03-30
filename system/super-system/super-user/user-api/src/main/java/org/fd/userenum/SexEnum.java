package org.fd.userenum;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description: 用户性别枚举
 * @Author: Mr. Dai
 * @Date: 2023/3/27 22:03
 **/
public enum SexEnum {



    WOMAN("女",0),
    MAN("男",1),
    UNKNOWN("未知",2),
    EMPTY("",-1);

    //显示值
    private String label;
    //存储值
    private Integer code;

    SexEnum(String label, Integer code){
        this.label=label;
        this.code=code;
    }


    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }


}
