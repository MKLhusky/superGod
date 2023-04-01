package com.system.supercommon.util.http;

import com.alibaba.fastjson2.JSON;
import com.system.supercommon.util.spring.JsonSerializableUtil;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

public class HttpPostRequest extends HttpRequest{

    public HttpPostRequest setParams(Object params) {

        if (params == null) {
            throw new RuntimeException("参数不可为空"); //后续封装为统一异常
        }

        try {
            paramsOutputStream = urlConnection.getOutputStream();

            paramsOutputStream.write(JsonSerializableUtil.objToByteArray(params));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return this;
    }

}
