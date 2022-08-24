package com.cloneproject.ssgjojo.attention.service;

import com.cloneproject.ssgjojo.attention.domain.Attention;
import com.cloneproject.ssgjojo.attention.dto.AttentionAddDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionEditFolderDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionInputFolderDto;
import com.cloneproject.ssgjojo.attention.dto.AttentionOutputDto;
import com.cloneproject.ssgjojo.attention.repository.IAttentionRepository;
import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.attentionfolder.repository.IAttentionFolderRepository;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    // 좋아요 항목 추가
    @Override
    public void AttentionAdd(AttentionAddDto addDto) {
        Optional<User> user = iUserRepository.findById(addDto.getUserId());
        Optional<Product> product = iProductRepository.findById(addDto.getProductId());

        if(user.isPresent() && product.isPresent()) {
            List<Attention> attention = iAttentionRepository.findByUserAndProduct(user.get(), product.get());
            List<AttentionFolder> folderList = iAttentionFolderRepository.findAllByUserOrderByNo(user.get());

            // 이미 추가된 상품 확인
            if(attention.size() != 0)
                return;

            // 사용자의 0번 폴더에 항목을 추가한다.
            AttentionFolder folder = folderList.get(0);

            iAttentionRepository.save(Attention.builder()
                            .product(product.get())
                            .user(user.get())
                            .attentionFolder(folder)
                    .build());
        }
    }

    // 좋아요 항목 폴더에 추가
    @Override
    public void AttentionAddFolder(AttentionInputFolderDto addFolderDto) {
        Optional<User> user = iUserRepository.findById(addFolderDto.getUserId());

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

    // 좋아요 항목 볼더 변경
    @Override
    @Transactional
    public List<AttentionOutputDto> AttentionEditFolder(AttentionEditFolderDto attentionEditFolderDto) {
        Optional<User> user = iUserRepository.findById(attentionEditFolderDto.getUserId());

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


            //
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
}
