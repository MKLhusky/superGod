package com.system.supercommon.funcbean;



import java.io.Serializable;

public class R<T> implements Serializable {

    private static final String SUCCESS_MSG = "操作成功";

    private static final String FAIL_MSG = "操作失败";

    private int code = 200;

    private String msg = SUCCESS_MSG;

    private T data;


    public R(){}

    public R(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> success(){
        return new R<T>();
    }

    public static <T> R<T> success(T data){
        return new R<T>(200, SUCCESS_MSG, data);
    }

    public static <T> R<T> success(String msg, T data){
        return new R<T>(200, msg, data);
    }

    public static <T> R<T> success(int code, String msg, T data){
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> fail(){
        return new R<T>();
    }

    public static <T> R<T> fail(int code){
        return new R<T>(code, FAIL_MSG, null);
    }

    public static <T> R<T> fail(String msg){
        return new R<T>(500, msg, null);
    }

    public static <T> R<T> fail(int code, String msg){
        return new R<T>(code, msg, null);
    }

    public static <T> R<T> fail(int code, String msg, T data){
        return new R<T>(code, msg, data);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "code: " + this.code + ",  msg: " +
                this.msg + ",  data: " + this.data;
    }
}
