package com.cloneproject.ssgjojo.attentionfolder.service;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderAddDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderDeleteDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderEditDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderOutputDto;

import java.util.List;

public interface IAttentionFolderService {
    AttentionFolderOutputDto addFolder(AttentionFolderAddDto addDto);
    AttentionFolderOutputDto editFolder(AttentionFolderEditDto editDto);
    void deleteFolderById(AttentionFolderDeleteDto deleteDto);
    List<AttentionFolderOutputDto> findAllByUser(Long userId);
}
