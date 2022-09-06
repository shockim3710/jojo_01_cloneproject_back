package com.cloneproject.ssgjojo.productnewservice.controller;

import com.cloneproject.ssgjojo.banner.domain.Banner;
import com.cloneproject.ssgjojo.productnewservice.domain.ProductNewService;
import com.cloneproject.ssgjojo.productnewservice.dto.ProductNewServiceAddDto;
import com.cloneproject.ssgjojo.productnewservice.service.IProductNewServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductNewServiceController {
    private final IProductNewServiceService iProductNewServiceService;

    // 뉴 서비스 등록
    @PostMapping("/newservice/add")
    public ResponseEntity<?> addNewService(@RequestParam("newServicePhoto")MultipartFile newServicePhoto,
                                        @RequestPart ProductNewServiceAddDto productNewServiceAddDto) {
        return ResponseEntity.status(200).body(iProductNewServiceService.addProductNewService(productNewServiceAddDto, newServicePhoto));
    }

    // 뉴 서비스 조회
    @GetMapping("/newservice/getAll")
    public ResponseEntity<?> getAllNewService() {
        return ResponseEntity.status(200).body(iProductNewServiceService.getAllProductNewService());
    }
}
