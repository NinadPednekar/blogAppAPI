package com.blog.api.services.impl;

import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.entities.User;
import com.blog.api.exc.ResourceNotFoundException;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.UserDto;
import com.blog.api.repos.CategoryRepo;
import com.blog.api.repos.PostRepo;
import com.blog.api.repos.UserRepo;
import com.blog.api.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", catId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = this.postRepo.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));
        List<Post> list = this.postRepo.findByCategory(cat);
        List<PostDto> postDtos = list.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", id));
        List<Post> list= this.postRepo.findByUser(user);
        List<PostDto> postDtos = list.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }
}
