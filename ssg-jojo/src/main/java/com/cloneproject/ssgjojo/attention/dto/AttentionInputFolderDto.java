package com.cloneproject.ssgjojo.attention.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttentionInputFolderDto {
    private Long userId;
    private List<Long> attentionIdList;
    private List<Long> folderIdList;
}