package com.cloneproject.ssgjojo.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnAOutputDto {
    private Long id;
    private String title;
    private String questionMain;
    private String answerMain;
    private String questionDate;
    private String answerDate;
    private boolean lockCase;
    private String userAccount;
    private Long productId;
}
