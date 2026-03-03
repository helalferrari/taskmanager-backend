package com.helalferrari.taskmanager.config;

import com.helalferrari.taskmanager.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        try {
            userEmail = jwtService.extractEmail(jwt);
            System.out.println("Filtro JWT - Extraindo e-mail do token: " + userEmail);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Como não temos um UserDetailsService completo ainda, criamos um UserDetails básico.
                UserDetails userDetails = new User(userEmail, "", Collections.emptyList());

                if (jwtService.isTokenValid(jwt, userEmail)) {
                    System.out.println("Filtro JWT - Token validado com sucesso para: " + userEmail);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Filtro JWT - Autenticação configurada no contexto.");
                } else {
                    System.out.println("Filtro JWT - Token inválido ou expirado.");
                }
            }
        } catch (Exception e) {
            System.err.println("Filtro JWT - Erro ao processar o token: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
