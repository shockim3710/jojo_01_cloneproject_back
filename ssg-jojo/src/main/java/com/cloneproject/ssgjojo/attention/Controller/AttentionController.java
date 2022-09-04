package com.cloneproject.ssgjojo.attention.Controller;

import com.amazonaws.Response;
import com.cloneproject.ssgjojo.attention.dto.*;
import com.cloneproject.ssgjojo.attention.service.IAttentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> attentionAdd(@RequestBody AttentionAddDto attentionAddDto, HttpServletRequest request){
        boolean result = iAttentionService.AttentionAdd(attentionAddDto, request);

        if(result)
            return ResponseEntity.status(200).body("추가 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 좋아요 항목 폴더에 추가
    @PostMapping("/attention/add/infolder")
    public ResponseEntity<?> attentionAddFolder(@RequestBody AttentionInputFolderDto attentionInputFolderDto, HttpServletRequest request) {
        boolean result = iAttentionService.AttentionAddFolder(attentionInputFolderDto, request);

        if(result)
            return ResponseEntity.status(200).body("추가 완료");
        else
            return ResponseEntity.status(400).body("error page");

    }

    // 좋아요 항목 폴더 변경
    @PutMapping("/attention/edit")
    public ResponseEntity<?> attentionEditFolder(@RequestBody AttentionEditFolderDto attentionEditFolderDto, HttpServletRequest request) {
        boolean result = iAttentionService.AttentionEditFolder(attentionEditFolderDto, request);

        if(result)
            return ResponseEntity.status(200).body("변경 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 특정 폴더에 있는 좋아요 항목 조회
    @GetMapping("/attention/get/{folderId}")
    public ResponseEntity<?> attentionFindAllByFolder(@PathVariable Long folderId) {
        List<AttentionOutputDto> result = iAttentionService.findAllByAttentionFolder(folderId);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 전체 폴더에서 삭제
    @DeleteMapping("/attention/delete")
    public ResponseEntity<?> attentionDeleteById(@RequestBody AttentionDeleteDto attentionDeleteDto, HttpServletRequest request) {
        boolean result = iAttentionService.deleteAttention(attentionDeleteDto, request);

        if(result)
            return ResponseEntity.status(200).body("삭제 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 특정 폴더에서 삭제
    @DeleteMapping("/attention/infolder/delete")
    public ResponseEntity<?> attentionDeleteInFolder(@RequestBody AttentionDeleteFolderDto attentionDeleteFolderDto, HttpServletRequest request) {
        boolean result = iAttentionService.deleteAttentionInFolder(attentionDeleteFolderDto, request);

        if(result)
            return ResponseEntity.status(200).body("삭제 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }
}
