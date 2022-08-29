package com.cloneproject.ssgjojo.recentsearches.controller;

import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesDto;
import com.cloneproject.ssgjojo.recentsearches.service.IRecentSearchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecentSearchesController {

    private final IRecentSearchesService iRecentSearchesService;

    @PostMapping("/recentsearches/add") // 최근검색어 추가
    public RecentSearchesAddDto addRecentSearches(@RequestBody RecentSearchesAddDto recentSearchesAddDto) {
        return iRecentSearchesService.addRecentSearches(recentSearchesAddDto);
    }

    @GetMapping("/recentsearches/get/{id}") // 해당 사용자의 최근검색어 조회
    public List<RecentSearchesDto> getRecentSearches(@PathVariable Long id) {
        return iRecentSearchesService.getRecentSearchesByUserId(id);
    }

    @DeleteMapping("/recentsearches/delete/{id}") // 해당 사용자의 최근검색어 삭제
    public void deleteRecentSearches(@PathVariable Long id) {
        iRecentSearchesService.deleteRecentSearches(id);
    }

}
