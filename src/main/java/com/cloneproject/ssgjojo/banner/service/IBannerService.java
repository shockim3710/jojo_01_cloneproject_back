package com.cloneproject.ssgjojo.banner.service;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.banner.dto.BannerAddDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBannerService {
    Banner addBanner(MultipartFile bannerPhoto, BannerAddDto bannerAddDto);
    List<Banner> getAllBanner();
    boolean deleteBanner(Long id);
    Banner editBanner(Banner banner);
}
