package org.momo.common.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.momo.common.BaseErrorCode;
import org.momo.common.ErrorReasonDto;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    //일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,400,"잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,401,"인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, 403, "금지된 요청입니다."),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 401, "사용자가 없습니다."),
    USER_NOT_EXISTS(HttpStatus.NOT_FOUND, 404, "존재하지 않는 사용자입니다."),
    NICKNAME_EXISTS(HttpStatus.CONFLICT, 409, "이미 존재하는 닉네임입니다."),

    //JWT 토큰 관련 에러
    JWT_BAD_REQUEST(HttpStatus.BAD_REQUEST,400,"잘못된 JWT 서명입니다."),
    JWT_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, 401,"유효한 JWT 토큰이 없습니다"),
    JWT_ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,402,"액세스 토큰이 만료되었습니다"),
    JWT_TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED,403,"지원하지 않는 JWT 토큰입니다"),

    JWT_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, 401, "올바르지 않은 JWT 토큰입니다."),
    JWT_PAYLOAD_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "Payload 정보가 일치하지 않습니다."),
    JWT_EXPIRED_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "JWT 토큰이 만료되었습니다."),

    NO_PUBLIC_KEY_EXCEPTION(HttpStatus.NOT_FOUND, 401, "일치하는 공개 키가 없습니다."),
    TOKEN_MISMATCH_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "사용자가 일치하지 않습니다"),
    INVALID_SIGNATURE_EXCEPTION(HttpStatus.BAD_REQUEST,400,"잘못된 JWT 서명입니다."),

    // 에러 모음
    UNSUPPORTED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "지원하지 않은 JWT 입니다."),
    MALFORMED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "올바르지 않은 JWT 입니다."),
    SIGNATURE_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "JWT 토큰 서명 오류입니다."),
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "만료된 JWT 토큰 입니다."),
    ILLEGAL_ARGUMENT_EXCEPTION(HttpStatus.UNAUTHORIZED, 401, "올바르지 않은 JWT 입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "요청한 주소로 응답을 받을 수 없습니다."),

    ANOTHER_USER(HttpStatus.UNAUTHORIZED, 401, "토큰 탈취 위협이 있습니다."),
    TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED, 401, "올바른 형식의 토큰이 아닙니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @Override
    public ErrorReasonDto getReason(){
        return ErrorReasonDto.builder()
                .message(message)
                .code(code)
                .success(false)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus(){
        return ErrorReasonDto.builder()
                .httpStatus(httpStatus)
                .message(message)
                .code(code)
                .success(false)
                .build();
    }
}
