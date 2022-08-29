package com.cloneproject.ssgjojo.recentlyproduct.controller;

import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductAddDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;
import com.cloneproject.ssgjojo.recentlyproduct.service.IRecentlyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecentlyProductController {
    private final IRecentlyProductService iRecentlyProductService;

    @PostMapping("/recently/add")
    public void addRecentlyProduct(@RequestBody RecentlyProductAddDto recentlyProductAddDto) {
        iRecentlyProductService.addRecentlyProduct(recentlyProductAddDto);
    }

    @GetMapping("/recently/list/{id}")
    public List<RecentlyProductOutputDto> findRecentlyByUser(@PathVariable Long id) {
        return iRecentlyProductService.findAllByUser(id);
    }

    @DeleteMapping("/recently/delete")
    public void deleteByRecentlyId(@RequestBody List<RecentlyProductDeleteDto> deleteDtoList) {
        iRecentlyProductService.deleteByRecentlyId(deleteDtoList);
    }

    @DeleteMapping("/recently/delete/{id}")
    public void deleteAllByUserId(@PathVariable Long id) {
        iRecentlyProductService.deleteAllByUserId(id);
    }

}
