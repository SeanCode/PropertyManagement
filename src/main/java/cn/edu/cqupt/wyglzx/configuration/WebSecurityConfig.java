package cn.edu.cqupt.wyglzx.configuration;

import cn.edu.cqupt.wyglzx.common.PasswordHashEncoder;
import cn.edu.cqupt.wyglzx.service.AdminDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by cc on 16/6/10.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        AdminDetailService adminDetailService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/api/private/v1/admin/**")
                    .authorizeRequests()
                    .antMatchers("/api/private/v1/admin/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable()
                    .httpBasic();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminDetailService)
                    .passwordEncoder(new PasswordHashEncoder());
        }
    }

}
