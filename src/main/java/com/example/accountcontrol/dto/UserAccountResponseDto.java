package com.example.accountcontrol.dto;

import java.util.List;
import com.example.accountcontrol.model.UserAccount;
import lombok.Getter;
import lombok.Setter;

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
