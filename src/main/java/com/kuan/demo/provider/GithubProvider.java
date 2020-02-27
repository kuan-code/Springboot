package com.kuan.demo.provider;
import com.alibaba.fastjson.JSON;
import com.kuan.demo.dto.AStoken_Dto;
import com.kuan.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLOutput;

@Component
public class GithubProvider {
    public String getAsToken(AStoken_Dto aStoken_dto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(aStoken_dto),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String access_token = str.split("&")[0].split("=")[1];
            return access_token;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public GithubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
