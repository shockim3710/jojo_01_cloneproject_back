package com.cloneproject.ssgjojo.qna.service;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.*;
import com.cloneproject.ssgjojo.qna.repository.IQnARepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class QnAServiceImple implements IQnAService {

    private final IQnARepository iQnARepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final JwtTokenProvider jwtTokenProvider;



    // 상품문의글 작성
    @Override
    public QnAOutputDto addQ(QuestionInputDto questionInputDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);
        Optional<Product> product = iProductRepository.findById(questionInputDto.getProductId());

        if(product.isPresent()) {
            QnA qna = iQnARepository.save(QnA.builder()
                    .title(questionInputDto.getTitle())
                    .questionMain(questionInputDto.getQuestionMain())
                    .questionDate(new Timestamp(System.currentTimeMillis()))
                    .lockCase(questionInputDto.isLockCase())
                    .user(user.get())
                    .product(product.get())
                    .build());

            return QnAOutputDto.builder()
                    .id(qna.getId())
                    .title(qna.getTitle())
                    .questionMain(qna.getQuestionMain())
                    .questionDate(qna.getQuestionDate())
                    .userAccount(qna.getUser().getUserId().substring(0,3)+"******")
                    .lockCase(qna.isLockCase())
                    .productId(qna.getProduct().getId())
                    .build();
        }

        return null;
    }


    // 기존 상품문의글 편집
    @Override
    public QnAOutputDto editQ(QnAEditDto qnAEditDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);
        Optional<Product> product = iProductRepository.findById(qnAEditDto.getProductId());
        Optional<QnA> qnA = iQnARepository.findById(qnAEditDto.getId());

        if (qnA.isPresent() && user.isPresent() && product.isPresent()) {
            if(qnA.get().getUser().getId() == userId) {
                if(qnA.get().getAnswerMain() == null) {
                    QnA qnaEdit = iQnARepository.save(QnA.builder()
                            .id(qnAEditDto.getId())
                            .title(qnAEditDto.getTitle())
                            .questionMain(qnAEditDto.getQuestionMain())
                            .questionDate(new Timestamp(System.currentTimeMillis()))
                            .lockCase(qnAEditDto.isLockCase())
                            .user(user.get())
                            .product(product.get())
                            .build());

                    return QnAOutputDto.builder()
                            .id(qnA.get().getId())
                            .title(qnaEdit.getTitle())
                            .questionMain(qnaEdit.getQuestionMain())
                            .questionDate(qnaEdit.getQuestionDate())
                            .userAccount(qnaEdit.getUser().getUserId().substring(0,3)+"******")
                            .lockCase(qnaEdit.isLockCase())
                            .productId(qnaEdit.getProduct().getId())
                            .build();
                }
            }
        }


        return null;
    }


    // 해당 상품에 대한 상품문의 목록 최신순으로 정렬, 조회
    @Override
    public List<QnAOutputDto> sortedGetQnaByProductId(Long id) {

        Optional<Product> product = iProductRepository.findById(id);
        List<QnAOutputDto> qnAOutputDtoList = new ArrayList<>();
        List<QnA> qnAList = new ArrayList<>();

        if(product.isPresent()) {
            qnAList = iQnARepository.findAllByProductOrderByIdDesc(product.get());

        for(QnA qnA : qnAList) {
            qnAOutputDtoList.add(QnAOutputDto.builder()
                    .id(qnA.getId())
                    .userAccount(qnA.getUser().getName())
                    .title(qnA.getTitle())
                    .questionMain(qnA.getQuestionMain())
                    .questionDate(qnA.getQuestionDate())
                    .answerMain(qnA.getAnswerMain())
                    .answerDate(qnA.getAnswerDate())
                    .lockCase(qnA.isLockCase())
                    .build());
            }

        return qnAOutputDtoList;
        }

        return null;
    }


    // 작성된 상품문의글 삭제
    @Override
    public void deleteQuestion(QnADeleteDto qnADeleteDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);
        Optional<QnA> qnA = iQnARepository.findById(qnADeleteDto.getId());

        if (user.isPresent() && qnA.isPresent()) {
            if(qnA.get().getUser().getId() == userId) {
                iQnARepository.deleteById(qnADeleteDto.getId());
            }
        }

    }


    // 관리자가 상품문의에 대한 답변 추가
    @Override
    @Transactional
    public QnAOutputDto addA(AnswerInputDto answerInputDto) {
        Optional<Product> product = iProductRepository.findById(answerInputDto.getProductId());
        Optional<QnA> qnA = iQnARepository.findById(answerInputDto.getId());

        if(product.isPresent() && qnA.isPresent()) {
            Optional<QnA> aAdd = iQnARepository.findById(answerInputDto.getId());

            if(!aAdd.isPresent())
                return null;

            aAdd.get().setAnswerDate(new Timestamp(System.currentTimeMillis()));
            aAdd.get().setAnswerMain(answerInputDto.getAnswer());

            return QnAOutputDto.builder()
                    .id(aAdd.get().getId())
                    .title(aAdd.get().getTitle())
                    .questionMain(aAdd.get().getQuestionMain())
                    .questionDate(aAdd.get().getQuestionDate())
                    .answerMain(aAdd.get().getAnswerMain())
                    .answerDate(aAdd.get().getAnswerDate())
                    .lockCase(aAdd.get().isLockCase())
                    .userAccount(aAdd.get().getUser().getUserId().substring(0, 3)+"******")
                    .productId(aAdd.get().getProduct().getId())
                    .build();
        }

        return null;
    }
}
