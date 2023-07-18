package org.fd.superuser.user;

import org.fd.pojo.dto.UserLoginDTO;
import org.fd.pojo.vo.UserInfoVO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description: 用户内部服务接口
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:12
 */
public interface UserService {


    String getUser(UserLoginDTO userLoginDto);

    UserInfoVO selectUserInfo(Long userId);
}
