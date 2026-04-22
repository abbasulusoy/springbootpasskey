package com.example.demo.configuration;

import com.example.demo.adapter.JpaUserCredentialRepositoryAdapter;
import com.example.demo.adapter.JpaUserEntityRepositoryAdapter;
import com.example.demo.component.JpaCreationOptionsRepository;
import com.example.demo.repository.JpaPublicKeyCredentialUserEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.webauthn.management.PublicKeyCredentialUserEntityRepository;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.security.web.webauthn.registration.PublicKeyCredentialCreationOptionsRepository;

@Configuration(proxyBeanMethods = false)
public class WebAuthnConfig {

    @Bean
    public UserCredentialRepository userCredentialRepository(
            JpaUserCredentialRepositoryAdapter adapter) {
        return adapter;
    }
    @Bean
    @Primary
    public PublicKeyCredentialCreationOptionsRepository creationOptionsRepository(
            JpaCreationOptionsRepository adapter) {
        return adapter;
    }
    @Bean
    public  PublicKeyCredentialUserEntityRepository credentialUserEntityRepository(JpaUserEntityRepositoryAdapter adapter) {
        return adapter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails alex = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(alex);
    }

}