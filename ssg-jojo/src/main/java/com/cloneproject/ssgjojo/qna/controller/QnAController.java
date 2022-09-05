package com.cloneproject.ssgjojo.qna.controller;

import com.cloneproject.ssgjojo.qna.dto.*;
import com.cloneproject.ssgjojo.qna.service.IQnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QnAController {
    private final IQnAService iQnAService;

    // 상품문의글 작성
    @PostMapping("/qna/qAdd")
    public QnAOutputDto addQ(@RequestBody QuestionInputDto questionInputDto, HttpServletRequest request) {
        return iQnAService.addQ(questionInputDto, request);
    }

    // 기존 상품문의글 편집
    @PutMapping("/qna/qEdit")
    public QnAOutputDto editQ(@RequestBody QnAEditDto qnAEditDto, HttpServletRequest request) {
        return iQnAService.editQ(qnAEditDto, request);
    }

    // 해당 상품에 대한 상품문의 목록 최신순으로 정렬, 조회
    @GetMapping("/qna/{id}")
    public List<QnAOutputDto> getQnaByProuductId(@PathVariable Long id) {
        return iQnAService.sortedGetQnaByProductId(id);
    }

    // 작성된 상품문의글 삭제
    @DeleteMapping("/qna/deleteQ")
    public void deleteQuestion(@RequestBody QnADeleteDto qnADeleteDto, HttpServletRequest request) {
        iQnAService.deleteQuestion(qnADeleteDto, request);
    }

    // 관리자가 상품문의에 대한 답변 추가
    @PostMapping("/qna/aAdd")
    public QnAOutputDto addA(@RequestBody AnswerInputDto answerInputDto) {
        return iQnAService.addA(answerInputDto);
    }
}
