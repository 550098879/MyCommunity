package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zyx.entity.AccessTokenDTO;
import org.zyx.entity.GithubUser;
import org.zyx.entity.User;
import org.zyx.provider.GithubProvider;
import org.zyx.repository.UserRepository;
import org.zyx.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**GitHub认证
 * Created by SunShine on 2020/4/16.
 */
@Controller
public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;



    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser Guser = githubProvider.getUser(accessToken);
        System.out.println(Guser);

        if(Guser!=null){
            //登陆成功,设置session和cookie
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);//随机唯一id
            user.setName(Guser.getName());
            user.setAccount_id(String.valueOf(Guser.getId()));//强制转换
            user.setBio(Guser.getBio());
            user.setAvatar_url(Guser.getAvatar_url());

            userService.createOrUpdate(user);//创建或更新


//            userRepository.insert(user);//存储进数据库中
            //将token设置到cookie中,而不是直接设置到session中
            response.addCookie(new Cookie("token",token));

//            HttpSession session=request.getSession();
//            session.setAttribute("Guser",Guser);
            return "redirect:/";//重定向
        }else{
            //登陆失败,重新登陆
        }


        return "redirect:/";//请求转发
    }


    @GetMapping("/logout")
    public String logout(HttpSession session,HttpServletResponse response){

        session.removeAttribute("user");
//        重置token的cookie
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);

        return "redirect:/";
    }


}
