package com.cloneproject.ssgjojo.category.controller;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
import com.cloneproject.ssgjojo.category.dto.CategoryLDto;
import com.cloneproject.ssgjojo.category.service.ICategoryLService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryLController {

    private final ICategoryLService iCategoryLService;

    @PostMapping("/categoryL/add")
    public CategoryL addCategory(@RequestBody CategoryLDto categoryLDto) {

        return iCategoryLService.addCategory(categoryLDto);
    }

    @GetMapping("/categoryL/getAll")
    public List<CategoryL> getAllCategory() {
        return iCategoryLService.getAllCategory();
    }

    @PutMapping("/categoryL/edit")
    public CategoryL editCategory(@RequestBody CategoryLDto categoryLDto) {
        return iCategoryLService.editCategory(categoryLDto);
    }
}
