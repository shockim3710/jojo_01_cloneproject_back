package com.cloneproject.ssgjojo.productnewservice.domain;

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
public class ProductNewService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String newServiceName;
    @Column(nullable = false)
    private String newServiceContent;
    @Column(nullable = false)
    private String productNewPhotoPath;
    @Column(nullable = false)
    private String newServiceUri;
}
