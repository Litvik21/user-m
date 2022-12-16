package com.example.accountcontrol.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountLoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
