package com.cloneproject.ssgjojo.recentlyproduct.service;

import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductAddDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;

import java.util.List;

public interface IRecentlyProductService {
    void addRecentlyProduct(RecentlyProductAddDto recentlyProductAddDto);
    List<RecentlyProductOutputDto> findAllByUser(Long userId);
    void deleteByRecentlyId(List<RecentlyProductDeleteDto> recentlyProductDeleteDto);
    void deleteAllByUserId(Long userId);
}
