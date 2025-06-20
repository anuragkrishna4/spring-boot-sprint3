package com.nisum.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

    private final List<String> allowed = Arrays.asList("Electronics", "Grocery", "Clothing");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && allowed.contains(value);
    }
}
