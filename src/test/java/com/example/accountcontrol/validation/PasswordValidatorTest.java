package com.example.accountcontrol.validation;

import javax.validation.ConstraintValidatorContext;
import com.example.accountcontrol.lib.PasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PasswordValidatorTest {
    private PasswordValidator passwordValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    void isValid_Ok() {
        Assertions.assertTrue(passwordValidator.isValid("Litvik1234", context));
        Assertions.assertTrue(passwordValidator.isValid("Eugen0011", context));
    }

    @Test
    void isValid_NotOk() {
        Assertions.assertFalse(passwordValidator.isValid("12345678", context));
        Assertions.assertFalse(passwordValidator.isValid("12345678", context));
    }
}
