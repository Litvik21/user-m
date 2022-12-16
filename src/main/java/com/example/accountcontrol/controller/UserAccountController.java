package com.example.accountcontrol.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.example.accountcontrol.dto.UserAccountAddingNewDto;
import com.example.accountcontrol.dto.UserAccountResponseDto;
import com.example.accountcontrol.controller.mapper.UserAccountMapper;
import com.example.accountcontrol.model.UserAccount;
import com.example.accountcontrol.service.UserAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get list of accounts")
    public List<UserAccountResponseDto> getAll() {
        return accountService.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Show details of account user for all roles")
    public UserAccountResponseDto get(
            @PathVariable @ApiParam(value = "id of account user that you want get")
                                          Long id) {
        return mapper.toDto(accountService.get(id));
    }

    @GetMapping("/{id}/admin")
    @ApiOperation(value = "This endpoint able ADMIN change status for USER")
    public UserAccountResponseDto getForAdmin(
            @PathVariable @ApiParam(value = "id of account user that you want change status")
            Long id) {
        UserAccount account = accountService.get(id);
        if(account.getStatus() == UserAccount.Status.ACTIVE) {
            account.setStatus(UserAccount.Status.INACTIVE);
        } else {
            account.setStatus(UserAccount.Status.ACTIVE);
        }
        return mapper.toDto(account);
    }

    @PutMapping("/{id}/edit")
    @ApiOperation(value = "Update account information of account user")
    public UserAccountResponseDto update(
            @PathVariable @ApiParam(value = "id of account user that you want update") Long id,
                                         @RequestBody UserAccountAddingNewDto requestDto) {
        UserAccount account = mapper.toModel(requestDto);
        account.setId(id);
        return mapper.toDto(accountService.update(account));
    }
}
