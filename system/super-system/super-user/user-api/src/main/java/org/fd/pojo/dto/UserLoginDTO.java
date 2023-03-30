package org.fd.pojo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.fd.userenum.LoginStatusEnum;

/**
 * Description: 用户登录查询对象
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:30
 */
@Getter
@Setter
public class UserLoginDTO {

    @NotNull(message = "用户账号不能为空")
    private String userAccount;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6,message = "密码最少6位数")
    private String password;

    private LoginStatusEnum loginStatus;

}
