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

    @PostMapping(value = "/banner/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Banner addBanner(@RequestParam("bannerPhoto") MultipartFile bannerPhoto,
                            @RequestPart BannerAddDto bannerAddDto) {
        return iBannerService.addBanner(bannerPhoto, bannerAddDto);
    }

    @GetMapping("/banner/getAll")
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
