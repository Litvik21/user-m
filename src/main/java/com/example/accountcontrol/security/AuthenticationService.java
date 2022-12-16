package com.example.accountcontrol.security;

import com.example.accountcontrol.model.UserAccount;

public interface AuthenticationService {
    UserAccount addNewAccount(String username, String password, String firstName, String lastName);

    UserAccount login(String username, String password);
}
