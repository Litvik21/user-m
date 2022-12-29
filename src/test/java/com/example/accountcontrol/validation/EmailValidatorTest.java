package com.example.accountcontrol.validation;

import com.example.accountcontrol.lib.UsernameValidator;
import com.example.accountcontrol.service.UserAccountService;
import com.example.accountcontrol.service.UserAccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;

class EmailValidatorTest {
    private UsernameValidator usernameValidator;
    private ConstraintValidatorContext validatorContext;
    private UserAccountService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(UserAccountServiceImpl.class);
        validatorContext = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    void isValid_Ok() {
        Mockito.when(service.getAll()).thenReturn(Collections.emptyList());
        usernameValidator = new UsernameValidator(service);
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
