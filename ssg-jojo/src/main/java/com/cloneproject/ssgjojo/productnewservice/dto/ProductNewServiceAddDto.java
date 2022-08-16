package com.cloneproject.ssgjojo.productnewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductNewServiceAddDto {
    private String newServiceName;
    private String newServiceContents;
    private String newServiceUri;
}
