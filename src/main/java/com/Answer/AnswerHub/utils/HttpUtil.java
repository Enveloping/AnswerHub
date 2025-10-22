package com.Answer.AnswerHub.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {

    public static final String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

    public static OkHttpClient getClient() {
        return getCustomClient(60,60,60);
    }

    public static OkHttpClient getCustomClient(int connectionTimeout, int readTimeout, int writeTimeout) {
        OkHttpClient.Builder builder = new okhttp3.OkHttpClient().newBuilder();
        builder.connectTimeout(connectionTimeout, TimeUnit.SECONDS);
        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        return builder.build();
    }

    public static String doGet(OkHttpClient client , String url , Map<String,Object> map , Headers headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().get();
        StringBuilder urlBuilder = new StringBuilder(url);
        if(Objects.nonNull(map)){
            urlBuilder.append("?");
            map.forEach((k,v)->{
                urlBuilder.append(URLEncoder.encode(k , StandardCharsets.UTF_8))
                          .append("=")
                          .append(URLEncoder.encode((String) v , StandardCharsets.UTF_8))
                          .append("&");
            });
            urlBuilder.deleteCharAt(urlBuilder.length()-1);//删除后面多余的&
        }
        log.info("get请求的url : {}", urlBuilder);
        Request request = requestBuilder.url(urlBuilder.toString()).headers(headers).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()&&response.body()!=null) {
                return response.body().string();
            }else  {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public static String doGet(String url,Map<String,Object> map) throws IOException {
        Headers headers = new Headers.Builder().build();
        return doGet(getClient(),url,map,headers);
    }

    public static String doGet(String url) throws IOException {
        Headers headers = new Headers.Builder().build();
        return doGet(getClient(),url,null,headers);
    }

    public static String doPost(OkHttpClient client, String url , Map<String,Object> map, Headers headers) throws IOException {
        MediaType mediaType = MediaType.parse(MEDIA_TYPE_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsString(map),mediaType);
        Request request = new Request.Builder().url(url).headers(headers).post(requestBody).build();
        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful() && response.body()!=null){
                return response.body().toString();
            }else  {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public static String doPost(String url, Map<String,Object> map) throws IOException {
        Headers header = new Headers.Builder().build();
        return doPost(getClient(),url,map,header);
    }
}
