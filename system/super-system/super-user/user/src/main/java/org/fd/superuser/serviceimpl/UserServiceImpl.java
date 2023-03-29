package org.fd.superuser.serviceimpl;

import jakarta.annotation.Resource;
import org.fd.jdbc.SqlUtils;
import org.fd.pojo.dto.UserLoginDto;
import org.fd.superuser.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description: 用户服务实现
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SqlUtils sqlUtils;

    @Override
    public String getUser(UserLoginDto userLoginDto) {
        return null;
    }
}
