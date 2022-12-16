package com.example.accountcontrol.lib;

import com.example.accountcontrol.dto.UserAccountAddingNewDto;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, UserAccountAddingNewDto> {
    private String field;
    private String fieldMatch;
    private static final String PASSWORD_VALIDATION_REGEX = "^\\w(\\d)+(A-Za-z)+$";

    public void initialize(ValidPassword constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(UserAccountAddingNewDto requestDto, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(requestDto)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(requestDto)
                .getPropertyValue(fieldMatch);
        return fieldValue != null && fieldValue.equals(fieldMatchValue)
                && fieldValue.toString().matches(PASSWORD_VALIDATION_REGEX);
    }
}
