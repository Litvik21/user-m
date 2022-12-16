package com.example.accountcontrol.service;

import com.example.accountcontrol.model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    UserAccount add(UserAccount account);

    UserAccount update(UserAccount account);

    List<UserAccount> getAll();

    UserAccount get(Long id);

    Optional<UserAccount> findByUsername(String username);
}
