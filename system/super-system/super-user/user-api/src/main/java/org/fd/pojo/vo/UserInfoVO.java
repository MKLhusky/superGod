package org.fd.pojo.vo;

import com.system.supercommon.bean.ParentVO;
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
public class UserInfoVO  extends ParentVO {
    private Long userId;

    private String userName;

    private String userPhoto;

    private String userNick;

    private String userAccount;

    private String idCard;

    private SexEnum sex;

    private IdentityEnum identity;

    private Long businessIdentity;

    private Long businessUnique;

    private StatusEnum status;
}
