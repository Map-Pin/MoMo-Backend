package org.momo.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDto {
    private final HttpStatus httpStatus;
    private final boolean success;
    private final int code;
    private final String message;
}
