package com.example.expensetracker.model.mapper;

import com.example.expensetracker.model.DTO.UserDto;
import com.example.expensetracker.model.entity.Users;
import com.example.expensetracker.model.request.UserRequest;
import com.example.expensetracker.model.response.UserResponse;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(Users user) {

        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());

        return userDto;
    }

    @Override
    public UserResponse toResponse(UserDto userDto) {

        UserResponse response = new UserResponse();
        response.setId(userDto.getId());
        response.setName(userDto.getName());

        return response;
    }

    @Override
    public UserDto toDto(UserRequest request) {

        UserDto userDto = new UserDto();
        userDto.setName(request.getName());
        userDto.setPassword(request.getPassword());

        return userDto;
    }

}
