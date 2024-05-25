package org.momo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.momo.common.status.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "result"})
@Schema(description = "기본 응답")
public class BaseResponseDto<T> {
    private final boolean success;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 성공한 경우 응답 생성
    public static <T> BaseResponseDto<T> onSuccess(T data){
        return new BaseResponseDto<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), data);
    }
    public static <T> BaseResponseDto<T> of(int code, String message, T data){
        return new BaseResponseDto<>(true, code, message, data);
    }

    // 실패한 경우 응답 생성
    public static <T> BaseResponseDto<T> onFailure(int code, String message, T data) {
        return new BaseResponseDto<>(false, code, message, data);
    }
}
