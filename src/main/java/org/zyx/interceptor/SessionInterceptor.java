package org.zyx.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zyx.model.User;
import org.zyx.model.UserExample;
import org.zyx.repository.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by SunShine on 2020/4/29.
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        处理自动登陆功能
        Cookie cookies[]=request.getCookies();
        if(cookies==null){
            System.out.println("cookie为空");
        }else{
            for(Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    String token=cookie.getValue();

                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(token);

                    List<User> users = userMapper.selectByExample(example);
                    if(users.size() != 0){
                        request.getSession().setAttribute("user",users.get(0));
                    }
                    break;

                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
