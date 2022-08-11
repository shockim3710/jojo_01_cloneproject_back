package com.cloneproject.ssgjojo.banner.contoller;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.banner.dto.BannerAddDto;
import com.cloneproject.ssgjojo.banner.service.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BannerController {
    private final IBannerService iBannerService;

    @PostMapping("/banner/add")
    public Banner addBanner(@RequestBody BannerAddDto bannerAddDto) {
        return iBannerService.addBanner(bannerAddDto);
    }

    @GetMapping("/banner/getall")
    public List<Banner> getAllBanner() {
        return iBannerService.getAllBanner();
    }

    @GetMapping("/banner/{id}")
    public Banner getBannerById(@PathVariable Long id) {
        return iBannerService.getBannerById(id);
    }

    @DeleteMapping("/banner/{id}")
    public void deleteBanner(@PathVariable Long id) {
        iBannerService.deleteBanner(id);
    }

    @PutMapping("/banner/edit")
    public Banner editBanner(@RequestBody Banner banner) {
        return iBannerService.editBanner(banner);
    }


}
