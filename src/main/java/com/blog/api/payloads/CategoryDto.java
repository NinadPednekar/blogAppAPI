package com.blog.api.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Minimum size for Category is 4")
    private String category;

    @NotEmpty
    @Size(min = 10, message = "Minimum size for Description is 10")
    private String description;

}
