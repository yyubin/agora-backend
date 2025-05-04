package org.ratio.agora.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ratio.agora.base.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    EMAIL_ALREADY_EXISTS("MEMBER_001", "이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT),
    MEMBER_NOT_FOUND("MEMBER_002", "해당 회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    UNAUTHORIZED_ACCESS("MEMBER_003", "접근 권한이 없습니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
