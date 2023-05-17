package com.example.expensetracker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.model.request.LoginRequest;
import com.example.expensetracker.model.response.AuthenticationResponse;
import java.util.Collections;
import java.util.Date;

import com.example.expensetracker.service.AuthServiceImpl;
import com.example.expensetracker.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticate_withManagerRole() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("manager");
        request.setPassword("password");
        SimpleGrantedAuthority managerAuthority = new SimpleGrantedAuthority("ROLE_MANAGER");
        Authentication authentication = new UsernamePasswordAuthenticationToken("manager", "password",
                Collections.singletonList(managerAuthority));
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtService.generateToken(authentication)).thenReturn("jwt-token");

        // Act
        AuthenticationResponse response = authService.authenticate(request);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals("jwt-token", response.getToken());
        Assertions.assertTrue(response.getExpiredAt().after(new Date()));
    }

    @Test
    public void testAuthenticate_withEmployeeRole() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("employee");
        request.setPassword("password");
        SimpleGrantedAuthority employeeAuthority = new SimpleGrantedAuthority("ROLE_EMPLOYEE");
        Authentication authentication = new UsernamePasswordAuthenticationToken("employee", "password",
                Collections.singletonList(employeeAuthority));
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtService.generateToken(authentication)).thenReturn("jwt-token");

        // Act
        AuthenticationResponse response = authService.authenticate(request);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals("jwt-token", response.getToken());
        Assertions.assertTrue(response.getExpiredAt().after(new Date()));
    }

    @Test
    public void testAuthenticate_withInvalidCredentials() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("password");
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password",
                Collections.singletonList(userAuthority));
        when(authenticationManager.authenticate(any())).thenThrow(new BadRequestException("Invalid credentials"));

        // Act and Assert
        Assertions.assertThrows(BadRequestException.class, () -> authService.authenticate(request));
    }

}