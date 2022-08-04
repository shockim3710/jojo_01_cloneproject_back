package com.cloneproject.ssgjojo.categoryLv1.controller;

import com.cloneproject.ssgjojo.categoryLv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categoryLv1.service.ICategoryLv1Service;
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
}
