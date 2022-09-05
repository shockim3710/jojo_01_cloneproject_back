package com.cloneproject.ssgjojo.banner.service;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.banner.dto.BannerAddDto;
import com.cloneproject.ssgjojo.banner.repository.IBannerRepository;
import com.cloneproject.ssgjojo.util.MultipartUtil;
import com.cloneproject.ssgjojo.util.s3.AwsS3ResourceStorage;
import com.cloneproject.ssgjojo.util.s3.FileInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImple implements IBannerService{
    private final IBannerRepository iBannerRepository;
    private final AwsS3ResourceStorage awsS3ResourceStorage;

    // 배너 추가
    @Override
    public Banner addBanner(MultipartFile bannerPhoto, BannerAddDto bannerAddDto) {
        FileInfoDto bannerDto = FileInfoDto.multipartOf(bannerPhoto, "banner");
        awsS3ResourceStorage.store(bannerDto, bannerPhoto);

        return iBannerRepository.save(Banner.builder()
                .bannerName(bannerAddDto.getBannerName())
                .bannerContents(bannerAddDto.getBannerContents())
                .bannerPhotoPath(MultipartUtil.createURL(bannerDto.getRemotePath()))
                .bannerUri(bannerAddDto.getBannerUri())
                .build()
        );
    }

    // 배너 조회
    @Override
    public List<Banner> getAllBanner() {
        return iBannerRepository.findAll();
    }

    // 배너 편집
    @Override
    public Banner editBanner(Banner banner) {
        Optional<Banner> updateBanner = iBannerRepository.findById(banner.getId());

        if(updateBanner.isPresent()) {
            return iBannerRepository.save(Banner.builder()
                    .id(banner.getId())
                    .bannerName(banner.getBannerName())
                    .bannerContents(banner.getBannerContents())
                    .bannerPhotoPath(banner.getBannerPhotoPath())
                    .bannerUri(banner.getBannerUri())
                    .build()
            );
        }
        return null;
    }

    // 배너 삭제
    @Override
    public boolean deleteBanner(Long id) {
        Optional<Banner> banner = iBannerRepository.findById(id);
        if(banner.isPresent()) {
            iBannerRepository.deleteById(id);

            return true;
        }

        return false;
    }

}
