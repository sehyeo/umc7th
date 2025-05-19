package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.validation.annotation.ExistPage;

public class PageExistValidator implements ConstraintValidator<ExistPage, Integer> {
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