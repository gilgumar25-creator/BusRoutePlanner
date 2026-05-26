package com.salesianostriana.dam.busrouteplannermariogil.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

@Component
@Log
public class RoleBasedSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final String ROLE_ADMIN_URL = "/admin/index";
    private final String ROLE_OPERADOR_URL = "/operador/index";
    private final String ROLE_DEFAULT_URL = "/login?error=Error en el rol asignado";

    private static final Map<String, Integer> role_weight = Map.of(
        "ROLE_ADMIN", 10,
        "ROLE_OPERADOR", 5
    );

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
            throws IOException, ServletException {
        
        log.info("Autenticación exitosa: " + authentication.toString());

        String role = getMaxRole(authentication.getAuthorities());
        log.info("Rol con mayor privilegio: " + role);

        String redirectUrl = determineTargetUrl(role);
        log.info("Redirigiendo a: " + redirectUrl);
        
        if (response.isCommitted()) {
            log.info("No se puede redirigir, la respuesta ya ha sido procesada.");
            return;
        }
        
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    private String getMaxRole(Collection<? extends GrantedAuthority> collection) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>(collection);
        
        if (authoritiesList.isEmpty()) {
            return "ROLE_DEFAULT";
        }
        
        return authoritiesList.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> a.startsWith("ROLE_"))
                .sorted((role1, role2) -> role_weight.getOrDefault(role2, Integer.MIN_VALUE) - role_weight.getOrDefault(role1, Integer.MIN_VALUE))
                .findFirst()
                .orElse("ROLE_DEFAULT");
    }

    private String determineTargetUrl(String role) {
        return switch(role) {
            case "ROLE_ADMIN" -> ROLE_ADMIN_URL;
            case "ROLE_OPERADOR" -> ROLE_OPERADOR_URL;
            default -> ROLE_DEFAULT_URL;
        };
    }
}