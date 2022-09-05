package com.cloneproject.ssgjojo.attentionfolder.controller;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderAddDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderDeleteDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderEditDto;
import com.cloneproject.ssgjojo.attentionfolder.dto.AttentionFolderOutputDto;
import com.cloneproject.ssgjojo.attentionfolder.service.IAttentionFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttentionFolderController {
    private final IAttentionFolderService iAttentionFolderService;

    // 좋아요 폴더 추가
    @PostMapping("/attentionfolder/add")
    public ResponseEntity<?> addFolder(@RequestBody AttentionFolderAddDto addDto, HttpServletRequest request) {
        AttentionFolderOutputDto result = iAttentionFolderService.addFolder(addDto, request);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 유저별 좋아요 폴더 조회
    @GetMapping("/attentionfolder/get")
    public ResponseEntity<?> getFolderByUser(HttpServletRequest request) {
        List<AttentionFolderOutputDto> result = iAttentionFolderService.findAllByUser(request);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 좋아요 폴더 수정
    @PutMapping("/attentionfolder/edit")
    public ResponseEntity<?> editFolder(@RequestBody AttentionFolderEditDto editDto, HttpServletRequest request) {
        AttentionFolderOutputDto result = iAttentionFolderService.editFolder(editDto, request);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 좋아요 폴더 삭제
    @DeleteMapping("/attentionfolder/delete")
    public ResponseEntity<?> deleteFolder(@RequestBody AttentionFolderDeleteDto deleteDto, HttpServletRequest request) {
        boolean result = iAttentionFolderService.deleteFolderById(deleteDto, request);

        if(result)
            return ResponseEntity.status(200).body("삭제 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }
}
