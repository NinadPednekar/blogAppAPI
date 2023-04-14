package com.blog.api.services.impl;

import com.blog.api.entities.Category;
import com.blog.api.exc.ResourceNotFoundException;
import com.blog.api.payloads.CategoryDto;
import com.blog.api.repos.CategoryRepo;
import com.blog.api.services.CategotyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategotyService {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired
    private CategoryRepo categoryRepo;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category savedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));
        cat.setCategory(categoryDto.getCategory());
        cat.setDescription(categoryDto.getDescription());
        Category updatedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoty", "Category Id", id));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoty", "Category Id", id));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> list = this.categoryRepo.findAll();
        List<CategoryDto> dtoList = list.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return dtoList;
    }
}
