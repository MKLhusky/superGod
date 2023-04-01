package org.fd.userenum;

import com.system.supercommon.util.EnumUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description: 用户状态枚举
 * @Author: Mr. Dai
 * @Date: 2023/3/27 22:16
 **/

public enum StatusEnum {

    EXCEPTION("异常",2),
    FREEZE("冻结",1),
    NORMAL("正常的",0);

    //显示值
    private String label;
    //存储值
    private Integer code;

    StatusEnum(String label, Integer code){
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
