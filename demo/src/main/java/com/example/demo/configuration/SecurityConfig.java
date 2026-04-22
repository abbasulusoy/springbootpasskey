package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.webauthn.registration.PublicKeyCredentialCreationOptionsRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    PublicKeyCredentialCreationOptionsRepository optionsRepo) throws Exception {

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/webauthn/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                //.formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .formLogin(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth
                        //
                        .requestMatchers("/webauthn/**").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        // allow login page
                        .requestMatchers("/webauthn/authenticate/**", "/login/webauthn").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/webauthn.html").permitAll()
                        .requestMatchers("/.well-known/**").permitAll()
                        .anyRequest().authenticated()
                )

                .webAuthn(web -> web
                        .rpName("ZAFE")
                        .rpId("localhost")
                        .allowedOrigins("http://localhost:8181")

                        .creationOptionsRepository(optionsRepo)
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ⚠️ WICHTIG: dein Frontend URL
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        config.setAllowedHeaders(List.of("*"));

        // 🔥 GANZ WICHTIG für WebAuthn + Session
        config.setAllowCredentials(true);

        // Optional
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}