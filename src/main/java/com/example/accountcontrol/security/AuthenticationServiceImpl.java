package com.example.accountcontrol.security;

import java.util.Optional;
import java.util.Set;
import com.example.accountcontrol.model.Role;
import com.example.accountcontrol.model.UserAccount;
import com.example.accountcontrol.service.RoleService;
import com.example.accountcontrol.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountService accountService;

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public AuthenticationServiceImpl(UserAccountService accountService,
                                     PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserAccount addNewAccount(String username, String password,
                                     String firstName, String lastName) {
        UserAccount account = new UserAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole(Set.of(roleService.getRoleByRoleName(Role.RoleName.USER)));
        account.setStatus(UserAccount.Status.ACTIVE);
        return accountService.add(account);
    }

    @Override
    public UserAccount login(String username, String password) {
        Optional<UserAccount> account = accountService.findByUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        if (account.isEmpty() || !account.get().getPassword().equals(encodedPassword)) {
            throw new RuntimeException("Incorrect username or password!");
        }
        return account.get();
    }
}
