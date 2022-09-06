package com.cloneproject.ssgjojo.recentlyproduct.service;

import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductAddDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IRecentlyProductService {
    List<RecentlyProductOutputDto> findAllByUser(HttpServletRequest request);
    boolean deleteByRecentlyId(RecentlyProductDeleteDto recentlyProductDeleteDto, HttpServletRequest request);
    boolean deleteAllByUserId(HttpServletRequest request);
}
