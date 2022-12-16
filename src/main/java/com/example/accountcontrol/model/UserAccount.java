package com.example.accountcontrol.model;

import javax.persistence.*;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @ManyToMany
    private Set<Role> role;
    @Enumerated(EnumType.STRING)
    private Status status;


    public enum Status {
        ACTIVE("Active"),
        INACTIVE("Inactive");

        private String value;
        Status(String value) {
            this.value = value;
        }
    }
}
