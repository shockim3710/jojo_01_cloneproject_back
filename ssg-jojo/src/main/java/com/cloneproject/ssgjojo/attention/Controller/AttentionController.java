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

    @PostMapping("/attention/add")
    public void attentionAdd(@RequestBody AttentionAddDto attentionAddDto, HttpServletRequest request){
        iAttentionService.AttentionAdd(attentionAddDto, request);
    }

    @PostMapping("/attention/add/infolder")
    public void attentionAddFolder(@RequestBody AttentionInputFolderDto attentionInputFolderDto) {
        iAttentionService.AttentionAddFolder(attentionInputFolderDto);
    }

    @GetMapping("/attention/get/{folderId}")
    public List<AttentionOutputDto> attentionFindAllByFolder(@PathVariable Long folderId) {
        return iAttentionService.findAllByAttentionFolder(folderId);
    }

    @PutMapping("/attention/edit")
    public List<AttentionOutputDto> attentionEditFolder(@RequestBody AttentionEditFolderDto attentionEditFolderDto) {
        return iAttentionService.AttentionEditFolder(attentionEditFolderDto);
    }

    @DeleteMapping("/attention/delete")
    public boolean attentionDeleteById(@RequestBody AttentionDeleteDto attentionDeleteDto) {
        return iAttentionService.deleteAttention(attentionDeleteDto);
    }

    @DeleteMapping("/attention/infolder/delete")
    public boolean attentionDeleteInFolder(@RequestBody AttentionDeleteFolderDto attentionDeleteFolderDto) {
        return iAttentionService.deleteAttentionInFolder(attentionDeleteFolderDto);
    }
}
