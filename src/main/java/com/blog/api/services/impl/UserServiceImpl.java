package com.blog.api.services.impl;

import com.blog.api.entities.User;
import com.blog.api.exc.ResourceNotFoundException;
import com.blog.api.payloads.UserDto;
import com.blog.api.repos.UserRepo;
import com.blog.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        UserDto returnedUser = this.usertoDto(savedUser);
        return returnedUser;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

        User user = this.userRepo.findById(id).orElseThrow((() -> new ResourceNotFoundException("User", "id", id)));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPass(userDto.getPass());
        user.setAbout(userDto.getAbout());
        System.out.println(userDto.getPass());

        User updatedUser = userRepo.save(user);

        return usertoDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow((() -> new ResourceNotFoundException("User", "id", id)));

        return usertoDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDto> usersDto = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());

        return usersDto;
    }

    @Override
    public void deleteUser(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow((() -> new ResourceNotFoundException("User", "id", id)));
        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPass(userDto.getPass());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto usertoDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPass(user.getPass());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

}
