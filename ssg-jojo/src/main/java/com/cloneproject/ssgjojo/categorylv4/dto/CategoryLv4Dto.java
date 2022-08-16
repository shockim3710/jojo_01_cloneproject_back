package com.cloneproject.ssgjojo.categorylv4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryLv4Dto {

    private Long id;

    private Long categoryLv3;
    private String lv4name;
}
