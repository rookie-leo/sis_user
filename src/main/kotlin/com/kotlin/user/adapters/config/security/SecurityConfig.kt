package com.kotlin.user.adapters.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun authorizeRequests(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests { auth ->
            auth
                .requestMatchers(HttpMethod.POST, "/api/v1/user")
                .permitAll()
                .anyRequest()
                .authenticated()
        }.csrf().disable().build()
    }
}