package com.cloneproject.ssgjojo.qna.repository;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.AnswerInputDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IQnARepository extends JpaRepository<QnA, Long> {
    List<QnA> findAllByProduct(Product product);

//    AnswerInputDto answerQ(AnswerInputDto answerInputDto);
}
