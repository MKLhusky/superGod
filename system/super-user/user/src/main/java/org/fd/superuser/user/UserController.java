package org.fd.superuser.user;

import com.system.supercommon.funcbean.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.fd.pojo.dto.UserLoginDTO;
import org.fd.pojo.vo.UserInfoVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 用户controller
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:11
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "UserController",description = "用户模块")
public class UserController {

    private final UserService userService;


    /**
     * @Description:   根据账号密码验证 成功返回token
     * @param userLoginDto
     * @return com.system.supercommon.result.R
     * @author Mr. Dai
     * @date 2023/3/29 17:47
     */
    @Operation(summary = "用户根据账号密码登陆",responses = {
            @ApiResponse(description = "成功",responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = String.class))}
            )
    })
    @PostMapping("/loginPassword")
    public R  getUser(@RequestBody @Validated UserLoginDTO userLoginDto){
        return R.success(userService.getUser(userLoginDto));
    }


    /**
     * @Description:   根据用户id 获取用户信息详情
     * @param userId
     * @return com.system.supercommon.result.R
     * @author Mr. Dai
     * @date 2023/3/30 15:10
     */
            /*方法描述*/
    @Operation(summary = "根据用户id查询用户信息",responses = {
            /*成功描述*/
            @ApiResponse(description = "成功",responseCode = "200",
                    /*返回对象*/
                    content = {@Content(schema = @Schema(implementation = UserInfoVO.class))}),

     /*参数描述*/
    },parameters = {
            @Parameter(name = "userId",description = "用户主键id")
    })
    @GetMapping("selectUserInfo/{userId}")
    public R selectUserInfo(@PathVariable("userId")Long userId){
        return R.success(userService.selectUserInfo(userId));
    }
}
