package com.example.expensetracker.model.mapper;

import com.example.expensetracker.model.DTO.UserDto;
import com.example.expensetracker.model.entity.Users;
import com.example.expensetracker.model.request.UserRequest;
import com.example.expensetracker.model.response.UserResponse;

public interface UserMapper {

    UserDto toDto(Users user);

    UserResponse toResponse(UserDto userDto);

    UserDto toDto(UserRequest request);

}
