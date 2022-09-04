package com.cloneproject.ssgjojo.product.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "select search(ps) from Product ps where ps.product.name=:id ")
//    List<ProductListDto> productSearch(Product product);

    List<Product> findByProductNameContaining(String name);

    @Query(value = "select pr  from Product pr")
    List<Product> getProduct(Pageable pr);

    @Query(value = "select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " p.id, p.thumbnail, '신세계몰', p.productName, " +
            " p.manufactureCompany, p.discountRate, " +
            " p.price, " +
            " p.price, " +
            " p.fee, p.adultCase, " +
            " (select count(rev) from Review rev where rev.product.id = p.id)," +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = p.id), 0), " +
            " false " +
            " )" +
            " from Product p " +
            " left join Attention a " +
            " on p.id = a.product.id ")
    List<ProductListAttentionDto> getProductList(Pageable pr);

    @Query(value = "select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " p.id, p.thumbnail, '신세계몰', p.productName, " +
            " p.manufactureCompany, p.discountRate, " +
            " p.price, " +
            " p.price, " +
            " p.fee, p.adultCase, " +
            " (select count(rev) from Review rev where rev.product.id = p.id)," +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = p.id), 0), " +
            " case when a.id is not null then true else false end " +
            " )" +
            " from Product p " +
            " left join Attention a " +
            " on p.id = a.product.id " +
            " and a.user.id = :id ")
    List<ProductListAttentionDto> getProductListWithUser(Pageable pr, @Param("id") Long id);

    @Query(value = "select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " p.id, p.thumbnail, '신세계몰', p.productName, " +
            " p.manufactureCompany, p.discountRate, " +
            " p.price, " +
            " p.price, " +
            " p.fee, p.adultCase, " +
            " (select count(rev) from Review rev where rev.product.id = p.id)," +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = p.id), 0), " +
            " false " +
            " )" +
            " from Product p " +
            " where p.productName like :keyword ")
    List<ProductListAttentionDto> getProductListWithKeyword(Pageable pr, @Param("keyword") String keyword);

    @Query(value = "select new com.cloneproject.ssgjojo.product.dto.ProductListAttentionDto( " +
            " p.id, p.thumbnail, '신세계몰', p.productName, " +
            " p.manufactureCompany, p.discountRate, " +
            " p.price, " +
            " p.price, " +
            " p.fee, p.adultCase, " +
            " (select count(rev) from Review rev where rev.product.id = p.id)," +
            " coalesce((select avg(rev2.score) from Review rev2 where rev2.product.id = p.id), 0), " +
            " case when a.id is not null then true else false end " +
            " )" +
            " from Product p" +
            " left join Attention a " +
            " on p.id = a.product.id " +
            " and a.user.id = :id " +
            " where p.productName like :keyword ")
    List<ProductListAttentionDto> getProductListWithKeywordAndUser(Pageable pr, @Param("id") Long id, @Param("keyword") String keyword);
}
