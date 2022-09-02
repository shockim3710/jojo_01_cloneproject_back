package com.cloneproject.ssgjojo.recentlyproduct.controller;

import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductAddDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;
import com.cloneproject.ssgjojo.recentlyproduct.service.IRecentlyProductService;
import lombok.RequiredArgsConstructor;
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
    public List<RecentlyProductOutputDto> findRecentlyByUser(HttpServletRequest request) {
        return iRecentlyProductService.findAllByUser(request);
    }

    // 최근 본 상품 각각 삭제
    @DeleteMapping("/recently/delete")
    public String deleteByRecentlyId(@RequestBody List<RecentlyProductDeleteDto> deleteDtoList, HttpServletRequest request) {
        return iRecentlyProductService.deleteByRecentlyId(deleteDtoList, request);
    }

    // 최근 본 상품 전체 삭제
    @DeleteMapping("/recently/delete/all")
    public String deleteAllByUserId(HttpServletRequest request) {
        return iRecentlyProductService.deleteAllByUserId(request);
    }

}
