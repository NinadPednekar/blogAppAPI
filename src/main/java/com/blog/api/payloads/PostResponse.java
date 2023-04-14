package com.blog.api.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private boolean lastPage;
    private Integer totalPages;

}
