package com.springinaction.tacocloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 모델 데이터가 없거나 처리할 필요가 없는 HTTP GET 요청을 처리할 때는 뷰 컨트롤러를 사용할 수 있다
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // HomeController 삭제해도 default로 지정
        registry.addViewController("/").setViewName("home");
    }
}
