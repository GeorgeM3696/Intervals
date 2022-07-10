package com.example.intervalsjava.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value( "${hardcodedBearToken}" )
    private String hardcodedBearToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header.isEmpty() || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        final var token = header.split(" ")[1].trim();
        if (!token.equals(hardcodedBearToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        var  authReq = new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(authReq);
        filterChain.doFilter(request, response);
    }
}
