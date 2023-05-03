package com.example.expensetracker.service;

import com.example.expensetracker.model.DTO.UserDto;
import com.example.expensetracker.model.entity.RoleName;
import com.example.expensetracker.model.entity.Roles;
import com.example.expensetracker.model.entity.Users;
import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.model.mapper.UserMapper;
import com.example.expensetracker.repository.RoleRepository;
import com.example.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;

    @Override
    public UserDto getUserById(Long id) {

        Users user = userRepository.findById(id).get();

        UserDto userDto = userMapper.toDto(user);

        return userDto;
    }

    @Override
    public UserDto addUser(UserDto userDto) {

        Users user = new Users();
        user.setUsername(userDto.getName());
        user.setPassword(encoder.encode(userDto.getPassword()));
        Roles role = roleRepository.findByRoleName(RoleName.ROLE_EMPLOYEE).get();
        user.setRole(role);
        Users addUser = userRepository.save(user);

        UserDto addUserDto = userMapper.toDto(addUser);

        return addUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long user_id, UserDto userDto) {
        Users existingUser = userRepository.findById(user_id).orElseThrow(() -> new BadRequestException("User not found!"));;
        existingUser.setUsername(userDto.getName());
        existingUser.setPassword(encoder.encode(userDto.getPassword()));
        return userMapper.toDto(userRepository.save(existingUser));
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
//		return "User deleted: " + id;
        return null;
    }
}
