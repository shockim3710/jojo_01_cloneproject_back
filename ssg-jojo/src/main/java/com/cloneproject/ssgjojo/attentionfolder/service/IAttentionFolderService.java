package com.cloneproject.ssgjojo.attentionfolder.service;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderAddDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderDeleteDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderEditDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderOutputDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAttentionFolderService {
    AttentionFolderOutputDto addFolder(AttentionFolderAddDto addDto, HttpServletRequest request);
    List<AttentionFolderOutputDto> findAllByUser(HttpServletRequest request);
    AttentionFolderOutputDto editFolder(AttentionFolderEditDto editDto, HttpServletRequest request);
    boolean deleteFolderById(AttentionFolderDeleteDto deleteDto, HttpServletRequest request);
}
