package com.cloneproject.ssgjojo.qna.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.qna.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IQnARepository extends JpaRepository<QnA, Long> {

    @Query(value = "select count(qa) from QnA qa where qa.product.id=:id")
    Integer getQuestionCountByProduct(@Param("id") Long id);

    List<QnA> findAllByProduct(Product product);
    List<QnA> findTop5ByProduct(Product product);
    List<QnA> findAllByProductOrderByIdDesc(Product product);
}
