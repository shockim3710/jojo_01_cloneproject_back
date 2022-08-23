package com.cloneproject.ssgjojo.qna.controller;

import com.cloneproject.ssgjojo.qna.domain.QnA;
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

    @DeleteMapping("/qna/deleteQ")
    public void deleteQuestion(@RequestBody QnADeleteDto qnADeleteDto) {
        iQnAService.deleteQuestion(qnADeleteDto);
    }

    @PostMapping("/qna/aAdd")
    public QnAOutputDto addA(@RequestBody AnswerInputDto answerInputDto) {
        return iQnAService.addA(answerInputDto);
    }
}
