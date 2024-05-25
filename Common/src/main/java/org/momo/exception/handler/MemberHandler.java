package org.momo.exception.handler;


import org.momo.common.BaseErrorCode;
import org.momo.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
