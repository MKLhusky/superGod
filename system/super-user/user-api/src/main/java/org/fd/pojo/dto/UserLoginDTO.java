package org.fd.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.fd.userenum.LoginTypeEnum;

/**
 * Description: 用户登录查询对象
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:30
 */
@Getter
@Setter
@Schema(name = "账户登陆")
public class UserLoginDTO {

    @NotNull(message = "用户账号不能为空")
    @Schema(name = "用户账号")
    private String userAccount;

    @Schema(name = "用户密码")
    @NotNull(message = "用户密码不能为空")
    @Size(min = 6,message = "密码最少6位数")
    private String password;

    @Schema(name = "登陆类别")
    private LoginTypeEnum loginStatus;

}
