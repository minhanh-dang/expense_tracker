package com.example.expensetracker.controller;

import com.example.expensetracker.model.DTO.UserDto;
import com.example.expensetracker.model.mapper.UserMapper;
import com.example.expensetracker.model.request.UserRequest;
import com.example.expensetracker.model.response.UserResponse;
import com.example.expensetracker.security.CurrentUser;
import com.example.expensetracker.security.UserPrincipal;
import com.example.expensetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserMapper userMapper;

	private final UserService userService;

	@GetMapping("/me")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	public UserResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserDto userDto = userService.getUserById(currentUser.getId());

		return userMapper.toResponse(userDto);
	}

	@PostMapping("/addUser")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	public UserResponse addUser(@RequestBody UserRequest request) {

		UserDto userDto = userMapper.toDto(request);
		UserDto user = userService.addUser(userDto);

		return userMapper.toResponse(user);
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	List<UserResponse> getUsers() {
		List<UserDto> users = userService.getAllUsers();
		return users.stream().map(user -> userMapper.toResponse(user)).collect(Collectors.toList());
	}

	@PutMapping("/updateUser")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	UserResponse updateUser(@RequestBody UserRequest userRequest) {
		UserDto userDto = userMapper.toDto(userRequest);
		UserDto updatedUser = userService.updateUser(userDto);
		return userMapper.toResponse(updatedUser);
	}

	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
}