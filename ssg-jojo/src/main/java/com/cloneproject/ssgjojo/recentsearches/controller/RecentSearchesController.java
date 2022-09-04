package com.cloneproject.ssgjojo.recentsearches.controller;

import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.recentsearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentsearches.service.IRecentSearchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecentSearchesController {

    private final IRecentSearchesService iRecentSearchesService;

    @PostMapping("/recentsearches/add") // 최근검색어 추가
    public ResponseEntity<?> addRecentSearches(@RequestBody RecentSearchesAddDto recentSearchesAddDto, HttpServletRequest request) {
        RecentSearches recentSearches = iRecentSearchesService.addRecentSearches(recentSearchesAddDto, request);

        if(recentSearches!=null){
            return ResponseEntity.status(200).body("최근검색어가 추가되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @GetMapping("/recentsearches/get") // 해당 사용자의 최근검색어 조회
    public List<String> getRecentSearches(HttpServletRequest request) {
        return iRecentSearchesService.getRecentSearchesByUserId(request);
    }

    @DeleteMapping("/recentsearches/delete/{id}") // 해당 사용자의 최근검색어 삭제
    public ResponseEntity<?> deleteRecentSearches(@PathVariable Long id, HttpServletRequest request) {
        Optional<RecentSearches> recentSearches = iRecentSearchesService.deleteRecentSearches(id, request);

        if(recentSearches.isPresent()){
            return ResponseEntity.status(200).body("최근검색어가 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @DeleteMapping("/recentsearches/delete/userId") // 해당 사용자의 최근검색어 전부삭제
    public void deleteAllByUser(HttpServletRequest request) {
        iRecentSearchesService.deleteAllByUser(request);
    }

}
