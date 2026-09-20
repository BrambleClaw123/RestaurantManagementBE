package com.example.qlnh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Tắt bảo vệ CSRF (nếu không tắt, các request POST/PUT thêm dữ liệu sẽ bị chặn đứng)
                .csrf(csrf -> csrf.disable())
                // Cấu hình phân quyền
                .authorizeHttpRequests(auth -> auth
                        // Cho phép tất cả các đường dẫn API đi qua mà không cần xác thực
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}