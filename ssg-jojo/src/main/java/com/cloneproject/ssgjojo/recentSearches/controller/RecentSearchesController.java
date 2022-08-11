package com.cloneproject.ssgjojo.recentSearches.controller;

import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesAddDto;
import com.cloneproject.ssgjojo.recentSearches.dto.RecentSearchesDto;
import com.cloneproject.ssgjojo.recentSearches.service.IRecentSearchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecentSearchesController {

    private final IRecentSearchesService iRecentSearchesService;

    @PostMapping("/recentsearches/add")
    public RecentSearchesAddDto addRecentSearches(@RequestBody RecentSearchesAddDto recentSearchesAddDto) {
        return iRecentSearchesService.addRecentSearches(recentSearchesAddDto);
    }

    @GetMapping("/recentsearches/get/{id}")
    public List<RecentSearchesDto> getRecentSearches(@PathVariable Long id) {
        return iRecentSearchesService.getRecentSearchesByUserId(id);
    }

    @DeleteMapping("/recentsearches/delete/{id}")
    public void deleteRecentSearches(@PathVariable Long id) {
        iRecentSearchesService.deleteRecentSearches(id);
    }

}
