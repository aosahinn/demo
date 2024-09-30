package com.preschool.demo.config;

import com.preschool.demo.utils.SecurityUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

public class HttpConfigurer extends AbstractHttpConfigurer<HttpConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(SecurityUtil.getIgnoredMatchers()).permitAll()
                .anyRequest().authenticated();

    }

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http
                .addFilter(new JwtAuthenticationFilter(authenticationManager))
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    public static HttpConfigurer httpConfigurer() {
        return new HttpConfigurer();
    }
}
