//package com.example.expensetrackerapi.Security;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class Example extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain) throws ServletException, IOException {
//        try {
//        if (checkJWTToken(request, response)) {
//            Claims claims = validateToken(request);
//            if (claims.get("authorities") == null) { setUpSpringAuthentication(claims); } else { SecurityContextHolder.clearContext(); }
//        } else { SecurityContextHolder.clearContext(); }
//        chain.doFilter(request, response);
//    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
//        return;
//    } }
//}