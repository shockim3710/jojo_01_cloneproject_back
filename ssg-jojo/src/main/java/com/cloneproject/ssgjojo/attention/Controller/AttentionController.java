package com.cloneproject.ssgjojo.attention.Controller;

import com.cloneproject.ssgjojo.attention.dto.AttentionAddDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionEditFolderDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionInputFolderDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionOutputDto;
import com.cloneproject.ssgjojo.attention.service.IAttentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttentionController {
    private final IAttentionService iAttentionService;

    @PostMapping("/attention/add")
    public void attentionAdd(@RequestBody AttentionAddDto attentionAddDto){
        iAttentionService.AttentionAdd(attentionAddDto);
    }

    @PostMapping("/attention/add/folder")
    public void attentionAddFolder(@RequestBody AttentionInputFolderDto attentionInputFolderDto) {
        iAttentionService.AttentionAddFolder(attentionInputFolderDto);
    }

    @PutMapping("/attention/edit")
    public List<AttentionOutputDto> attentionEditFolder(@RequestBody AttentionEditFolderDto attentionEditFolderDto) {
        return iAttentionService.AttentionEditFolder(attentionEditFolderDto);
    }
}
