package com.example.accountcontrol.security;

import com.example.accountcontrol.model.UserAccount;
import com.example.accountcontrol.service.UserAccountService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;

public class CustomAccountDetailsService implements UserDetailsService {
    private final UserAccountService accountService;

    public CustomAccountDetailsService(UserAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> account = accountService.findByUsername(username);

        UserBuilder builder;
        if (account.isPresent()) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(account.get().getPassword());
            builder.roles(account.get().getRole()
                    .stream()
                    .map(r -> r.getRoleName().name())
                    .toArray(String[]::new));
            return builder.build();
        }
        throw new UsernameNotFoundException("Account not found.");
    }
}
