package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyx.entity.AccessTokenDTO;
import org.zyx.entity.GithubUser;
import org.zyx.provider.GithubProvider;

/**GitHub认证
 * Created by SunShine on 2020/4/16.
 */
@Controller
public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("c675236053a463ef56cb");
        accessTokenDTO.setClient_secret("6c5afe4f1c999380c4e9c9793948f3635f47263e");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:7777/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);

        return "index";
    }



}
