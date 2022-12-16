package com.example.accountcontrol.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.example.accountcontrol.dto.UserAccountAddingNewDto;
import com.example.accountcontrol.dto.UserAccountResponseDto;
import com.example.accountcontrol.mapper.UserAccountMapper;
import com.example.accountcontrol.model.UserAccount;
import com.example.accountcontrol.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountMapper mapper;
    private final UserAccountService accountService;

    public UserAccountController(UserAccountMapper mapper, UserAccountService accountService) {
        this.mapper = mapper;
        this.accountService = accountService;
    }

    @GetMapping
    public List<UserAccountResponseDto> getAll() {
        return accountService.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserAccountResponseDto get(@PathVariable Long id) {
        return mapper.toDto(accountService.get(id));
    }

    @GetMapping("/{id}/admin")
    public UserAccountResponseDto getForAdmin(@PathVariable Long id) {
        UserAccount account = accountService.get(id);
        if(account.getStatus() == UserAccount.Status.ACTIVE) {
            account.setStatus(UserAccount.Status.INACTIVE);
        } else {
            account.setStatus(UserAccount.Status.ACTIVE);
        }
        return mapper.toDto(account);
    }

    @PutMapping("/{id}/edit")
    public UserAccountResponseDto update(@PathVariable Long id,
                                         @RequestBody UserAccountAddingNewDto requestDto) {
        UserAccount account = mapper.toModel(requestDto);
        account.setId(id);
        return mapper.toDto(accountService.update(account));
    }
}
