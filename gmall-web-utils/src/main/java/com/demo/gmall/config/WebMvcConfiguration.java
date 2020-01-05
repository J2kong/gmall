package com.demo.gmall.config;

import com.demo.gmall.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/4 13:57
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
   private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/error");
        super.addInterceptors(registry);
    }
}
