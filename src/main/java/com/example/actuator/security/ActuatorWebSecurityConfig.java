package com.example.actuator.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Actuator web security config.
 */
@Configuration
@Order(10)
public class ActuatorWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.to("health", "info")).authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic().and().formLogin().and().logout().permitAll();
    }
}
