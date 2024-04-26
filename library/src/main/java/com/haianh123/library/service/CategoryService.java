package com.haianh123.library.service;

import com.haianh123.library.dto.request.CategoryCreateRequest;
import com.haianh123.library.dto.response.CategoryResponse;
import com.haianh123.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryService{
    List<CategoryResponse> getAllCategory();
    void createCategory(CategoryCreateRequest categoryCreateRequest);
    void updateCategory(int id,CategoryCreateRequest categoryCreateRequest);
    void deleteCategory(int id);
    CategoryResponse getCategoryById(int id);
}
