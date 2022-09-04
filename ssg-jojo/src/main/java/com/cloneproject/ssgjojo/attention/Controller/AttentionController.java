package com.cloneproject.ssgjojo.attention.Controller;

import com.cloneproject.ssgjojo.attention.dto.*;
import com.cloneproject.ssgjojo.attention.service.IAttentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttentionController {
    private final IAttentionService iAttentionService;

    // 좋아요 항목 추가
    @PostMapping("/attention/add")
    public void attentionAdd(@RequestBody AttentionAddDto attentionAddDto, HttpServletRequest request){
        iAttentionService.AttentionAdd(attentionAddDto, request);
    }

    // 좋아요 항목 폴더에 추가
    @PostMapping("/attention/add/infolder")
    public void attentionAddFolder(@RequestBody AttentionInputFolderDto attentionInputFolderDto, HttpServletRequest request) {
        iAttentionService.AttentionAddFolder(attentionInputFolderDto, request);
    }

    // 좋아요 항목 폴더 변경
    @PutMapping("/attention/edit")
    public List<AttentionOutputDto> attentionEditFolder(@RequestBody AttentionEditFolderDto attentionEditFolderDto, HttpServletRequest request) {
        return iAttentionService.AttentionEditFolder(attentionEditFolderDto, request);
    }

    // 특정 폴더에 있는 좋아요 항목 조회
    @GetMapping("/attention/get/{folderId}")
    public List<AttentionOutputDto> attentionFindAllByFolder(@PathVariable Long folderId) {
        return iAttentionService.findAllByAttentionFolder(folderId);
    }

    // 전체 폴더에서 삭제
    @DeleteMapping("/attention/delete")
    public boolean attentionDeleteById(@RequestBody AttentionDeleteDto attentionDeleteDto, HttpServletRequest request) {
        return iAttentionService.deleteAttention(attentionDeleteDto, request);
    }

    // 특정 폴더에서 삭제
    @DeleteMapping("/attention/infolder/delete")
    public boolean attentionDeleteInFolder(@RequestBody AttentionDeleteFolderDto attentionDeleteFolderDto, HttpServletRequest request) {
        return iAttentionService.deleteAttentionInFolder(attentionDeleteFolderDto, request);
    }
}
