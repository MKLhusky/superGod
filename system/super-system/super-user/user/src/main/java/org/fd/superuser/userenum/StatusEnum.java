package org.fd.superuser.userenum;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description: 用户状态枚举
 * @Author: Mr. Dai
 * @Date: 2023/3/27 22:16
 **/

public enum StatusEnum {

    EMPTY("",-1),
    EXCEPTION("异常",3),
    FREEZE("冻结",2),
    DELETE("删除",1),
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

    /**
     * @Author: Mr. Dai
     * @Description:  根据code值获取映射的枚举对象
     * @Date: 22:07 2023/3/27
     * @param code
     **/
    public static StatusEnum getStatus(Integer code){
        Optional<StatusEnum> status = Arrays.asList(StatusEnum.values()).stream().filter(x -> x.getCode().equals(code)).findFirst();
        return status.isPresent()?status.get(): StatusEnum.EMPTY;
    }
}
