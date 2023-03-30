package org.fd.superuser.serviceimpl;

import com.system.supercommon.result.UserToken;
import com.system.supercommon.util.TokenUtil;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.fd.jdbc.QueryFieldSelect;
import org.fd.jdbc.QueryFiledExclude;
import org.fd.jdbc.SqlUtils;
import org.fd.pojo.dto.UserLoginDTO;
import org.fd.pojo.vo.UserInfoVO;
import org.fd.superuser.po.UserBasePO;
import org.fd.superuser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    public String getUser(UserLoginDTO userLoginDto) {

        UserBasePO userBasePO = new UserBasePO();
        BeanUtils.copyProperties(userLoginDto,userBasePO);

        //查询账号是否存在 顺带查询密码盐
        UserBasePO passwordSalt = sqlUtils.selectOne(new UserBasePO()
                        .setUserAccount(userBasePO.getUserAccount()),
                new QueryFieldSelect<>(UserBasePO::getPasswordSalt));

        if (ObjectUtils.isEmpty(passwordSalt)) {
            throw new RuntimeException("账号不存在");
        }

        //根据密码盐加密
        String password = DigestUtils.md5Hex(String.format("%s%s", userBasePO.getPassword(), passwordSalt.getPasswordSalt()));

        //根据账号和密码查询用户  [排除账户密码和盐]
        UserBasePO result = sqlUtils.selectOne(new UserBasePO()
                .setUserAccount(userBasePO.getUserAccount())
                .setPassword(password), new QueryFiledExclude<>(UserBasePO::getPassword, UserBasePO::getPasswordSalt));

        if(ObjectUtils.isEmpty(result)){
            throw new RuntimeException("密码错误");
        }

        //根据用户信息创建token  [过期时间2小时]
        String token = TokenUtil.createToken(new UserToken().setUserId(result.getUserId()),2, TimeUnit.HOURS);

        return token;
    }

    @Override
    public UserInfoVO selectUserInfo(Long userId) {
        UserBasePO userBasePO = sqlUtils.selectOne(new UserBasePO().setUserId(userId));
        //不等于null 返回视图对象
        if (ObjectUtils.isNotEmpty(userBasePO)) {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(userBasePO,userInfoVO);
            return userInfoVO;
        }
        return null;
    }
}
