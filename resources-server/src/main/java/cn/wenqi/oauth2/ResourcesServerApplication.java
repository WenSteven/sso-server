package cn.wenqi.oauth2;

import cn.wenqi.oauth2.exception.AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.security.Principal;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableResourceServer
@RestController
public class ResourcesServerApplication extends ResourceServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(ResourcesServerApplication.class,args);
    }


    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(defaultTokenServices).resourceId("my/res").stateless(false);
        resources.accessDeniedHandler(accessDeniedHandler);
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }



    @GetMapping("/userInfo")
    public Principal principal(Principal principal){
        return principal;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/goods/**").permitAll();
    }
}
