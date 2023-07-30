package com.example.carental.controller.login;

import com.example.carental.controller.login.LoginController;
import com.example.carental.dto.login.LoginRequest;
import com.example.carental.dto.login.LoginResponse;
import com.example.carental.dto.register.UserRegistrationRequest;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.mapper.UserMapper;
import com.example.carental.model.User;
import com.example.carental.security.JwtTokenProvider;
import com.example.carental.service.EmailService;
import com.example.carental.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider tokenProvider;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateUser() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setFirstName("username");
        loginRequest.setPassword("password");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getFirstName(), loginRequest.getPassword()))).thenReturn(authentication);
        when(tokenProvider.generateToken(authentication)).thenReturn("jwtToken");

        ResponseEntity<?> result = loginController.authenticateUser(loginRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new LoginResponse("jwtToken"), result.getBody());
    }

    @Test
    public void testRegister() throws Exception {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = new User();
        when(userService.registerUser(registrationRequest)).thenReturn(userResponseDTO);
        when(userMapper.fromResDTO(userResponseDTO)).thenReturn(user);

        ResponseEntity<?> result = loginController.register(registrationRequest);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

}
