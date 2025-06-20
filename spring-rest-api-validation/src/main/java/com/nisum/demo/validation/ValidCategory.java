package com.nisum.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {
    String message() default "Invalid category (must be Electronics, Grocery, or Clothing)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
