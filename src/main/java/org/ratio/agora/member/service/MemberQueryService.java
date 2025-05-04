package org.ratio.agora.member.service;

import org.ratio.agora.member.domain.Member;

public interface MemberQueryService {
    Member getById(Long id);
}
