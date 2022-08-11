package com.cloneproject.ssgjojo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserDto {
    private String name; // 사용자 이름
    private String phone; // 사용자 전화번호
}
