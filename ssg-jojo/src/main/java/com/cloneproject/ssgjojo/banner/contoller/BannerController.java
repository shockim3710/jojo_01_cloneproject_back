package com.cloneproject.ssgjojo.banner.contoller;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.banner.dto.BannerAddDto;
import com.cloneproject.ssgjojo.banner.service.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addBanner(@RequestParam("bannerPhoto") MultipartFile bannerPhoto,
                                    @RequestPart BannerAddDto bannerAddDto) {
        return ResponseEntity.status(200).body(iBannerService.addBanner(bannerPhoto, bannerAddDto));
    }

    // 배너 조회
    @GetMapping("/banner/getAll")
    public ResponseEntity<?> getAllBanner() {
        return ResponseEntity.status(200).body(iBannerService.getAllBanner());
    }

    // 배너 편집
    @PutMapping("/banner/edit")
    public ResponseEntity<?> editBanner(@RequestBody Banner banner) {
        Banner result = iBannerService.editBanner(banner);

        if(result != null)
            return ResponseEntity.status(200).body(result);
        else
            return ResponseEntity.status(400).body("error page");
    }

    // 배너 삭제
    @DeleteMapping("/banner/{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable Long id) {
        boolean result = iBannerService.deleteBanner(id);

        if(result)
            return ResponseEntity.status(200).body("삭제 완료");
        else
            return ResponseEntity.status(400).body("error page");
    }
}
