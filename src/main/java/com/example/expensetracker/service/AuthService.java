package com.example.expensetracker.service;

import com.example.expensetracker.model.request.LoginRequest;
import com.example.expensetracker.model.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse authenticate(LoginRequest request);
}
