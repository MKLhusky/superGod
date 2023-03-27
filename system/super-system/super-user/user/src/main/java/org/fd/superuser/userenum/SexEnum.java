package org.fd.superuser.userenum;

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

    /**
     * @Author: Mr. Dai
     * @Description:  根据code值获取映射的枚举对象
     * @Date: 22:07 2023/3/27
     * @param code
     **/
    public static SexEnum getSex(Integer code){
        Optional<SexEnum> userSex = Arrays.asList(SexEnum.values()).stream().filter(x -> x.getCode().equals(code)).findFirst();
        return userSex.isPresent()?userSex.get(): SexEnum.EMPTY;
    }

}
