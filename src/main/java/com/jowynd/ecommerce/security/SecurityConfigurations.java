package com.jowynd.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity
                    .cors()
                    .and()
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                            .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/product/{id}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/product/{id}/activate").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/product/{id}/inactivate").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/product/{id}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/user/{id}/activate").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/user/{id}/inactivate").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/orders/{id}").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated())
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//Disable security
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        try {
            return authenticationConfiguration.getAuthenticationManager();

        } catch (Exception e) {
            throw new RuntimeException("Authentication fail");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5500", "http://127.0.0.1:5500"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
