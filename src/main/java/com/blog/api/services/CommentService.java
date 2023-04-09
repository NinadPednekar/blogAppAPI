package com.blog.api.services;

import com.blog.api.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer id);

    void deleteComment(Integer id);
}
