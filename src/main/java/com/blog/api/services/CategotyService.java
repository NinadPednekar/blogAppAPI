package com.blog.api.services;

import com.blog.api.payloads.CategoryDto;

import java.util.List;

public interface CategotyService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    //delete
    void deleteCategory(Integer id);

    //getOne
    CategoryDto getCategory(Integer id);

    //getAll
    List<CategoryDto> getAllCategories();

}
