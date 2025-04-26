package com.example.demo1.Config;

import com.example.demo1.repo.ProductRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UniqueNameValidator implements ConstraintValidator<UniqueNameList, String> {

    @Autowired
    private ProductRepo productRepo;

    private static final Set<String> existingNames = new HashSet<>();


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true; // Allow null or blank values if needed
        return !productRepo.existsByName(value);
    }
}