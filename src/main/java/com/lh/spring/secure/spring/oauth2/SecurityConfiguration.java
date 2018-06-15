package com.lh.spring.secure.spring.oauth2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${secure.username.lh}")
    private String lh_username;
    @Value("${secure.password.lh}")
    private String lh_password;

    @Value("${secure.username.baidu}")
    private String baidu_username;
    @Value("${secure.password.baidu}")
    private String baidu_password;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(lh_username).password(lh_password).roles("lh").build());
        manager.createUser(User.withUsername(baidu_username).password(baidu_password).roles("baidu").build());
        return manager;
    }

    /**
     * 设置不对oauth的请求进行拦截
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/secure/oauth/test/*").permitAll();
    }
}