package com.cloneproject.ssgjojo.attention.service;

import com.cloneproject.ssgjojo.attention.domain.Attention;
import com.cloneproject.ssgjojo.attention.dto.*;
import com.cloneproject.ssgjojo.attention.repository.IAttentionRepository;
import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.repository.IAttentionFolderRepository;
import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttentionServiceImple implements IAttentionService{
    private final IAttentionRepository iAttentionRepository;
    private final IAttentionFolderRepository iAttentionFolderRepository;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;
    private final JwtTokenProvider jwtTokenProvider;



    // 좋아요 항목 추가
    @Override
    public void AttentionAdd(AttentionAddDto addDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        User user = iUserRepository.findById(userId).orElseThrow(()
                -> new IllegalStateException("없는 사용자입니다."));
        Optional<Product> product = iProductRepository.findById(addDto.getProductId());

        if(product.isPresent()) {
            List<Attention> attention = iAttentionRepository.findByUserAndProduct(user, product.get());
            List<AttentionFolder> folderList = iAttentionFolderRepository.findAllByUserOrderByNo(user);

            // 이미 추가된 상품 확인
            if(attention.size() != 0)
                return;

            // 사용자의 0번 폴더에 항목을 추가한다.
            AttentionFolder folder = folderList.get(0);

            iAttentionRepository.save(Attention.builder()
                            .product(product.get())
                            .user(user)
                            .attentionFolder(folder)
                    .build());
        }
    }


    // 좋아요 항목 폴더에 추가
    @Override
    public void AttentionAddFolder(AttentionInputFolderDto addFolderDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if (user.isPresent()) {
            for (Long attentionId : addFolderDto.getAttentionIdList()) {
                Optional<Attention> attention = iAttentionRepository.findById(attentionId);

                if(attention.get().getUser().getId() != addFolderDto.getUserId())
                    continue;

                for (Long folderId : addFolderDto.getFolderIdList()) {
                    Optional<AttentionFolder> folder = iAttentionFolderRepository.findById(folderId);

                    if(!folder.isPresent())
                        continue;


                    if(folder.get().getUser().getId() != addFolderDto.getUserId())
                        continue;

                    Product product = attention.get().getProduct();

                    iAttentionRepository.save(Attention.builder()
                                    .product(product)
                                    .user(user.get())
                                    .attentionFolder(folder.get())
                            .build());
                }

            }
        }
    }


    // 좋아요 항목 폴더 변경
    @Override
    @Transactional
    public List<AttentionOutputDto> AttentionEditFolder(AttentionEditFolderDto attentionEditFolderDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            // 변경하여 저장 할 폴더 리스트
            List<AttentionFolder> folderList = new ArrayList<>();

            for(Long folderId : attentionEditFolderDto.getFolderIdList()) {
                Optional<AttentionFolder> folder = iAttentionFolderRepository.findById(folderId);

                if(folder.isPresent() && folder.get().getUser().getId() == user.get().getId())
                    folderList.add(folder.get());
            }

            // 저장될 좋아요 항목 리스트
            List<Attention> attentionList = new ArrayList<>();

            for(Long attentionId : attentionEditFolderDto.getAttentionIdList()) {
                Optional<Attention> attention = iAttentionRepository.findById(attentionId);

                if(attention.isPresent())
                    attentionList.add(attention.get());
            }

            for(Long attentionId : attentionEditFolderDto.getAttentionIdList()) {
                Optional<Attention> attention = iAttentionRepository.findById(attentionId);

                if(!attention.isPresent())
                    continue;

                for(AttentionFolder folder : folderList ) {
                    iAttentionRepository.save(Attention.builder()
                                    .attentionFolder(folder)
                                    .user(user.get())
                                    .product(attention.get().getProduct())
                            .build());
                }

                iAttentionRepository.delete(attention.get());
            }
            AttentionFolder originFolder = iAttentionFolderRepository.findById(attentionEditFolderDto.getOriginFolderId()).get();
            List<Attention> originAttentionList = iAttentionRepository.findAllByAttentionFolder(originFolder);

            List<AttentionOutputDto> outputDtoList = new ArrayList<>();

            for(Attention attention : originAttentionList) {
                Product temp = iProductRepository.findById(attention.getProduct().getId()).get();

                String productInfo =  temp.getManufactureCompany() + temp.getProductName();
                Long productPrice = temp.getPrice();
                Long productId = temp.getId();

                outputDtoList.add(AttentionOutputDto.builder()
                        .productId(productId)
                        .productInfo(productInfo)
                        .productPrice(productPrice)
                        .build());
            }

            return outputDtoList;

        }
        return null;
    }


    // 특정 폴더에 있는 좋아요 항목 조회
    @Override
    public List<AttentionOutputDto> findAllByAttentionFolder(Long folderId) {

        Optional<AttentionFolder> attentionFolder = iAttentionFolderRepository.findById(folderId);

        if(attentionFolder.isPresent()) {
            List<Attention> attentionList = iAttentionRepository.findAllByAttentionFolder(attentionFolder.get());
            List<AttentionOutputDto> returnDto = new ArrayList<>();

            for(Attention attention : attentionList) {
                Product product = attention.getProduct();

                String productInfo = product.getManufactureCompany() + product.getProductName();
                Long productPrice = product.getPrice();

                if(product.getDiscountRate() != 0)
                    productPrice = (long) ((float) productPrice * (1 - ((float) product.getDiscountRate() /100 )));

                returnDto.add(AttentionOutputDto.builder()
                                .productId(attention.getProduct().getId())
                                .productInfo(productInfo)
                                .productPrice(productPrice)
                        .build());
            }

            return returnDto;
        }

        return null;
    }


    // 전체 폴더에서 삭제
    @Override
    @Transactional
    public boolean deleteAttention(AttentionDeleteDto deleteDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            for (Long productId : deleteDto.getProductId()) {
                Optional<Product> product = iProductRepository.findById(productId);
                if(!product.isPresent())
                    continue;

                iAttentionRepository.deleteAllByProductAndUser(product.get(), user.get());
            }
            return true;
        }

        return false;
    }


    // 특정 폴더에서 삭제
    @Override
    public boolean deleteAttentionInFolder(AttentionDeleteFolderDto deleteDto, HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            for(Long attentionId : deleteDto.getAttentionId()) {
                Optional<Attention> attention = iAttentionRepository.findById(attentionId);
                if(!attention.isPresent())
                    continue;
                if(attention.get().getUser().getId() == user.get().getId())
                    iAttentionRepository.deleteById(attentionId);
            }

            return true;
        }

        return false;
    }
}