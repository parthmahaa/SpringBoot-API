package com.example.demo1.Config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueNameValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNameList {
    String message() default "Names must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
