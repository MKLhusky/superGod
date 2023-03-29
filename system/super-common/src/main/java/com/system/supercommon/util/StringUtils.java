package com.system.supercommon.util;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 字符串工具类
 *
 * @author Mr. Dai
 * @date 2023/3/28 16:58
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final Integer RANDOM_START=33;
    private static final Integer RANDOM_END=126;
    private static final SecureRandom SECURERANDOM;

    private static Pattern SNAKE_PATTERN;
    private static Pattern HUMP_PATTERN;


    static {
        HUMP_PATTERN = Pattern.compile("[A-Z](?=[a-z])");
        SNAKE_PATTERN = Pattern.compile("_[a-z]");
        SECURERANDOM=new SecureRandom();
    }


    /**
     * @Description:   首字母大写
     * @param s
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 16:59
     */
    public static String toFirstUpperCase(String s){
        char[] chars = s.toCharArray();
        if('a'<=chars[0] && chars[0]<='z'){
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }

    /**
     * @Description:   首字母小写
     * @param s
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 17:04
     */
    public static String toFirstLowerCase(String s){
        char[] chars = s.toCharArray();
        if('A'<=chars[0] && chars[0] <= 'Z'){
            chars[0] +=32;
        }
        return String.valueOf(chars);
    }

    /**
     * @Description:  去除指定后缀
     * @param s
     * @param suffix
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 17:07
     */
    public static String cutSuffix(String s, String suffix){
        if(null!=suffix&&suffix.length()<s.length()){
            s=s.substring(0,s.length()-suffix.length());
        }
        return s;
    }

    /**
     * @Description:  驼峰转蛇形
     * @param s 待转的字符串
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 15:56
     */
    //转蛇形
    public static String toSnake(String s){
        s=toFirstLowerCase(s);
        StringBuffer buffer=new StringBuffer();
        Matcher matcher = HUMP_PATTERN.matcher(s);
        while (matcher.find()){
            matcher.appendReplacement(buffer,"_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * @Description:   String  蛇形转驼峰
     * @param s 待转的字符串
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/28 15:56
     */
    public static String toHump(String s){
        StringBuffer buffer=new StringBuffer();
        Matcher matcher = SNAKE_PATTERN.matcher(s);
        while (matcher.find()){
            matcher.appendReplacement(buffer,matcher.group(0).toUpperCase().replace("_",""));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }


    /**
     * @Description:  根据指定长度获取随机字符
     *  采用ascii 码33-126的基本字符 抛弃无法识别的扩展字符
     * @param size
     * @return java.lang.String
     * @author Mr. Dai
     * @date 2023/3/29 10:55
     */
    public static String random(int size){
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<size;i++){
            buffer.append((char)(SECURERANDOM.nextInt(RANDOM_END-RANDOM_START)+RANDOM_START));
        }
        return buffer.toString();
    }

}
