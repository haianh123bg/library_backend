package com.haianh123.library.service.impl;

import com.haianh123.library.dto.request.CategoryCreateRequest;
import com.haianh123.library.dto.response.CategoryResponse;
import com.haianh123.library.entity.Category;
import com.haianh123.library.mapper.CategoryMapper;
import com.haianh123.library.repository.CategoryRepository;
import com.haianh123.library.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    // trả về tất cả danh mục
    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories=categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for(Category cate : categories){
            CategoryResponse categoryResponse = categoryMapper.CategorytoCategoryResponse(cate);
            categoryResponseList.add(categoryResponse);
        }
        return categoryResponseList;

    }
    // tạo 1 danh mục mới
    @Override
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        Category category = categoryMapper.CategoryRequesttoCategory(categoryCreateRequest);
        categoryRepository.save(category);
    }
    // chỉnh sửa 1 danh mục
    @Override
    public void updateCategory(int id, CategoryCreateRequest categoryCreateRequest) {
        Category category = categoryRepository.getCategoryById(id);
        category.setName(categoryCreateRequest.getName());
        category.setTotalBooks(categoryCreateRequest.getTotalBooks());
        category.setCreatedAt(categoryCreateRequest.getCreatedAt());
        category.setUpdatedAt(categoryCreateRequest.getUpdatedAt());
        categoryRepository.save(category);
    }
    // xóa 1 danh mục
    @Override
    public void deleteCategory(int id) {
        Category category = categoryRepository.getCategoryById(id);
        categoryRepository.delete(category);
    }
    // trả về 1 danh mục theo id
    @Override
    public CategoryResponse getCategoryById(int id) {
        Category category = categoryRepository.getCategoryById(id);
        CategoryResponse categoryResponse = categoryMapper.CategorytoCategoryResponse(category);
        return categoryResponse;
    }


}
