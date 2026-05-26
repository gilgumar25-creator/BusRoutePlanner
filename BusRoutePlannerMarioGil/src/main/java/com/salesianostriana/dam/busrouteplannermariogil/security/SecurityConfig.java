package com.salesianostriana.dam.busrouteplannermariogil.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN", "OPERADOR")
                .build();
        
        UserDetails operador = User.builder()
                .username("operador")
                .password("{noop}operador")
                .roles("OPERADOR")
                .build();

        return new InMemoryUserDetailsManager(admin, operador);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestCache requestCache = new NullRequestCache();

        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/css/**", "/js/**", "/error").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/operador/**").hasRole("OPERADOR")
                .anyRequest().authenticated()
            )
            .requestCache(cache -> cache.requestCache(requestCache))
            .formLogin(loginz -> loginz
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
