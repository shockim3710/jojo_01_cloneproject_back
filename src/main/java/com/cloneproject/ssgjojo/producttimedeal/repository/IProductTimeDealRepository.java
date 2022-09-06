package com.cloneproject.ssgjojo.producttimedeal.repository;

import com.cloneproject.ssgjojo.producttimedeal.domain.ProductTimeDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductTimeDealRepository extends JpaRepository<ProductTimeDeal, Long> {
    @Query("select p from ProductTimeDeal p " +
            "where p.timeDealStartDate <= current_timestamp " +
            "and p.timeDealEndDate >= current_timestamp " +
            "order by p.timeDealStartDate")
    public List<ProductTimeDeal> findProductTimeDealList();


//    public findTop1ByTimeDealStartDate
//    public ProductTimeDeal findTop1ByTimeDealStartDateLessThanEqualOrderByTimeDealStartDate();
}
