package com.cloneproject.ssgjojo.categoryproductlist.repository;

import com.cloneproject.ssgjojo.categoryproductlist.domain.CategoryProductList;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
    void deleteByProduct(Product product);
    Optional<CategoryProductList> findByProduct(Product product);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " false " +
            " ) " +
            " from CategoryProductList ct " +
            " where ct.categoryLv1.id =:categoryLv1" +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv1id(@Param("categoryLv1") Long categoryLv1, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " case when a.id is not null then true else false end " +
            " ) " +
            " from CategoryProductList ct " +
            " left join Attention a " +
            " on ct.product.id = a.product.id " +
            " and a.user.id = :userId " +
            " where ct.categoryLv1.id =:categoryLv1 " +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv1idWithUser(@Param("categoryLv1") Long categoryLv1, @Param("userId") Long userId, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " false " +
            " ) " +
            " from CategoryProductList ct " +
            " where ct.categoryLv2.id =:categoryLv2" +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv2id(@Param("categoryLv2") Long categoryLv2, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " case when a.id is not null then true else false end " +
            " ) " +
            " from CategoryProductList ct " +
            " left join Attention a " +
            " on ct.product.id = a.product.id " +
            " and a.user.id = :userId " +
            " where ct.categoryLv2.id =:categoryLv2 " +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv2idWithUser(@Param("categoryLv2") Long categoryLv2, @Param("userId") Long userId, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " false " +
            " ) " +
            " from CategoryProductList ct " +
            " where ct.categoryLv3.id =:categoryLv3" +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv3id(@Param("categoryLv3") Long categoryLv3, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " case when a.id is not null then true else false end " +
            " ) " +
            " from CategoryProductList ct " +
            " left join Attention a " +
            " on ct.product.id = a.product.id " +
            " and a.user.id = :userId " +
            " where ct.categoryLv3.id =:categoryLv3 " +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv3idWithUser(@Param("categoryLv3") Long categoryLv3, @Param("userId") Long userId, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " false " +
            " ) " +
            " from CategoryProductList ct " +
            " where ct.categoryLv4.id =:categoryLv4" +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv4id(@Param("categoryLv4") Long categoryLv4, Pageable pageable);

    @Query("select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " ct.product.id, ct.product.thumbnail, '신세계몰', ct.product.productName," +
            " ct.product.manufactureCompany, ct.product.discountRate, " +
            " ct.product.price, ct.product.price, " +
            " ct.product.fee, ct.product.adultCase," +
            " (select count(rev) from Review rev where rev.product.id = ct.product.id), " +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = ct.product.id), 0), " +
            " case when a.id is not null then true else false end " +
            " ) " +
            " from CategoryProductList ct " +
            " left join Attention a " +
            " on ct.product.id = a.product.id " +
            " and a.user.id = :userId " +
            " where ct.categoryLv4.id =:categoryLv4 " +
            " order by ct.product.id asc ")
    List<ProductListAttentionDto> findByCategoryLv4idWithUser(@Param("categoryLv4") Long categoryLv4, @Param("userId") Long userId, Pageable pageable);
}
