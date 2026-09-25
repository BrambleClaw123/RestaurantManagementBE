package com.example.qlnh.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()).exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            // Chỉ cần trả đúng 1 câu JSON đơn giản
                            response.getWriter().write("{\"status\": 401,\"message\": \"Unauthorized\"}");
                        })
                )
                .authorizeHttpRequests(auth -> auth
                        // CHỈ CHO PHÉP API Đăng nhập được đi qua không cần hỏi giấy tờ
                        .requestMatchers("/api/auth/login", "/error").permitAll()

                        // TOÀN BỘ CÁC API KHÁC (Nhân viên, Tài khoản, Món ăn...) ĐỀU PHẢI XÁC THỰC!
                        .anyRequest().authenticated()
                )
                // Tắt session mặc định của Spring (vì ta đã dùng JWT là Stateless)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Chèn cái chốt JwtFilter lên đứng trước cổng bảo vệ mặc định của Spring
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}