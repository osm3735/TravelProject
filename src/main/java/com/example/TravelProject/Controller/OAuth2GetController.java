package com.example.TravelProject.Controller;

import com.example.TravelProject.Jwt.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class OAuth2GetController {

    private final JwtProvider jwtProvider;

    @GetMapping("/app/login/oauth2/code/google")
    public ResponseEntity<?> redirectToFrontend(@AuthenticationPrincipal OAuth2User oauthUser,
                                   HttpServletResponse response
                                   ) throws IOException {
        // 방법 1: null 체크 후 안전하게 처리
        if (oauthUser == null) {
            // 디버깅을 위한 로그
            System.err.println("OAuth2User가 null입니다 - 인증이 실패했을 가능성이 있습니다");
            
            // 로그인 페이지로 리다이렉트하거나 에러 응답 반환
            return ResponseEntity.status(HttpStatus.OK)
                .body("인증에 실패했습니다. 다시 로그인해 주세요.");
        }
    	
        
        try {
            // 이제 안전하게 OAuth2User 속성에 접근
            String email = oauthUser.getAttribute("email");
            String name = oauthUser.getAttribute("name");
            
            System.out.println("구글 컨트롤러 요청 성공 : ");
            System.out.println("oauth email : " + email);
            System.out.println("oauth name : " + name);
            
            // 기존 로직을 여기에 작성
            // ... 사용자 데이터 처리 및 프론트엔드로 리다이렉트
            
            String jwtAccessToken = jwtProvider.createAccessToken(email);
            String refreshToken = jwtProvider.createRefreshToken(email);

            String redirectUrl = "http://localhost:3000/oauth?access_token=" + jwtAccessToken +
                    "&refresh_token=" + refreshToken +
                    "&email=" + email +
                    "&name=" + name;

            response.sendRedirect(redirectUrl);
            
            
            return ResponseEntity.ok().body("성공");
            
        } catch (Exception e) {
            System.err.println("OAuth2 사용자 처리 중 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("인증 처리 중 오류가 발생했습니다");
        }

    	

    }    
}
