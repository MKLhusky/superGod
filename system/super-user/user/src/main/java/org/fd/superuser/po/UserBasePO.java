package org.fd.superuser.po;

import com.system.supercommon.bean.BaseEntityPO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.fd.userenum.IdentityEnum;
import org.fd.userenum.SexEnum;
import org.fd.userenum.StatusEnum;

/**
 * @Description: 用户基本信息表映射对象
 * @Author: Mr. Dai
 * @Date: 2023/3/27 22:00
 **/


@Accessors(chain = true)
@Getter
@Setter
public class UserBasePO extends BaseEntityPO {

    private Long userId;

    private String userName;

    private String userPhoto;

    private String userNick;

    private String userAccount;

    private String password;

    private String passwordSalt;

    private String idCard;

    private SexEnum sex;

    private IdentityEnum identity;

    private Long businessIdentity;

    private Long businessUnique;

    private StatusEnum status;

}
