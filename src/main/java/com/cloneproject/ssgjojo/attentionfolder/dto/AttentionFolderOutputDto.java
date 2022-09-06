package com.cloneproject.ssgjojo.attentionfolder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttentionFolderOutputDto {
    private Long id;
    private int no;
    private String folderName;
    private Long userId;
}
