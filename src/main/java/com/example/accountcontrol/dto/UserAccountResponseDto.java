package com.example.accountcontrol.dto;

import com.example.accountcontrol.model.UserAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAccountResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<RoleResponseDto> roles;
    private UserAccount.Status status;
}
