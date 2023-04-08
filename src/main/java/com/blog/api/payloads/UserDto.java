package com.blog.api.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "Name must be min of 4 characters !!")
    private String name;
    @NotEmpty
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters !!")
    private String pass;
    @NotEmpty
    private String about;

}
