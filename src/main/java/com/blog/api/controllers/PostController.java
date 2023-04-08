package com.blog.api.controllers;

import com.blog.api.entities.Post;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;
import com.blog.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //Create post
    @PostMapping("/{user}/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Integer userId,
                                              @PathVariable("categoryId") Integer categoryId){
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    //Get post by user
    @GetMapping("/user/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("id") Integer id){
        List<PostDto> postDtos = this.postService.getPostByUser(id);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    //Get post by category
    @GetMapping("/category/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Integer id){
        List<PostDto> postDtos = this.postService.getPostByCategory(id);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    //Get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                     @ RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        PostResponse response = this.postService.getAllPosts(pageNumber, pageSize, sortBy);
        return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
    }

    //Get post by id
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id){
        PostDto postDto = this.postService.getPostById(id);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    //Delete post
    @DeleteMapping("/posts/{id}")
    public ApiResponse deletePost(@PathVariable("id") Integer id){
        this.postService.deletePost(id);
        return new ApiResponse("Post deleted Successfully", true);
    }

    //Update post
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") Integer id){
        PostDto updatePost = this.postService.updatePost(postDto, id);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //search method
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

}
