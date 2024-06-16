package org.momo.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.momo.common.BaseErrorCode;
import org.momo.common.ErrorReasonDto;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode baseErrorCode;

    public ErrorReasonDto getReason(){
        return this.baseErrorCode.getReason();
    }

    public ErrorReasonDto getReasonHttpStatus(){
        return this.baseErrorCode.getReasonHttpStatus();
    }

}
