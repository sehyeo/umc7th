package umc.workbook7th.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.workbook7th.validation.validator.PageExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistPage {
    String message() default "유효하지 않는 페이지입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}