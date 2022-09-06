package com.cloneproject.ssgjojo.banner.repository;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBannerRepository extends JpaRepository<Banner, Long> {
}
