package com.example.expensetracker.security;

import com.example.expensetracker.model.entity.Users;
import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Users> optUser = repository.findByUserName(userName);

		if (!optUser.isPresent()) {
			throw new BadRequestException("User Not Found");
		}
		return UserPrincipal.build(optUser.get());
	}
}