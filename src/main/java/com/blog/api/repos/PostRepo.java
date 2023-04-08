package com.blog.api.repos;

import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
