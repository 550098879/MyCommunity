package org.zyx.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**拦截器
 * Created by SunShine on 2020/4/29.
 */
@Configuration      //配置类
//@EnableWebMvc       //成为手动接管MVC,引用这个注解会导致不调用addResourceHandlers方法放行静态资源
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LocaleInterceptor());
//        registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");

    }

    //默认实现的方法(对静态资源目录的放行)
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/public", "classpath:/static/")
//                .setCachePeriod(31556926);
//    }//
}