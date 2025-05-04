package org.ratio.agora.member.repository;

import org.ratio.agora.member.domain.Member;
import org.ratio.agora.member.domain.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauthIdAndProvider(String oauthId, OAuthProvider provider);
}
