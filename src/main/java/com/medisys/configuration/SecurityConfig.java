package com.medisys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.medisys.security.AuthenticationTokenProcessingFilter;
import com.medisys.security.UnauthorizedEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationTokenProcessingFilter authenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*/auth/login/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .addFilterBefore(authenticationTokenFilter, BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .csrf().disable();

    }
}
