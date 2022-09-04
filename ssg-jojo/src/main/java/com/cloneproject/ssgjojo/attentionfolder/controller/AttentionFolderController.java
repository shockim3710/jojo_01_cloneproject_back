package com.cloneproject.ssgjojo.attentionfolder.controller;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderAddDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderDeleteDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderEditDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderOutputDto;
import com.cloneproject.ssgjojo.attentionfolder.service.IAttentionFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttentionFolderController {
    private final IAttentionFolderService iAttentionFolderService;

    // 좋아요 폴더 추가
    @PostMapping("/attentionfolder/add")
    public AttentionFolderOutputDto addFolder(@RequestBody AttentionFolderAddDto addDto) {
        return iAttentionFolderService.addFolder(addDto);
    }

    // 유저별 좋아요 폴더 조회
    @GetMapping("/attentionfolder/get/{id}")
    public List<AttentionFolderOutputDto> getFolderByUser(@PathVariable Long id) {
        return iAttentionFolderService.findAllByUser(id);
    }

    // 좋아요 폴더 수정
    @PutMapping("/attentionfolder/edit")
    public AttentionFolderOutputDto editFolder(@RequestBody AttentionFolderEditDto editDto) {
        return iAttentionFolderService.editFolder(editDto);
    }

    // 좋아요 폴더 삭제
    @DeleteMapping("/attentionfolder/delete")
    public void deleteFolder(@RequestBody AttentionFolderDeleteDto deleteDto) {
        iAttentionFolderService.deleteFolderById(deleteDto);
    }
}
