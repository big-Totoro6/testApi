package com.example.testapi.config;

import com.example.testapi.common.SaveParamHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    final
    SaveParamHandlerMethodArgumentResolver saveParamHandlerMethodArgumentResolver;

    public WebConfig(SaveParamHandlerMethodArgumentResolver saveParamHandlerMethodArgumentResolver) {
        this.saveParamHandlerMethodArgumentResolver = saveParamHandlerMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(saveParamHandlerMethodArgumentResolver);
    }
}
