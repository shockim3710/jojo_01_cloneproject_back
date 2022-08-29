package com.cloneproject.ssgjojo.productnewservice.service;

import com.cloneproject.ssgjojo.productnewservice.domain.ProductNewService;
import com.cloneproject.ssgjojo.productnewservice.dto.ProductNewServiceAddDto;
import com.cloneproject.ssgjojo.productnewservice.repository.IProductNewServiceRepository;
import com.cloneproject.ssgjojo.util.MultipartUtil;
import com.cloneproject.ssgjojo.util.s3.AwsS3ResourceStorage;
import com.cloneproject.ssgjojo.util.s3.FileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductNewServiceService implements IProductNewServiceService{
    private final AwsS3ResourceStorage awsS3ResourceStorage;
    private final IProductNewServiceRepository iProductNewServiceRepository;

    // 뉴 서비스 등록
    @Override
    public ProductNewService addProductNewService(ProductNewServiceAddDto productNewServiceAddDto, MultipartFile newServicePhoto) {
        FileInfoDto newServicePhotoFile = FileInfoDto.multipartOf(newServicePhoto, "newService");
        awsS3ResourceStorage.store(newServicePhotoFile, newServicePhoto);

        return iProductNewServiceRepository.save(ProductNewService.builder()
                .newServiceName(productNewServiceAddDto.getNewServiceName())
                .newServiceContent(productNewServiceAddDto.getNewServiceContents())
                .productNewPhotoPath(MultipartUtil.createURL(newServicePhotoFile.getRemotePath()))
                .newServiceUri(productNewServiceAddDto.getNewServiceUri())
                .build());
    }

    // 뉴 서비스 조회
    @Override
    public List<ProductNewService> getAllProductNewService() {
        return iProductNewServiceRepository.findAll();
    }

}
