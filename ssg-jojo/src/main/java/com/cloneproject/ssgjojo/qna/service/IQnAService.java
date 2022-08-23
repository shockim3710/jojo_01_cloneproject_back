package com.cloneproject.ssgjojo.qna.service;

import com.cloneproject.ssgjojo.qna.dto.*;

import java.util.List;

public interface IQnAService {

    QnAOutputDto addQ(QuestionInputDto questionInputDto);
    List<QnAOutputDto> getQnaByProuductId(Long id);
    QnAOutputDto editQ(QnAEditDto qnAEditDto);
    Integer getQuestionCountByProduct(Long productId);
    List<QnAOutputDto> getTop5(Long productId);
    List<QnAOutputDto> sortedGetQnaByProductId(Long id);
    void deleteQuestion(QnADeleteDto qnADeleteDto);

    QnAOutputDto addA(AnswerInputDto answerInputDto);

}
