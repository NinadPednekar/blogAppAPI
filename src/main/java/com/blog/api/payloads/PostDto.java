package com.blog.api.payloads;

import com.blog.api.entities.Category;
import com.blog.api.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String imageName;
    private Date createdDate;
    private CategoryDto category;
    private UserDto user;

}
