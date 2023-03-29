package org.fd.superuser.service;

import org.fd.pojo.dto.UserLoginDto;

/**
 * Description: 用户内部服务接口
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:12
 */
public interface UserService {


    String getUser(UserLoginDto userLoginDto);
}
