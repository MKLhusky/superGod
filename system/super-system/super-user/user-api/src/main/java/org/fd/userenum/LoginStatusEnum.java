package org.fd.userenum;

/**
 * Description: 登录状态 (待扩展) //todo
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:31
 */
public enum LoginStatusEnum {

    APP_PASSWORD("app密码登录",1),
    PC_PASSWORD("pc密码登录",0);

    LoginStatusEnum(String label,Integer code){
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
