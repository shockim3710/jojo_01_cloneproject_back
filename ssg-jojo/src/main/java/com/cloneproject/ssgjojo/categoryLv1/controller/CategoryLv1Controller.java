package com.cloneproject.ssgjojo.categoryLv1.controller;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv1.service.ICategoryLv1Service;
import com.cloneproject.ssgjojo.categoryLv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categoryLv2.dto.CategoryLv2Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryLv1Controller {

    private final ICategoryLv1Service iCategoryLv1Service;

    @PostMapping("/category/Lv1/add")
    public CategoryLv1 addCategory(@RequestBody CategoryLv1 categoryLv1) {
        return iCategoryLv1Service.addCategory(categoryLv1);
    }

    @GetMapping("/category/Lv1/getAll")
    public List<CategoryLv1> getAllCategory() {
        return iCategoryLv1Service.getAllCategory();
    }

    @PutMapping("/category/Lv1/edit")
    public CategoryLv1 editCategory(@RequestBody CategoryLv1 categoryLv1) {
        return iCategoryLv1Service.editCategory(categoryLv1);
    }

    @DeleteMapping("/category/Lv1/{id}")
    public void deleteCategory(@PathVariable Long id) {
        iCategoryLv1Service.deleteCategory(id);
    }

    @GetMapping("/category/Lv1/{id}")
    public CategoryLv1 getCategoryById(@PathVariable Long id) {
        return iCategoryLv1Service.getCategoryById(id);
    }

}
