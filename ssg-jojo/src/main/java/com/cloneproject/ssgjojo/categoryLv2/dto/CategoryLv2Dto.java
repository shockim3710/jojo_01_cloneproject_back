package com.cloneproject.ssgjojo.categoryLv2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryLv2Dto {

    private Long id;

    private Long categoryLv1;
    private String lv2name;

}
