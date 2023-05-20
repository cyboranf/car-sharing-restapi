package com.example.carental.controller;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequestDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<CarResponseDTO>> getUsersCars(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUsersCars(userId));
    }

}
