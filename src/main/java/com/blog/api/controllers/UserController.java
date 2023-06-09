package com.blog.api.controllers;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.UserDto;
import com.blog.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String test(){
//        return "Test method";
//    }

    //POST-Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //PUT-Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer id){
        UserDto updatedUser = this.userService.updateUser(userDto, id);
        return ResponseEntity.ok(updatedUser);
    }

    //Delete-Delete User
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id){
        this.userService.deleteUser(id);
        ResponseEntity userDeletedSuccessfully = new ResponseEntity(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
        return userDeletedSuccessfully;
    }

    //Get-Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //Get-Get User
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

}
