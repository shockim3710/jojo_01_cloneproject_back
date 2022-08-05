package com.cloneproject.ssgjojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDeleteDto {

    private Long id; // 기본키
    private Boolean isLeave; // 탈퇴 여부
}
