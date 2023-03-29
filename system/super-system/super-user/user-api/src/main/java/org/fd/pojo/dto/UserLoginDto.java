package org.fd.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import org.fd.pojo.LoginStatusEnum;

/**
 * Description: 用户登录查询对象
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:30
 */
@Getter
@Setter
public class UserLoginDto {

    private String userAccount;

    private String password;

    private LoginStatusEnum loginStatus;

}
