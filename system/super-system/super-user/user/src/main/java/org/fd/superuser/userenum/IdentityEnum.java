package org.fd.superuser.userenum;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description: 系统身份枚举
 * @Author: Mr. Dai
 * @Date: 2023/3/27 22:11
 **/


public enum IdentityEnum {

    EMPTY("空",-1),
    STAFF("普通用户",2),
    MANAGER("管理员",1),
    ADMIN("超级管理员",0);

    //显示值
    private String label;
    //存储值
    private Integer code;

    IdentityEnum(String label, Integer code){
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
    public static IdentityEnum getIdentity(Integer code){
        Optional<IdentityEnum> identity = Arrays.asList(IdentityEnum.values()).stream().filter(x -> x.getCode().equals(code)).findFirst();
        return identity.isPresent()?identity.get(): IdentityEnum.EMPTY;
    }
}
