package com.blog.api.security;

import lombok.Data;

@Data
public class JWTAuthRequest {
    private String username;
    private String password;
}
