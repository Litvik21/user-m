package com.example.accountcontrol.controller;

import java.util.Map;
import java.util.stream.Collectors;
import com.example.accountcontrol.dto.UserAccountAddingNewDto;
import com.example.accountcontrol.dto.UserAccountLoginDto;
import com.example.accountcontrol.dto.UserAccountResponseDto;
import com.example.accountcontrol.mapper.RoleMapper;
import com.example.accountcontrol.model.UserAccount;
import com.example.accountcontrol.security.AuthenticationService;
import com.example.accountcontrol.security.JwtTokenProvider;
import com.example.accountcontrol.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider provider;
    private final RoleMapper roleMapper;
    private final UserAccountService accountService;


    public AuthenticationController(AuthenticationService authenticationService,
                                    JwtTokenProvider provider, RoleMapper roleMapper, UserAccountService accountService) {
        this.authenticationService = authenticationService;
        this.provider = provider;
        this.roleMapper = roleMapper;
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public UserAccountResponseDto register(@RequestBody @Valid UserAccountAddingNewDto requestDto) {
        UserAccount account = authenticationService.addNewAccount(requestDto.getUsername(),
                requestDto.getPassword(), requestDto.getFirstName(),requestDto.getLastName());
        String token = provider.createToken(account.getUsername(), account.getRole().stream()
                .map(r -> r.getRoleName().name())
                .collect(Collectors.toList()));
        UserAccountResponseDto responseDto = new UserAccountResponseDto();
        responseDto.setUsername(account.getUsername());
        responseDto.setRoles(account.getRole().stream()
                .map(roleMapper::toDto).collect(Collectors.toList()));
        return responseDto;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserAccountLoginDto requestDto) {
        if (checkOnStatus(requestDto)) {
            UserAccount account = authenticationService.login(requestDto.getUsername(), requestDto.getPassword());
            String token = provider.createToken(account.getUsername(), account.getRole().stream()
                    .map(r -> r.getRoleName().name())
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.LOCKED);
    }

    private boolean checkOnStatus(UserAccountLoginDto requestDto) {
        String username = requestDto.getUsername();
        UserAccount account = accountService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found with username: " + username));
        return account.getStatus() == UserAccount.Status.ACTIVE;
    }
}