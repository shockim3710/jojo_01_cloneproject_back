package com.cloneproject.ssgjojo.qna.service;

import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.*;

import java.util.List;

public interface IQnAService {

    QnAOutputDto addQ(QuestionInputDto questionInputDto);
    List<QnAOutputDto> getQnaByProuductId(Long id);
    QnAOutputDto editQ(QnAEditDto qnAEditDto);
    Integer getQuestionCountByProduct(Long qnAId);
    void deleteQuestion(QnADeleteDto qnADeleteDto);

    QnAOutputDto addA(AnswerInputDto answerInputDto);

}
