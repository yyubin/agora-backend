package org.ratio.agora.member.service;

import org.ratio.agora.member.domain.Member;
import org.ratio.agora.member.domain.OAuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2MemberService {
    Member process(OAuth2User oAuth2User, OAuthProvider provider);


}
