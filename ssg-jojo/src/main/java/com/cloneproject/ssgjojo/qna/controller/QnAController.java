package com.cloneproject.ssgjojo.qna.controller;

import com.cloneproject.ssgjojo.qna.dto.*;
import com.cloneproject.ssgjojo.qna.service.IQnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QnAController {
    private final IQnAService iQnAService;

    @PostMapping("/qna/qAdd")
    public QnAOutputDto addQ(@RequestBody QuestionInputDto questionInputDto) {
        return iQnAService.addQ(questionInputDto);
    }

    @PutMapping("/qna/qEdit")
    public QnAOutputDto editQ(@RequestBody QnAEditDto qnAEditDto) {
        return iQnAService.editQ(qnAEditDto);
    }

    @GetMapping("/qna/{id}")
    public List<QnAOutputDto> getQnaByProuductId(@PathVariable Long id) {
        return iQnAService.getQnaByProuductId(id);
    }

    @GetMapping("/qna/getqna5/{productId}")
    public List<QnAOutputDto> getQna5(@PathVariable Long productId) {
        return iQnAService.getTop5(productId);
    }

    @GetMapping("/qna/getAll/sort/{productId}")
    public List<QnAOutputDto> sortedGetQnaByProductId(@PathVariable Long productId) {
        return iQnAService.sortedGetQnaByProductId(productId);
    }

    @GetMapping("/qna/count/{productId}")
    public Integer getQuestionCountByProduct(@PathVariable Long productId) {
        return iQnAService.getQuestionCountByProduct(productId);
    }

    @DeleteMapping("/qna/deleteQ")
    public void deleteQuestion(@RequestBody QnADeleteDto qnADeleteDto) {
        iQnAService.deleteQuestion(qnADeleteDto);
    }

    @PostMapping("/qna/aAdd")
    public QnAOutputDto addA(@RequestBody AnswerInputDto answerInputDto) {
        return iQnAService.addA(answerInputDto);
    }
}
