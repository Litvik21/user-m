package com.example.accountcontrol.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.example.accountcontrol.lib.FieldsValueMatch;
import com.example.accountcontrol.lib.ValidPassword;
import com.example.accountcontrol.lib.ValidUsername;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountAddingNewDto {
    @Size(min = 3, max = 16)
    @ValidUsername
    private String username;
    @Size(min = 3, max = 16)
    @NotEmpty
    @ValidPassword(field = "password", fieldMatch = "repeatPassword")
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
}


