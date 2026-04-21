package com.egnndigest.journal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

  @Bean
  SecurityFilterChain configue(HttpSecurity httpSecurity){
    httpSecurity.
                authorizeHttpRequests((req)->req.requestMatchers("/health-check/**","/user/save").permitAll()
                    .anyRequest().authenticated())
        .logout(LogoutConfigurer::permitAll)
        .csrf(csrf -> csrf.disable());

    return httpSecurity.build();
  }

  public static BCryptPasswordEncoder passwordEncoder(){
    return  new BCryptPasswordEncoder();
  }
}
