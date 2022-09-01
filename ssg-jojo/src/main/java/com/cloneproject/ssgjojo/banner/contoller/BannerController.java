package com.cloneproject.ssgjojo.banner.contoller;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.banner.dto.BannerAddDto;
import com.cloneproject.ssgjojo.banner.service.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BannerController {
    private final IBannerService iBannerService;

    // 배너 추가
    @PostMapping(value = "/banner/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Banner addBanner(@RequestParam("bannerPhoto") MultipartFile bannerPhoto,
                            @RequestPart BannerAddDto bannerAddDto) {
        return iBannerService.addBanner(bannerPhoto, bannerAddDto);
    }

    // 배너 조회
    @GetMapping("/banner/getAll")
    public List<Banner> getAllBanner() {
        return iBannerService.getAllBanner();
    }

    // 배너 편집
    @PutMapping("/banner/edit")
    public Banner editBanner(@RequestBody Banner banner) {
        return iBannerService.editBanner(banner);
    }

    // 배너 삭제
    @DeleteMapping("/banner/{id}")
    public void deleteBanner(@PathVariable Long id) {
        iBannerService.deleteBanner(id);
    }
}
