package com.example.accountcontrol.dto;

import javax.validation.constraints.Size;
import com.example.accountcontrol.lib.FieldsValueMatch;
import com.example.accountcontrol.lib.ValidPassword;
import com.example.accountcontrol.lib.ValidUsername;
import lombok.Getter;
import lombok.Setter;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Getter
@Setter
public class UserAccountAddingNewDto {
    @Size(min = 3, max = 16)
    @ValidUsername
    private String username;
    @Size(min = 3, max = 16)
    @ValidPassword
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
}
