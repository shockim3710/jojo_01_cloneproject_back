package com.cloneproject.ssgjojo.productnewservice.repository;

import com.cloneproject.ssgjojo.productnewservice.domain.ProductNewService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductNewServiceRepository extends JpaRepository<ProductNewService, Long> {
}
