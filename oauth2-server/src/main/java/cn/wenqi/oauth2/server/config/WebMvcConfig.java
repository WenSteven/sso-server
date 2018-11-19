package cn.wenqi.oauth2.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wenqi
 * @since v1.1
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("oauth/login");
        registry.addViewController("/oauth/confirm_access").setViewName("oauth/authorize");
        registry.addViewController("/error").setViewName("error");
    }
}
