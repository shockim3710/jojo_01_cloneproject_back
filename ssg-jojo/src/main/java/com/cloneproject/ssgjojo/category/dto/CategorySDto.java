package com.cloneproject.ssgjojo.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorySDto {

    private Long id;

    private Long categoryM;
    private String scategoryname;
}
