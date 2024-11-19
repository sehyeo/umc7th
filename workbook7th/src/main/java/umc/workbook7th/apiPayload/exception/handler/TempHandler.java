package umc.workbook7th.apiPayload.exception.handler;

import umc.workbook7th.apiPayload.code.BaseErrorCode;
import umc.workbook7th.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}