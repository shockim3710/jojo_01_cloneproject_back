package com.cloneproject.ssgjojo.category.dto;

import com.cloneproject.ssgjojo.category.domain.CategoryXl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryLDto {

    private Long id;

    private Long categoryXl;
    private String lcategoryname;

}
