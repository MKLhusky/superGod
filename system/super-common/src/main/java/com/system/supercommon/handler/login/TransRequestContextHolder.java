package com.system.supercommon.handler.login;

public class TransRequestContextHolder {

    private static ThreadLocal<UserInfo> THREAD_USER_INFO = new ThreadLocal<UserInfo>();


    public static void setUserInfo(UserInfo userInfo){
        THREAD_USER_INFO.set(userInfo);
    }

    public static ThreadLocal<UserInfo> getUserInfo(){
        return THREAD_USER_INFO;
    }

    public static Long getUserId(){
        if (THREAD_USER_INFO.get() != null){
            return THREAD_USER_INFO.get().getUserId();
        }
        return null;
    }

    public static void clear(){
        THREAD_USER_INFO.remove();
    }

    public static class UserInfo{
        private Long userId;

        public void setUserId(Long userId){
            this.userId = userId;
        }

        public Long getUserId(){
            return this.userId;
        }


    }


}
