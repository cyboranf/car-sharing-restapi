package com.example.carental.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private int contactsCount;
    private int msgCount;
    private boolean active;
    private Long addressId;
    private Set<Integer> roleIds;
}
