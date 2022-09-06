package com.cloneproject.ssgjojo.attentionfolder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttentionFolderAddDto {
    private String folderName;
}
