package com.cloneproject.ssgjojo.review.repository;

import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAllByProduct(Product product);
    public List<Review> findAllByProduct(Product product, Pageable pageable);
    List<Review> findAllByUser(User user);
    List<Review> findAllByOrdersAndProduct(Orders orders, Product product);
    public List<Review> findTop5ByProduct(Product product);
    public List<Review> findAllByProductOrderByScoreAsc(Product product, Pageable pageable);
    public List<Review> findAllByProductOrderByScoreDesc(Product product, Pageable pageable);
    public List<Review> findAllByProductOrderByCreatedDateDesc(Product product, Pageable pageable);
    @Query(value = "select count(rev) from Review rev where rev.product.id =:id")
    Integer getReviewCountByProduct(@Param("id") Long id);
    @Query(value = "select avg(rev.score) from Review rev where rev.product.id =:id")
    Float getReviewAvgScore(@Param("id") Long id);

    List<Review> findAllByProductOrderByCreatedDateAsc(Product product, Pageable pageable);

    public Page<Review> findByProductOrderByCreatedDateAsc(Product product, Pageable pageable);
}
