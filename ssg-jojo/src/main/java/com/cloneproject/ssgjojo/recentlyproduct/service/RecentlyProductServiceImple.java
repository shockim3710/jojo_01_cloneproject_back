package com.cloneproject.ssgjojo.recentlyproduct.service;

import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productnewservice.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductAddDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;
import com.cloneproject.ssgjojo.recentlyproduct.repository.IRecentlyProductRepository;
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
public class RecentlyProductServiceImple implements IRecentlyProductService{
    private final IUserRepository iUserRepository;
    private final IRecentlyProductRepository iRecentlyProductRepository;
    private final IProductRepository iProductRepository;

    @Override
    public void addRecentlyProduct(RecentlyProductAddDto recentlyProductAddDto) {
        Optional<User> user = iUserRepository.findById(recentlyProductAddDto.getUserId());
        Optional<Product> product = iProductRepository.findById(recentlyProductAddDto.getProductId());

        if(user.isPresent() && product.isPresent()) {
            iRecentlyProductRepository.save(RecentlyProduct.builder()
                    .user(user.get())
                    .product(product.get())
                    .build()
            );
        }
    }

    @Override
    public List<RecentlyProductOutputDto> findAllByUser(Long userId) {
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            List<RecentlyProduct> userViewList = iRecentlyProductRepository.findAllByUser(user.get());
            List<RecentlyProductOutputDto> returnDto = new ArrayList<>();

            for(RecentlyProduct item : userViewList) {
                Optional<Product> product = iProductRepository.findById(item.getProduct().getId());

                if(!product.isPresent())
                    continue;

                String productInfo = String.format("[%s]%s", product.get().getManufactureCompany(), product.get().getProductName());

                Long productPrice = product.get().getPrice();
                int productDiscount = product.get().getDiscountRate();

                if(productDiscount != 0)
                    productPrice = (long) ((float) productPrice * (1 - ((float) productDiscount /100 )));


                returnDto.add(RecentlyProductOutputDto.builder()
                                .id(item.getId())
                                .productId(item.getProduct().getId())
                                .productInfo(productInfo)
                                .price(productPrice)
                        .build());
            }

            return returnDto;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteByRecentlyId(List<RecentlyProductDeleteDto> recentlyProductDeleteDto) {
        for(RecentlyProductDeleteDto deleteDto : recentlyProductDeleteDto) {
            Optional<User> user = iUserRepository.findById(deleteDto.getUserId());
            
            // 유저가 존재하지 않을 경우
            // 요청한 유저랑 유저 번호가 다르면 어떡하지?
            if(!user.isPresent())
                continue;
            
            iRecentlyProductRepository.deleteById(deleteDto.getId());
        }
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        Optional<User> user = iUserRepository.findById(userId);

        if(!user.isPresent())
            return;

        iRecentlyProductRepository.deleteAllByUser(user.get());
    }
}
