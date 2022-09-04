package com.cloneproject.ssgjojo.attention.service;

import com.cloneproject.ssgjojo.attention.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAttentionService {
    void AttentionAdd(AttentionAddDto addDto, HttpServletRequest request);
    void AttentionAddFolder(AttentionInputFolderDto addFolderDto, HttpServletRequest request);
    List<AttentionOutputDto> AttentionEditFolder(AttentionEditFolderDto attentionInputFolderDto, HttpServletRequest request);
    List<AttentionOutputDto> findAllByAttentionFolder(Long folderId);
    boolean deleteAttention(AttentionDeleteDto deleteDto, HttpServletRequest request);
    boolean deleteAttentionInFolder(AttentionDeleteFolderDto deleteDto, HttpServletRequest request);
}
