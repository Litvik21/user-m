package com.example.accountcontrol.validation;

import com.example.accountcontrol.dto.UserAccountAddingNewDto;
import com.example.accountcontrol.lib.PasswordValidator;
import com.example.accountcontrol.lib.ValidPassword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

class PasswordValidatorTest {
    private PasswordValidator passwordValidator;
    private UserAccountAddingNewDto accountAdding;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
        accountAdding = new UserAccountAddingNewDto();
        context = Mockito.mock(ConstraintValidatorContext.class);
        ValidPassword password = Mockito.mock(ValidPassword.class);
        Mockito.when(password.field()).thenReturn("password");
        Mockito.when(password.fieldMatch()).thenReturn("repeatPassword");
        passwordValidator.initialize(password);
    }

    @Test
    void isValid_Ok() {
        accountAdding.setPassword("Litvik1234");
        accountAdding.setRepeatPassword("Litvik1234");
        Assertions.assertTrue(passwordValidator.isValid(accountAdding, context));
    }

    @Test
    void isValid_NotEqualsPasswords() {
        accountAdding.setPassword("12345678");
        accountAdding.setRepeatPassword("1234567");
        Assertions.assertFalse(passwordValidator.isValid(accountAdding, context));
    }
}
