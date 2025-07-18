package com.example.TravelProject.Controller;

import com.example.TravelProject.DTO.Jwt.JwtResponse;
import com.example.TravelProject.DTO.Jwt.LoginRequest;
import com.example.TravelProject.Jwt.JWTUtil;
import com.example.TravelProject.entity.UserAccount.User;
import com.example.TravelProject.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class LoginController {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // username으로 사용자 조회
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // 유저 역할 추출 및 JWT 발급
        String role = user.getUserRole();
        String token = jwtUtil.createJwt(user.getUsername(), role, 1000 * 60 * 60L); // 1시간 유효

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
