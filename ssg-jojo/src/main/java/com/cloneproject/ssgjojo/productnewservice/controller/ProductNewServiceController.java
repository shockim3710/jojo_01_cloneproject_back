package com.cloneproject.ssgjojo.productnewservice.controller;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.productnewservice.domain.ProductNewService;
import com.cloneproject.ssgjojo.productnewservice.dto.ProductNewServiceAddDto;
import com.cloneproject.ssgjojo.productnewservice.service.IProductNewServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductNewServiceController {
    private final IProductNewServiceService iProductNewServiceService;

    @PostMapping("/newservice/add")
    public ProductNewService addNewService(@RequestParam("newServicePhoto")MultipartFile newServicePhoto,
                                           @RequestPart ProductNewServiceAddDto productNewServiceAddDto) {
        return iProductNewServiceService.addProductNewService(productNewServiceAddDto, newServicePhoto);
    }

    @GetMapping("/newservice/getAll")
    public List<ProductNewService> getAllBanner() {
        return iProductNewServiceService.getAllProductNewService();
    }
}
