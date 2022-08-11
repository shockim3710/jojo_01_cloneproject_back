package com.cloneproject.ssgjojo.banner.service;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.banner.dto.BannerAddDto;
import com.cloneproject.ssgjojo.banner.repository.IBannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImple implements IBannerService{
    private final IBannerRepository iBannerRepository;

    @Override
    public Banner addBanner(BannerAddDto bannerAddDto) {
        return iBannerRepository.save(Banner.builder()
                .bannerName(bannerAddDto.getBannerName())
                .bannerPhotoPath(bannerAddDto.getBannerPhotoPath())
                .build()
        );
    }

    @Override
    public Banner getBannerById(Long id) {
        Optional<Banner> banner = iBannerRepository.findById(id);

        if(banner.isPresent()) {
            return banner.get();
        }
        return null;
    }
    @Override
    public List<Banner> getAllBanner() {
        return iBannerRepository.findAll();
    }

    @Override
    public void deleteBanner(Long id) {
        Optional<Banner> banner = iBannerRepository.findById(id);
        if(banner.isPresent())
            iBannerRepository.deleteById(id);
    }

    @Override
    public Banner editBanner(Banner banner) {
        Optional<Banner> updateBanner = iBannerRepository.findById(banner.getId());

        if(updateBanner.isPresent()) {
            return iBannerRepository.save(Banner.builder()
                    .id(banner.getId())
                    .bannerName(banner.getBannerName())
                    .bannerPhotoPath(banner.getBannerPhotoPath())
                    .build()
            );
        }
        return null;
    }
}
