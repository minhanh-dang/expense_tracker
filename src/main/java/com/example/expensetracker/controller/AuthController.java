package com.example.expensetracker.controller;

import com.example.expensetracker.model.request.LoginRequest;
import com.example.expensetracker.model.response.AuthenticationResponse;
import com.example.expensetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
