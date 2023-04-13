package com.blog.api.exc;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
    public ApiException(){
        super();
    }
}
