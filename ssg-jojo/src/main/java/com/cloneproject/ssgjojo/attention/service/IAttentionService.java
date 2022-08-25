package com.cloneproject.ssgjojo.attention.service;

import com.cloneproject.ssgjojo.attention.dto.AttentionAddDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionEditFolderDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionInputFolderDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionOutputDto;

import java.util.List;

public interface IAttentionService {
    void AttentionAdd(AttentionAddDto addDto);
    void AttentionAddFolder(AttentionInputFolderDto addFolderDto);
    List<AttentionOutputDto> AttentionEditFolder(AttentionEditFolderDto attentionInputFolderDto);

    List<AttentionOutputDto> findAllByAttentionFolder(Long folderId);
}
