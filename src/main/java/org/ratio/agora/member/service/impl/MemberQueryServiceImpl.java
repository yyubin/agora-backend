package org.ratio.agora.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.ratio.agora.member.domain.Member;
import org.ratio.agora.member.repository.MemberRepository;
import org.ratio.agora.member.service.MemberQueryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    public Member getById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
