package com.cloneproject.ssgjojo.category.controller;

import com.cloneproject.ssgjojo.category.domain.CategoryM;
import com.cloneproject.ssgjojo.category.domain.CategoryS;
import com.cloneproject.ssgjojo.category.dto.CategoryMDto;
import com.cloneproject.ssgjojo.category.dto.CategorySDto;
import com.cloneproject.ssgjojo.category.service.ICategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategorySController {

    private final ICategorySService iCategorySService;

    @PostMapping("/categoryS/add")
    public CategoryS addCategory(@RequestBody CategorySDto categorySDto) {
        return iCategorySService.addCategory(categorySDto);
    }

    @GetMapping("/categoryS/getAll")
    public List<CategoryS> getAllCategory() {
        return iCategorySService.getAllCategory();
    }

    @PutMapping("/categoryS/edit")
    public CategoryS editCategory(@RequestBody CategorySDto categorySDto) {
        return iCategorySService.editCategory(categorySDto);
    }
}
