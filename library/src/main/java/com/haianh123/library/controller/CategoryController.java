package com.haianh123.library.controller;

import com.haianh123.library.dto.request.CategoryCreateRequest;
import com.haianh123.library.dto.response.CategoryResponse;
import com.haianh123.library.service.impl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryServiceImpl = categoryService;
    }
    @GetMapping
    public ResponseEntity getAllCategory(){
        List<CategoryResponse> categoryResponses=categoryServiceImpl.getAllCategory();
        return ResponseEntity.ok().body(categoryResponses);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable(name ="categoryId") int id){
        CategoryResponse categoryResponse =categoryServiceImpl.getCategoryById(id);
        return ResponseEntity.ok().body(categoryResponse);
    }
    @PostMapping
    public ResponseEntity insertCategory(@RequestBody CategoryCreateRequest categoryCreateRequest){
        categoryServiceImpl.createCategory(categoryCreateRequest);
        return ResponseEntity.ok().body(categoryCreateRequest);
    }
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable(name="categoryId") int id){
        categoryServiceImpl.deleteCategory(id);
        return ResponseEntity.ok().body(null);
    }
    @PutMapping("/category/{id}")
    public ResponseEntity updateCategory(@PathVariable(name="id") int id, @RequestBody CategoryCreateRequest categoryCreateRequest){
         categoryServiceImpl.updateCategory(id,categoryCreateRequest);
         return ResponseEntity.ok().body(null);
    }

}
