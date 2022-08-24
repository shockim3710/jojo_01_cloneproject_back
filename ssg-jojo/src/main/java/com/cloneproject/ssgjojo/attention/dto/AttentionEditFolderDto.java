package com.cloneproject.ssgjojo.attention.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttentionEditFolderDto {
    private Long userId;
    private Long originFolderId;
    private List<Long> attentionIdList;
    private List<Long> folderIdList;
}
