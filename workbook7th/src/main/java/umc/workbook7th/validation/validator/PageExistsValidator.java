package umc.workbook7th.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.workbook7th.apiPayload.code.status.ErrorStatus;
import umc.workbook7th.validation.annotation.ExistPage;

public class PageExistsValidator implements ConstraintValidator<ExistPage, Integer> {
    @Override
    public void initialize(ExistPage constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        if(value > 0)
            return true;
        else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_VALID.toString()).addConstraintViolation();
            return false;
        }
    }
}
