package com.cloneproject.ssgjojo.qna.service;

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

    @Override
    public QnAOutputDto addQ(QuestionInputDto questionInputDto) {

        Optional<User> user = iUserRepository.findById(questionInputDto.getUserId());
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
                    .lockCase(qna.isLockCase())
                    .userId(qna.getUser().getId())
                    .productId(qna.getProduct().getId())
                    .build();
        }

        return null;
    }


    @Override
    public List<QnAOutputDto> getQnaByProuductId(Long id) {

        Optional<Product> product = iProductRepository.findById(id);
        List<QnAOutputDto> qnAOutputDtoList = new ArrayList<>();
        List<QnA> temp = new ArrayList<>();

        if(product.isPresent()) {
            List<QnA> qnAList = iQnARepository.findAllByProduct(product.get());

            if (!product.isEmpty()) {
                for (QnA qnA : qnAList) {
                    qnAOutputDtoList.add(QnAOutputDto.builder()
                            .id(qnA.getId())
                            .title(qnA.getTitle())
                            .questionMain(qnA.getQuestionMain())
                            .answerMain(qnA.getAnswerMain())
                            .questionDate(qnA.getQuestionDate())
                            .answerDate(qnA.getAnswerDate())
                            .lockCase(qnA.isLockCase())
                            .userId(qnA.getUser().getId())
                            .productId(qnA.getProduct().getId())
                            .build());
                }
            }

            return qnAOutputDtoList;

        }

        return null;
    }

    @Override
    public QnAOutputDto editQ(QnAEditDto qnAEditDto) {

        Optional<User> user = iUserRepository.findById(qnAEditDto.getUserId());
        Optional<Product> product = iProductRepository.findById(qnAEditDto.getProductId());
        Optional<QnA> qnA = iQnARepository.findById(qnAEditDto.getId());

        if (qnA.isPresent() && user.isPresent() && product.isPresent()) {
            if(qnA.get().getUser().getId() == qnAEditDto.getUserId()) {
                if(qnA.get().getAnswerMain() == null) {
                    QnA qnAEdit = iQnARepository.save(QnA.builder()
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
                            .title(qnAEdit.getTitle())
                            .questionMain(qnAEdit.getQuestionMain())
                            .questionDate(qnAEdit.getQuestionDate())
                            .lockCase(qnAEdit.isLockCase())
                            .userId(qnAEdit.getUser().getId())
                            .productId(qnAEdit.getProduct().getId())
                            .build();
                }
            }
        }


        return null;
    }

    @Override
    public Integer getQuestionCountByProduct(Long productId) {
        Integer qna = iQnARepository.getQuestionCountByProduct(productId);
        return qna;
    }

    @Override
    public List<QnAOutputDto> getTop5(Long productId) {

        Optional<Product> product =  iProductRepository.findById(productId);
        List<QnAOutputDto> returnQnaList = new ArrayList<>();

        if(product.isPresent()) {
            List<QnA> qnaList = iQnARepository.findTop5ByProduct(product.get());

            for(QnA qnA : qnaList) {
                returnQnaList.add(QnAOutputDto.builder()
                        .id(qnA.getId())
                        .title(qnA.getTitle())
                        .questionMain(qnA.getQuestionMain())
                        .questionDate(qnA.getQuestionDate())
                        .answerMain(qnA.getAnswerMain())
                        .answerDate(qnA.getAnswerDate())
                        .lockCase(qnA.isLockCase())
                        .userId(qnA.getUser().getId())
                        .productId(qnA.getProduct().getId())
                        .userAccount(qnA.getUser().getUserId().substring(3)+"******")
                        .build());
            }

            return returnQnaList;
        }

        return null;
    }

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

    @Override
    public void deleteQuestion(QnADeleteDto qnADeleteDto) {

        Optional<User> user = iUserRepository.findById(qnADeleteDto.getUserId());
        Optional<QnA> qnA = iQnARepository.findById(qnADeleteDto.getId());

        if (user.isPresent() && qnA.isPresent()) {
            if(qnA.get().getUser().getId() == qnADeleteDto.getUserId()) {
                iQnARepository.deleteById(qnADeleteDto.getId());
            }
        }

    }

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
                    .userId(aAdd.get().getUser().getId())
                    .productId(aAdd.get().getProduct().getId())
                    .build();
        }

        return null;
    }
}
