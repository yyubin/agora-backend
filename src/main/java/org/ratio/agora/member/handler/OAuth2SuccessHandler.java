package org.ratio.agora.member.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ratio.agora.member.domain.Member;
import org.ratio.agora.member.domain.OAuthProvider;
import org.ratio.agora.member.jwt.JwtProvider;
import org.ratio.agora.member.repository.MemberRepository;
import org.ratio.agora.member.service.OAuth2MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2MemberService oAuth2MemberService;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        OAuthProvider provider = OAuthProvider.GOOGLE;

        Member member = oAuth2MemberService.process(oAuth2User, provider);
        String token = jwtProvider.createToken(member.getId());

        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }
}
