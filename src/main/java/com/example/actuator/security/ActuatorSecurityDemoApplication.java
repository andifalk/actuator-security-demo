package com.example.actuator.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class ActuatorSecurityDemoApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder().encode("secret"))
                        .roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("secret"))
                        .roles("USER", "ADMIN").build(),
                User.withUsername("monitor").password(passwordEncoder().encode("secret"))
                        .roles("USER", "MONITOR_ADMIN").build()
        );
    }


    public static void main(String[] args) {
        SpringApplication.run(ActuatorSecurityDemoApplication.class, args);
    }

}

