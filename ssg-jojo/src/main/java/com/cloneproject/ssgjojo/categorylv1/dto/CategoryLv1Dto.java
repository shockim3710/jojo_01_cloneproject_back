package com.cloneproject.ssgjojo.categorylv1.dto;

import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv2.dto.CategoryLv2Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryLv1Dto {

    private String lv1name;
    private String lv1imgpath;

    List<CategoryLv2Dto> categoryLv2List;

}
