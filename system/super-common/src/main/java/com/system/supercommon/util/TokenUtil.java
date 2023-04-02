package com.system.supercommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.system.supercommon.funcbean.UserToken;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description: token 工具类
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:49
 */
public class TokenUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC512("fandaiwenqipengmeng");


    /**
     * @Description:   创建token 不限时间
     * @param userToken
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/29 18:10
     */
    public static String createToken(UserToken userToken){
        return createToken(userToken,-1,null);
    }


    /**
     * @Description:   创建过期token  没有单位 或者 time小于0 都代表不限时间的token
     * @param userToken
     * @param time 时间值
     * @param unit 单位
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/30 14:04
     */
    public static String createToken(UserToken userToken, long time,TimeUnit unit){
        JWTCreator.Builder builder = JWT.create()
                .withClaim("userId", userToken.getUserId());
        if(time<0 || null==unit){
            return builder.sign(ALGORITHM);
        }
        long expiresAt = TimeUtil.parsTimeToMillisecond(time, unit);

        Date date = new Date(new Date().getTime() + expiresAt);
        return builder.withExpiresAt(date).sign(ALGORITHM);
    }


    /**
     * @Description:   验证token
     * @param token
     * @return boolean
     * @author Mr. Dai
     * @date 2023/3/29 18:10
     */
    public static boolean verifier(String token){
        try {
            JWT.require(ALGORITHM)
                    .build().verify(token);
        } catch (JWTVerificationException exception){
            return false;
        }
        return true;
    }

    /**
     * @Description:  根据token获取token对象
     * @param token
     * @return com.system.supercommon.result.UserToken
     * @author Mr. Dai
     * @date 2023/3/29 18:11
     */
    public static UserToken getUserToken(String token){
        if (StringUtils.isEmpty(token)){
            throw new RuntimeException("token为空");
        }
        if (token.contains("Bearer ")){
            token = token.replace("Bearer ", "");
        }
        UserToken userToken = new UserToken();
        DecodedJWT verify;
        try {
            verify=JWT.require(ALGORITHM)
                    .build().verify(token);

            userToken.setUserId(verify.getClaim("userId").asLong());
        } catch (JWTVerificationException exception){
            throw new RuntimeException("token过期");
        }
        return userToken;
    }
}
