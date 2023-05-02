package com.example.expensetracker.service;

import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.model.request.LoginRequest;
import com.example.expensetracker.model.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	@Override
	public AuthenticationResponse authenticate(LoginRequest request) {
		Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);

//		System.out.println(request.getUsername() + request.getPassword());
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			String jwt = jwtService.generateToken(authentication);
			return AuthenticationResponse.builder().token(jwt).expiredAt(expiredAt).build();
		} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))) {
			String jwt = jwtService.generateToken(authentication);
			return AuthenticationResponse.builder().token(jwt).expiredAt(expiredAt).build();
		} else {
			throw new BadRequestException("Username or Password is incorrect!");
		}
	}
}