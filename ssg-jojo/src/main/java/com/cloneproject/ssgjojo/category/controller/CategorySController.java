package com.cloneproject.ssgjojo.category.controller;

import com.cloneproject.ssgjojo.category.domain.CategoryS;
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
    public CategoryS addCategory(@RequestBody CategoryS categoryS) {
        return iCategorySService.addCategory(categoryS);
    }

    @GetMapping("/categoryS/getAll")
    public List<CategoryS> getAllCategory() {
        return iCategorySService.getAllCategory();
    }
}
