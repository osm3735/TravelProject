package com.example.TravelProject.Jwt;

import com.example.TravelProject.DTO.OAuth.CustomOAuth2User;
import com.example.TravelProject.DTO.OAuth.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        // 로그인 및 OAuth2 관련 요청은 JWT 검사 건너뜀
        if (requestUri.matches("^/app/login(?:/.*)?$") || requestUri.matches("^/app/oauth2(?:/.*)?$")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("requestUri: "+requestUri);
        String token = null;

        // 1. 먼저 Authorization 헤더에서 토큰 확인
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && !headerAuth.isEmpty()) {
            token = headerAuth;
            System.out.println("JWT from Header: " + token);
        }

        // 2. 헤더에 없으면 쿠키에서 찾기
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                	System.out.println(cookie);
                    if ("Authorization".equals(cookie.getName()) || "access_token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        System.out.println("JWT from Cookie: " + token);
                        break;
                    }
                }
            }
        }

        // 3. 여전히 없으면 필터 통과
        if (token == null) {
            System.out.println("JWT not found");
            filterChain.doFilter(request, response);
            return;
        }

        // 4. 만료된 토큰은 거부
        if (jwtUtil.isExpired(token)) {
            System.out.println("JWT expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 5. 토큰에서 사용자 정보 추출
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);
        
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO, null);

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                customOAuth2User, null, customOAuth2User.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
