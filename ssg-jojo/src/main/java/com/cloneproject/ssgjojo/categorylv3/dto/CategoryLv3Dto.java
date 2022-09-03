package com.cloneproject.ssgjojo.categorylv3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryLv3Dto {

    private Long id;

    private Long categoryLv2;
    private String lv3name;
}
