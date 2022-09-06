package com.cloneproject.ssgjojo.banner.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * id: 배너 PK
 * bannerName: 배너 이름
 * bannerContents: 배너 내용
 * bannerPhotoPath: 배너 이미지 경로
 * bannerUri: 배너 클릭 시 리다이렉션 될 경로
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bannerName;
    @Column(nullable = false)
    private String bannerContents;
    @Column(nullable = false)
    private String bannerPhotoPath;
    @Column(nullable = false)
    private String bannerUri;
}
