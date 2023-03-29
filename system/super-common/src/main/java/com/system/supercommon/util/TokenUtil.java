package com.system.supercommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.system.supercommon.result.UserToken;

import java.util.Date;

/**
 * Description: token 工具类
 *
 * @author Mr. Dai
 * @date 2023/3/29 17:49
 */
public class TokenUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC512("fandaiwenqipengmeng");

    //过期时间
    private static final Long EXPIRES=1000*60*60*1L;


    /**
     * @Description:   创建token
     * @param userToken
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/29 18:10
     */
    public static String createToken(UserToken userToken){

        return JWT.create()
                .withClaim("userId",userToken.getUserId())
                .withExpiresAt(new Date(new Date().getTime()+EXPIRES))
                .sign(ALGORITHM);
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
