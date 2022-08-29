package com.cloneproject.ssgjojo.categorylv4.controller;

import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categorylv4.dto.CategoryLv4Dto;
import com.cloneproject.ssgjojo.categorylv4.service.ICategoryLv4Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryLv4Controller {

    private final ICategoryLv4Service iCategoryLv4Service;

    @PostMapping("/category/Lv4/add")
    public CategoryLv4 addCategory(@RequestBody CategoryLv4Dto categoryLv4Dto) {
        return iCategoryLv4Service.addCategory(categoryLv4Dto);
    }

    @PutMapping("/category/Lv4/edit")
    public CategoryLv4 editCategory(@RequestBody CategoryLv4Dto categoryLv4Dto) {
        return iCategoryLv4Service.editCategory(categoryLv4Dto);
    }

    @DeleteMapping("/category/Lv4/{id}")
    public void deleteCategory(@PathVariable Long id) {

        iCategoryLv4Service.deleteCategory(id);
    }

}
