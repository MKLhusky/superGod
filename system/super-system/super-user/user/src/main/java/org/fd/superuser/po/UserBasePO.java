package org.fd.superuser.po;

import com.system.supercommon.bean.ParentPO;
import lombok.Getter;
import lombok.Setter;
import org.fd.superuser.userenum.IdentityEnum;
import org.fd.superuser.userenum.SexEnum;
import org.fd.superuser.userenum.StatusEnum;

/**
 * @Description: 用户基本信息表映射对象
 * @Author: Mr. Dai
 * @Date: 2023/3/27 22:00
 **/

@Getter
@Setter
public class UserBasePO extends ParentPO {

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
