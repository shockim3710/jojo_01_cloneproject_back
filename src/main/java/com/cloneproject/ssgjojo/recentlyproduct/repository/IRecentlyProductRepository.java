package com.cloneproject.ssgjojo.recentlyproduct.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRecentlyProductRepository extends JpaRepository<RecentlyProduct, Long> {
    List<RecentlyProduct> findAllByUserOrderByViewTimeDesc(User user);
    void deleteAllByUser(User user);
    Optional<RecentlyProduct> findByUserAndProduct(User user, Product product);
}
