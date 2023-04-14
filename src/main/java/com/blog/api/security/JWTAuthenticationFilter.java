package com.blog.api.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDetailsService userDetailsService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get jwt token
        System.out.println(request);
        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);
        String username = null;
        String token = null;
        if(requestToken != null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
                //get username
                username = this.jwtTokenHelper.getUserNameFromToken(token);
            } catch (IllegalArgumentException e){
                System.out.println("Unable to get token");
            } catch (ExpiredJwtException e){
                System.out.println("Token Expired");
            } catch (MalformedJwtException e){
                System.out.println("Invalid Token");
            }
        }
        else {
            System.out.println("JWT Token does not begin with bearer");
        }
        //validate token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //load user
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken tptoken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                tptoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(tptoken);

            } else {
                System.out.println("Invalid Token");
            }
        } else {
            System.out.println("Username is null or context is not null");
        }
        //set spring security
        filterChain.doFilter(request, response);
    }
}
