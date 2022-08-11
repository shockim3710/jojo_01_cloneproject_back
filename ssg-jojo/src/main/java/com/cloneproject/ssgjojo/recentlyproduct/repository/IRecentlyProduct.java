package com.cloneproject.ssgjojo.recentlyproduct.repository;

import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecentlyProduct extends JpaRepository<RecentlyProduct, Long> {
}
