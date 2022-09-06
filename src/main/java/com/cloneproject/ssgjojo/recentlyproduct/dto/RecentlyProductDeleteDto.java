package com.cloneproject.ssgjojo.recentlyproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentlyProductDeleteDto {
    private List<Long> id;
}
