package com.cloneproject.ssgjojo.category.controller;

import com.cloneproject.ssgjojo.category.domain.CategoryXl;
import com.cloneproject.ssgjojo.category.service.ICategoryXlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryXlController {

    private final ICategoryXlService iCategoryXlService;

    @PostMapping("/categoryXl/add")
    public CategoryXl addCategory(@RequestBody CategoryXl categoryXl) {
        return iCategoryXlService.addCategory(categoryXl);
    }

    @GetMapping("/categoryXl/getAll")
    public List<CategoryXl> getAll() {
        return iCategoryXlService.getAll();
    }
}
