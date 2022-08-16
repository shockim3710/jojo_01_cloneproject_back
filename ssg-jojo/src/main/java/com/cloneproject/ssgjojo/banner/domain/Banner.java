package com.cloneproject.ssgjojo.banner.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
