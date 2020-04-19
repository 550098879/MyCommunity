package org.zyx.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.zyx.entity.AccessTokenDTO;
import org.zyx.entity.GithubUser;

import java.io.IOException;

/**获取第三方授权
 * Created by SunShine on 2020/4/16.
 */
@Component //只将这个类交由IOC管理
public class GithubProvider {

    /**
     * 发出post请求
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            try (Response response = client.newCall(request).execute()) {
                String str=response.body().string();//此处类似流操作,只能执行一次
                System.out.println(str);
                str=str.split("&")[0].split("=")[1];
                System.out.println(str);

                return str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String access_token){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try {
//            出现connection reset 则对网络进行重启即可
            Response response = client.newCall(request).execute();
            System.out.println(response);
            String str=response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
