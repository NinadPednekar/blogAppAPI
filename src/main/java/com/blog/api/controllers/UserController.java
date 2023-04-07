package com.blog.api.controllers;

import com.blog.api.entities.User;
import com.blog.api.payloads.UserDto;
import com.blog.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String test(){
        return "Test method";
    }

    //POST-Create User
    @PostMapping("/api/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        System.out.println(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //PUT-Update User

    //Delete-Delete User

    //Get-Get User

}
