package com.cloneproject.ssgjojo.attention.dto;

import lombok.Data;

import java.util.List;

/**
 * AttentionInputFolderDto: 좋아요 항목을 다른 폴더에 추가할 때 사용되는 Dto
 * userId: 사용자 PK
 * attentionId: 좋아요 항목 PK
 * folderIdList: 좋아요 폴더 PK 리스트
 */
@Data
public class AttentionInputFolderDto {
    private Long userId;
    private List<Long> attentionIdList;
    private List<Long> folderIdList;
}