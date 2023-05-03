package com.example.expensetracker.service;

import com.example.expensetracker.model.DTO.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long user_id, UserDto userInfo);

    String deleteUser(Long id);
}
