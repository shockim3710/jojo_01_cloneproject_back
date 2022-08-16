package com.cloneproject.ssgjojo.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerAddDto {
    private String bannerName;
    private String bannerContents;
    private String bannerUri;
}
