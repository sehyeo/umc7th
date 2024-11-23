package umc.workbook7th.apiPayload.exception.handler;

import umc.workbook7th.apiPayload.code.BaseErrorCode;
import umc.workbook7th.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}