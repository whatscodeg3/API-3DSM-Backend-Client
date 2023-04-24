//package com.br.WhatsCodeClientMicroservice.config;
//
//
//import com.br.WhatsCodeClientMicroservice.repository.UserRepository;
//import com.br.WhatsCodeClientMicroservice.service.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.io.IOException;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//@Component
//public class FilterToken extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String token;
//
//        var authorizationHeader = request.getHeader("Authorization");
//
//        if(authorizationHeader != null) {
//            token = authorizationHeader.replace("Bearer ", "");
//            var subject = this.tokenService.getSubject(token);
//
//            var user = this.userRepository.findByLogin(subject);
//
//            var authentication = new UsernamePasswordAuthenticationToken(user,
//                    null, user.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//}
