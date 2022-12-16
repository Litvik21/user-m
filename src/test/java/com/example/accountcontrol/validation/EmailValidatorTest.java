package com.example.accountcontrol.validation;

import com.example.accountcontrol.lib.UsernameValidator;
import com.example.accountcontrol.service.UserAccountService;
import com.example.accountcontrol.service.UserAccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

class EmailValidatorTest {
    private UsernameValidator usernameValidator;
    private ConstraintValidatorContext validatorContext;

    @BeforeEach
    void setUp() {
        UserAccountService service = Mockito.mock(UserAccountServiceImpl.class);
        usernameValidator = new UsernameValidator(service);
        validatorContext = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    void isValid_Ok() {
        Assertions.assertTrue(usernameValidator.isValid("Litvik", validatorContext));
        Assertions.assertTrue(usernameValidator.isValid("Username123", validatorContext));
    }

    @Test
    void isValid_NotOk() {
        Assertions.assertFalse(usernameValidator.isValid("1234442", validatorContext));
        Assertions.assertFalse(usernameValidator.isValid("litvik", validatorContext));
        Assertions.assertFalse(usernameValidator.isValid("111gggr", validatorContext));
    }
}
