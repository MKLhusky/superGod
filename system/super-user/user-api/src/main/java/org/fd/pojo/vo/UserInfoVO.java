package org.fd.pojo.vo;

import com.system.supercommon.bean.BaseEntityVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.fd.userenum.IdentityEnum;
import org.fd.userenum.SexEnum;
import org.fd.userenum.StatusEnum;

/**
 * Description: 用户详情信息
 *
 * @author Mr. Dai
 * @date 2023/3/30 11:53
 */
@Accessors(chain = true)
@Getter
@Setter
@Schema(name = "用户基础信息")
public class UserInfoVO  extends BaseEntityVO {

    @Schema(name = "用户id")
    private Long userId;
    @Schema(name = "用户姓名")
    private String userName;
    @Schema(name = "用户头像或照片")
    private String userPhoto;
    @Schema(name = "用户昵称")
    private String userNick;
    @Schema(name = "用户账号")
    private String userAccount;
    @Schema(name = "用户身份证")
    private String idCard;
    @Schema(name = "用户性别")
    private SexEnum sex;
    @Schema(name = "身份标识")
    private IdentityEnum identity;
    @Schema(name = "业务身份标识")
    private Long businessIdentity;
    @Schema(name = "业务唯一标识")
    private Long businessUnique;
    @Schema(name = "数据状态")
    private StatusEnum status;
}
