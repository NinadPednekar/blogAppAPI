package com.blog.api.services.impl;

import com.blog.api.entities.Comment;
import com.blog.api.entities.Post;
import com.blog.api.exc.ResourceNotFoundException;
import com.blog.api.payloads.CommentDto;
import com.blog.api.repos.CommentRepo;
import com.blog.api.repos.PostRepo;
import com.blog.api.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PostRepo postRepo;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CommentRepo commentRepo;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment addedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(addedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", id));
        this.commentRepo.delete(comment);
    }
}
