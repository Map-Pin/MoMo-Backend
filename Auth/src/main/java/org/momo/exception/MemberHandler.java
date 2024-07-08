package org.momo.exception;

import org.momo.common.BaseErrorCode;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
