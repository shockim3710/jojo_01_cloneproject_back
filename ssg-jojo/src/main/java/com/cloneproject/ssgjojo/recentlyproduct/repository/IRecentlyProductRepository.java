package com.cloneproject.ssgjojo.recentlyproduct.repository;

import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecentlyProductRepository extends JpaRepository<RecentlyProduct, Long> {
    List<RecentlyProduct> findAllByUser(User user);
    void deleteAllByUser(User user);
}
