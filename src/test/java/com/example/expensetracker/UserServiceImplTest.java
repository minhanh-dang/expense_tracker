package com.example.expensetracker;

import com.example.expensetracker.model.DTO.UserDto;
import com.example.expensetracker.model.entity.RoleName;
import com.example.expensetracker.model.entity.Roles;
import com.example.expensetracker.model.entity.Users;
import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.model.mapper.UserMapper;
import com.example.expensetracker.repository.RoleRepository;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        Users user = new Users();
        user.setId(userId);
        user.setUsername("Test User");
        user.setPassword("password");
//        Roles role = roleRepository.findByRoleName(RoleName.ROLE_EMPLOYEE).get();
//        user.setRole(RoleName.ROLE_EMPLOYEE);


        UserDto userDto = new UserDto();
        userDto.setName(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setId(user.getId());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        // Act
        UserDto retrievedUserDto = userService.getUserById(userId);

        // Assert
        assertEquals(userDto, retrievedUserDto);
    }

    @Test
    void testAddUser() {
        // Arrange
        UserDto addUserDto = new UserDto();
        addUserDto.setName("New Test User");
        addUserDto.setPassword("password");

        Users addUser = new Users();
        addUser.setId(1L);
        addUser.setUsername(addUserDto.getName());
        addUser.setPassword(encoder.encode(addUserDto.getPassword()));
        Roles role = new Roles();
        role.setId(1L);
        role.setRoleName(RoleName.ROLE_EMPLOYEE);
        addUser.setRole(role);

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setName(addUser.getUsername());
        expectedUserDto.setPassword(addUser.getPassword());
        expectedUserDto.setId(addUser.getId());

        when(roleRepository.findByRoleName(RoleName.ROLE_EMPLOYEE)).thenReturn(java.util.Optional.of(role));
        when(userRepository.save(any(Users.class))).thenReturn(addUser);
        when(userMapper.toDto(addUser)).thenReturn(expectedUserDto);

        // Act
        UserDto createdUserDto = userService.addUser(addUserDto);

        // Assert
        assertEquals(expectedUserDto.getName(), createdUserDto.getName());
        assertEquals(expectedUserDto.getPassword(), createdUserDto.getPassword());
        assertEquals(expectedUserDto.getId(), createdUserDto.getId());
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        Users user1 = new Users();
        user1.setId(1L);
        user1.setUsername("Test User 1");
        user1.setPassword("password1");

        Roles role1 = new Roles();
        role1.setId(1L);
        role1.setRoleName(RoleName.ROLE_EMPLOYEE);
        user1.setRole(role1);

        Users user2 = new Users();
        user2.setId(2L);
        user2.setUsername("Test User 2");
        user2.setPassword("password2");
        Roles role2 = new Roles();
        role1.setId(2L);
        role1.setRoleName(RoleName.ROLE_MANAGER);

        List<Users> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        UserDto userDto1 = new UserDto();
        userDto1.setName(user1.getUsername());
        userDto1.setPassword(user1.getPassword());
        userDto1.setId(user1.getId());

        UserDto userDto2 = new UserDto();
        userDto2.setName(user2.getUsername());
        userDto2.setPassword(user2.getPassword());
        userDto2.setId(user2.getId());

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto1);
        userDtoList.add(userDto2);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.toDto(user1)).thenReturn(userDto1);
        when(userMapper.toDto(user2)).thenReturn(userDto2);

        // Act
        List<UserDto> retrievedUserDtoList = userService.getAllUsers();

        // Assert
        assertEquals(userDtoList, retrievedUserDtoList);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;

        UserDto updateUserDto = new UserDto();
        updateUserDto.setId(userId);
        updateUserDto.setName("Updated Test User");
        updateUserDto.setPassword("updatedpassword");


        Users existingUser = new Users();
        existingUser.setId(userId);
        existingUser.setUsername("Test User");
        existingUser.setPassword("password");

        Roles role = new Roles();
        role.setId(1L);
        role.setRoleName(RoleName.ROLE_EMPLOYEE);
        existingUser.setRole(role);


        UserDto existingUserDto = new UserDto();
        existingUserDto.setName(existingUser.getUsername());
        existingUserDto.setPassword(existingUser.getPassword());
        existingUserDto.setId(existingUser.getId());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(Users.class))).thenReturn(existingUser);
        when(userMapper.toDto(existingUser)).thenReturn(existingUserDto);


        // Act
        UserDto updatedUserDto = userService.updateUser(userId, updateUserDto);

        // Assert
        assertEquals(updateUserDto.getName(), updatedUserDto.getName());
        assertEquals(updateUserDto.getPassword(), updatedUserDto.getPassword());
        assertEquals(userId, updatedUserDto.getId());
    }

    @Test
    void testUpdateUserWithNotFoundUser() {
        // Arrange
        Long userId = 1L;
        UserDto updateUserDto = new UserDto();
        updateUserDto.setName("Updated Test User");
        updateUserDto.setPassword("updatedpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> userService.updateUser(userId, updateUserDto));
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(anyLong());

        // Act
        String result = userService.deleteUser(userId);

        // Assert
        assertNull(result);
    }
}