package com.cloneproject.ssgjojo.category.controller;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.service.ICategoryMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryMController {

    private final ICategoryMService iCategoryMService;

    @PostMapping("/categoryM/add")
    public CategoryM addCategory(@RequestBody CategoryM categoryM) {
        return iCategoryMService.addCategory(categoryM);
    }

    @GetMapping("/categoryM/getAll")
    public List<CategoryM> getAll() {
        return iCategoryMService.getAll();
    }
}
