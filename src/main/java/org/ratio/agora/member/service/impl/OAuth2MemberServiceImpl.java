package org.ratio.agora.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.ratio.agora.member.domain.GoogleOAuth2UserInfo;
import org.ratio.agora.member.domain.Member;
import org.ratio.agora.member.domain.OAuth2UserInfo;
import org.ratio.agora.member.domain.OAuthProvider;
import org.ratio.agora.member.repository.MemberRepository;
import org.ratio.agora.member.service.OAuth2MemberService;
import org.ratio.agora.profile.domain.Profile;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.ratio.agora.member.domain.OAuthProvider.GOOGLE;

@Service
@RequiredArgsConstructor
public class OAuth2MemberServiceImpl implements OAuth2MemberService {

    private final MemberRepository memberRepository;


    public Member process(OAuth2User oAuth2User, OAuthProvider provider) {
        OAuth2UserInfo userInfo = extractUserInfo(provider, oAuth2User.getAttributes());

        return memberRepository.findByOauthIdAndProvider(userInfo.getOauthId(), provider)
                .orElseGet(() -> registerNewMember(userInfo, provider));
    }

    private OAuth2UserInfo extractUserInfo(OAuthProvider provider, Map<String, Object> attributes) {
        return switch (provider) {
            case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    private Member registerNewMember(OAuth2UserInfo info, OAuthProvider provider) {
        Member newMember = Member.builder()
                .oauthId(info.getOauthId())
                .email(info.getEmail())
                .provider(provider)
                .isPhoneVerified(false)
                .build();

        Profile profile = Profile.builder()
                .nickname(generateRandomNickname())
                .member(newMember)
                .build();

        newMember.setProfile(profile);
        return memberRepository.save(newMember);
    }

    private String generateRandomNickname() {
        return UUID.randomUUID().toString();
    }
}
