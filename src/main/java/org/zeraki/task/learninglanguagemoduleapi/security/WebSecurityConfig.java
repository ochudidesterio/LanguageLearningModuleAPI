package org.zeraki.task.learninglanguagemoduleapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF
                .csrf(csrf -> csrf.disable())
                // Configure URL-based authorization
                .authorizeHttpRequests(auth -> auth
                        // Allowed requests without authentication
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/lesson/getAll").permitAll()
                        .requestMatchers(HttpMethod.GET,"/exercise/getAll").permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )
                // Enable HTTP Basic authentication
                .headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .httpBasic(withDefaults());

        return http.build();

    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}

