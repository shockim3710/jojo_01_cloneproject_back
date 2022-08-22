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
public class QnAEditDto {

    private Long id;
    private String title;
    private String questionMain;
    private Timestamp questionDate;
    private boolean lockCase;

    private Long userId;
    private Long productId;
}
