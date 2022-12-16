package com.example.accountcontrol.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import com.example.accountcontrol.model.UserAccount;
import com.example.accountcontrol.service.UserAccountService;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    private static final String LOGIN_VALIDATION_REGEX = "^[A-Za-z]$";
    private final UserAccountService accountService;

    public UsernameValidator(UserAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        boolean checker = true;
        List<UserAccount> accounts = accountService.getAll();
        for (UserAccount account : accounts) {
            if (username.equals(account.getUsername())) {
                checker = false;
                break;
            }
        }
        return username != null && username.matches(LOGIN_VALIDATION_REGEX) && checker;
    }
}
