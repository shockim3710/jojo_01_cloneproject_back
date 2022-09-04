package com.cloneproject.ssgjojo.attention.dto;

import lombok.Data;

import java.util.List;

/**
 * AttentionEditFolderDto: 특정 폴더에 있는 좋아요 항목 다른 폴더로 이동할 때 사용되는 Dto
 * userId: 사용자 PK
 * originFolderId: 기존 폴더 PK
 * attentionIdList : 이동할 좋아요 항목 PK 리스트
 * folderIdList : 좋아요 폴더 PK 리스트 
 */
@Data
public class AttentionEditFolderDto {
    private Long userId;
    private Long originFolderId;
    private List<Long> attentionIdList;
    private List<Long> folderIdList;
}
