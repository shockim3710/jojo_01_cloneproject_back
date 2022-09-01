package com.cloneproject.ssgjojo.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionInputDto {

    private String title;
    private String questionMain;
    private boolean lockCase;

    private Long productId;
}
