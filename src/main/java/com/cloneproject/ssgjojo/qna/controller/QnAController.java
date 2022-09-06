package com.cloneproject.ssgjojo.qna.controller;

import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.*;
import com.cloneproject.ssgjojo.qna.service.IQnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QnAController {
    private final IQnAService iQnAService;

    // 상품문의글 작성
    @PostMapping("/qna/qAdd")
    public ResponseEntity<?> addQ(@RequestBody QuestionInputDto questionInputDto, HttpServletRequest request) {
        QnA qnA = iQnAService.addQ(questionInputDto, request);

        if(qnA!=null){
            return ResponseEntity.status(200).body("문의가 등록되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 기존 상품문의글 편집
    @PutMapping("/qna/qEdit")
    public ResponseEntity<?> editQ(@RequestBody QnAEditDto qnAEditDto, HttpServletRequest request) {
        QnA qnA = iQnAService.editQ(qnAEditDto, request);

        if(qnA!=null){
            return ResponseEntity.status(200).body("문의가 수정되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 해당 상품에 대한 상품문의 목록 최신순으로 정렬, 조회
    @GetMapping("/qna/{id}")
    public ResponseEntity<?> getQnaByProuductId(@PathVariable Long id) {
        List<QnAOutputDto> qnA = iQnAService.sortedGetQnaByProductId(id);

        if(qnA!=null){
            return ResponseEntity.status(200).body(qnA);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 작성된 상품문의글 삭제
    @DeleteMapping("/qna/deleteQ")
    public ResponseEntity<?> deleteQuestion(@RequestBody QnADeleteDto qnADeleteDto, HttpServletRequest request) {
        Optional<QnA> qnA = iQnAService.deleteQuestion(qnADeleteDto, request);

        if(qnA.isPresent()){
            return ResponseEntity.status(200).body("문의가 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    // 관리자가 상품문의에 대한 답변 추가
    @PostMapping("/qna/aAdd")
    public ResponseEntity<?> addA(@RequestBody AnswerInputDto answerInputDto) {
        QnAOutputDto qnA = iQnAService.addA(answerInputDto);

        if(qnA!=null){
            return ResponseEntity.status(200).body("답변이 추가되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }
}
