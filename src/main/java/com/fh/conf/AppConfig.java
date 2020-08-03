package com.fh.conf;

import com.fh.inteceptor.LoginInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class AppConfig extends WebMvcConfigurationSupport {
    @Bean
    public LoginInteceptor loginInteceptor(){
        return  new LoginInteceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(loginInteceptor()).addPathPatterns("/**");
    }
}
