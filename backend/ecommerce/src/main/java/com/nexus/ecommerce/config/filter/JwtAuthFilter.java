package com.nexus.ecommerce.config.filter;

import com.nexus.ecommerce.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
        throws ServletException, IOException{
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        // 1. Check if the Authorization header is present and valid
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        // 2. Extract the JWT from the header
        jwt = authHeader.substring(7);
        // 3. Extract the username from the JWT
        username = jwtService.extractUsername(jwt);
        // 4. Check if username exists and the user is not already authenticated
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // 5. If token is valid, update the SecurityContext
            if (jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Update the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Pass the request along the filter chain
        filterChain.doFilter(request, response);
    }
}
