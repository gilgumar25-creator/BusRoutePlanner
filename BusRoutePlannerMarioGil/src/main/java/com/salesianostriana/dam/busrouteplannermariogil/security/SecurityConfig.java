package com.salesianostriana.dam.busrouteplannermariogil.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public boolean isAdmin() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


		return isAuthenticated()

				&& authentication.getAuthorities().stream()

						.map(GrantedAuthority::getAuthority)

						.anyMatch("ROLE_ADMIN"::equals);

	}


	public boolean isAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


		return authentication != null

				&& authentication.isAuthenticated()

				&& !(authentication instanceof AnonymousAuthenticationToken);

	}


	public String getUsername() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


		return isAuthenticated() ? authentication.getName() : "";

	}
}
