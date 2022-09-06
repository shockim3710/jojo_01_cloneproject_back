package com.cloneproject.ssgjojo.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerInputDto {
    private Long id;
    private Long productId;
    private String answer;
}
