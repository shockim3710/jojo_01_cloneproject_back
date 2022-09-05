package com.cloneproject.ssgjojo.attention.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AttentionDeleteFolderDto: 특정 폴더에서 좋아요 삭제할 때 사용되는 Dto
 * attentionId: 좋아요 항목 PK
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttentionDeleteFolderDto {
    private List<Long> attentionId;
}
