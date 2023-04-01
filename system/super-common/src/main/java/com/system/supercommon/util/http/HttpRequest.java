package com.system.supercommon.util.http;

import com.alibaba.fastjson2.JSON;
import com.system.supercommon.funcbean.R;
import org.springframework.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpRequest {

    protected static HttpURLConnection urlConnection;

    protected OutputStream paramsOutputStream;

    private static HttpPostRequest httpPostRequest;

    public static HttpPostRequest post(String path) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestProperty("accept" , "*/*");
            connection.setRequestProperty("connection" , "Keep-Alive");
            connection.setRequestProperty("user-agent" , "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setRequestProperty("Content-Type" , "application/json;charset=utf-8");
            urlConnection = connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpPostRequest = new HttpPostRequest();
        return httpPostRequest;
    }

    public R connection() {
        InputStream responseInput = null;
        BufferedReader bufferedReader = null;
        try {
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (HttpStatus.OK.value() == responseCode) {
                responseInput = urlConnection.getInputStream();

                if (responseInput != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(responseInput, StandardCharsets.UTF_8));
                    String content = null;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((content = bufferedReader.readLine()) != null) {
                        stringBuilder.append(content);
                        stringBuilder.append("\r\n");
                    }
                    return JSON.parseObject(stringBuilder.toString(), R.class);
                }
            } else {
                return R.fail(responseCode);
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (paramsOutputStream != null) {
                    paramsOutputStream.close();
                }
                if (responseInput != null){
                    responseInput.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            urlConnection.disconnect();
        }

        return R.fail();
    }

}
