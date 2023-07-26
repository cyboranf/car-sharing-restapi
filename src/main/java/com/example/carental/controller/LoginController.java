package com.example.carental.controller;

import com.example.carental.dto.login.LoginRequest;
import com.example.carental.dto.login.LoginResponse;
import com.example.carental.dto.register.UserRegistrationRequest;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.mapper.UserMapper;
import com.example.carental.model.User;
import com.example.carental.security.JwtTokenProvider;
import com.example.carental.service.EmailService;
import com.example.carental.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final EmailService emailService;

    public LoginController(UserService userService, UserMapper userMapper, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, EmailService emailService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getFirstName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserResponseDTO newUser = userService.registerUser(registrationRequest);
            User user = userMapper.fromResDTO(newUser);

            String verificationLink = "http://localhost:8080/api/verify?token=" + newUser.getId();

            try {
                emailService.sendVerificationEmail(user, verificationLink);
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
                System.err.println("Error while sending verification email: " + e.getMessage());

            }

            UserResponseDTO userResponseDTO = userMapper.toDTO(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
