package com.example.carental.dto.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
