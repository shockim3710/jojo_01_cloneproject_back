package com.cloneproject.ssgjojo.qna.service;

import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface IQnAService {

    QnA addQ(QuestionInputDto questionInputDto, HttpServletRequest request);
    QnA editQ(QnAEditDto qnAEditDto, HttpServletRequest request);
    List<QnAOutputDto> sortedGetQnaByProductId(Long id);
    Optional<QnA> deleteQuestion(QnADeleteDto qnADeleteDto, HttpServletRequest request);
    QnAOutputDto addA(AnswerInputDto answerInputDto);

}
