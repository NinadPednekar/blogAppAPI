package com.blog.api.services;

import com.blog.api.entities.Post;
import com.blog.api.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto, Integer userId, Integer catId);

    //update post
    PostDto updatePost(PostDto postDto, Integer id);

    //delete post
    void deletePost(Integer id);

    //get all posts
    List<PostDto> getAllPosts();

    //get one post
    PostDto getPostById(Integer id);

    //get post by category
    List<PostDto> getPostByCategory(Integer id);

    //get post by user
    List<PostDto> getPostByUser(Integer id);

}