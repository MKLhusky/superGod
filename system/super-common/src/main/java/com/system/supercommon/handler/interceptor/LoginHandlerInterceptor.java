package com.system.supercommon.handler.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.system.supercommon.funcbean.UserToken;
import com.system.supercommon.handler.login.TransRequestContextHolder;
import com.system.supercommon.util.StringUtils;
import com.system.supercommon.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        try{
            if (StringUtils.isNotEmpty(token)){
                UserToken userToken = TokenUtil.getUserToken(token);
                TransRequestContextHolder.UserInfo userInfo = new TransRequestContextHolder.UserInfo();
                userInfo.setUserId(userToken.getUserId());
                TransRequestContextHolder.setUserInfo(userInfo);
            } else{
                log.warn("未获取token");
            }
        }catch (JWTVerificationException exception){
            log.error("获取token失败", exception);
            throw new RuntimeException(exception);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex){
        TransRequestContextHolder.clear();
    }
}
