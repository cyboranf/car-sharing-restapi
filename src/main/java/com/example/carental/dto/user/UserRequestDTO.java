package com.example.carental.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UserRequestDTO {
    @NotEmpty
    @Email
    private String email;
    private String firstName;
    private String lastName;
    @NotEmpty
    private String password;
    private int contactsCount;
    private int msgCount;
    private boolean active;
    private Long addressId;
    private Set<Integer> rolesIds;
}
