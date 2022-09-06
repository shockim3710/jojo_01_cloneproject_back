package com.cloneproject.ssgjojo.recentlyproduct.controller;

import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductAddDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;
import com.cloneproject.ssgjojo.recentlyproduct.service.IRecentlyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecentlyProductController {
    private final IRecentlyProductService iRecentlyProductService;

    // 유저별 최근 본 상품 조회
    @GetMapping("/recently/list")
    public ResponseEntity<?> findRecentlyByUser(HttpServletRequest request) {
        List<RecentlyProductOutputDto> result = iRecentlyProductService.findAllByUser(request);
        return ResponseEntity.status(200).body(result);
    }

    // 최근 본 상품 각각 삭제
    @DeleteMapping("/recently/delete")
    public ResponseEntity<?> deleteByRecentlyId(@RequestBody RecentlyProductDeleteDto deleteDtoList, HttpServletRequest request) {
        boolean result = iRecentlyProductService.deleteByRecentlyId(deleteDtoList, request);
        if(result)
            return ResponseEntity.status(200).body("삭제 성공");
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 최근 본 상품 전체 삭제
    @DeleteMapping("/recently/delete/all")
    public ResponseEntity<?> deleteAllByUserId(HttpServletRequest request) {
        boolean result = iRecentlyProductService.deleteAllByUserId(request);
        
        if(result)
            return ResponseEntity.status(200).body("삭제 성공");
        else
            return ResponseEntity.status(400).body("error page");
    }
}
