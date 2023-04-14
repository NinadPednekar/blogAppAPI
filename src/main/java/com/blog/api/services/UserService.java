package com.blog.api.services;

import com.blog.api.payloads.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    //register user
    UserDto registerUser(UserDto userDto);
    //create user
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer id);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);

}
