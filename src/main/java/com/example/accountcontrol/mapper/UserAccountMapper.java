package com.example.accountcontrol.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.example.accountcontrol.dto.RoleResponseDto;
import com.example.accountcontrol.dto.UserAccountAddingNewDto;
import com.example.accountcontrol.dto.UserAccountResponseDto;
import com.example.accountcontrol.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {
    private final RoleMapper roleMapper;

    public UserAccountMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserAccountResponseDto toDto(UserAccount account) {
        UserAccountResponseDto dto = new UserAccountResponseDto();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setFirstName(account.getFirstName());
        dto.setLastName(account.getLastName());
        List<RoleResponseDto> roles = account.getRole().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
        dto.setRoles(roles);
        dto.setStatus(account.getStatus());
        return dto;
    }

    public UserAccount toModel(UserAccountAddingNewDto requestDto) {
        UserAccount account = new UserAccount();
        account.setUsername(requestDto.getUsername());
        account.setPassword(requestDto.getPassword());
        account.setFirstName(requestDto.getFirstName());
        account.setLastName(requestDto.getLastName());
        account.setRole(new HashSet<>());
        return account;
    }
}
