package com.cloneproject.ssgjojo.category.controller;

import com.cloneproject.ssgjojo.category.domain.CategoryL;
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
    public CategoryL addCategory(@RequestBody CategoryL categoryL) {
        return iCategoryLService.addCategory(categoryL);
    }

    @GetMapping("/categoryL/getAll")
    public List<CategoryL> getAll() {
        return iCategoryLService.getAll();
    }
}
