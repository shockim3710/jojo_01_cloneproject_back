package com.cloneproject.ssgjojo.attention.service;

import com.cloneproject.ssgjojo.attention.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAttentionService {
    void AttentionAdd(AttentionAddDto addDto, HttpServletRequest request);
    void AttentionAddFolder(AttentionInputFolderDto addFolderDto);
    List<AttentionOutputDto> AttentionEditFolder(AttentionEditFolderDto attentionInputFolderDto);
    List<AttentionOutputDto> findAllByAttentionFolder(Long folderId);
    boolean deleteAttention(AttentionDeleteDto deleteDto);
    boolean deleteAttentionInFolder(AttentionDeleteFolderDto deleteDto);
}
