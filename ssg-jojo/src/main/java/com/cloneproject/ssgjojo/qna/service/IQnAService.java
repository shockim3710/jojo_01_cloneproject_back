package com.cloneproject.ssgjojo.qna.service;

import com.cloneproject.ssgjojo.qna.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IQnAService {

    QnAOutputDto addQ(QuestionInputDto questionInputDto, HttpServletRequest request);
    QnAOutputDto editQ(QnAEditDto qnAEditDto, HttpServletRequest request);
    List<QnAOutputDto> sortedGetQnaByProductId(Long id);
    void deleteQuestion(QnADeleteDto qnADeleteDto, HttpServletRequest request);
    QnAOutputDto addA(AnswerInputDto answerInputDto);

}
