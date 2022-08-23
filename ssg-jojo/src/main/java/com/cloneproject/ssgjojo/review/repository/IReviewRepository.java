package com.cloneproject.ssgjojo.review.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAllByProduct(Product product);
    @Query(value = "select count(rev) from Review rev where rev.product.id =:id")
    Integer getReviewCountByProduct(@Param("id") Long id);

    public List<Review> findTop5ByProduct(Product product);

    public List<Review> findAllByProductOrderByScoreAsc(Product product);
    public List<Review> findAllByProductOrderByScoreDesc(Product product);
    public List<Review> findAllByProductOrderByCreatedDateDesc(Product product);

    @Query(value = "select avg(rev.score) from Review rev where rev.product.id =:id")
    Float getReviewAvgScore(@Param("id") Long id);

    List<Review> findAllByUser(User user);
}
