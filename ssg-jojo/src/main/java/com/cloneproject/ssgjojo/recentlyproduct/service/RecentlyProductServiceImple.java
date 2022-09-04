package com.cloneproject.ssgjojo.recentlyproduct.service;

import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductDeleteDto;
import com.cloneproject.ssgjojo.recentlyproduct.dto.RecentlyProductOutputDto;
import com.cloneproject.ssgjojo.recentlyproduct.repository.IRecentlyProductRepository;
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
public class RecentlyProductServiceImple implements IRecentlyProductService{
    private final IUserRepository iUserRepository;
    private final IRecentlyProductRepository iRecentlyProductRepository;
    private final IProductRepository iProductRepository;
    private final JwtTokenProvider jwtTokenProvider;



    // 유저별 최근 본 상품 조회
    @Override
    public List<RecentlyProductOutputDto> findAllByUser(HttpServletRequest request) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(user.isPresent()) {
            List<RecentlyProduct> userViewList = iRecentlyProductRepository.findAllByUserOrderByViewTimeDesc(user.get());
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
                                .thumbnailUri(item.getProduct().getThumbnail())
                                .mallName("신세계몰")
                        .build());
            }

            return returnDto;
        }
        return null;
    }


    // 최근 본 상품 각각 삭제
    @Override
    @Transactional
    public String deleteByRecentlyId(List<RecentlyProductDeleteDto> recentlyProductDeleteDto, HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(!user.isPresent())
            return "False";

        for(RecentlyProductDeleteDto deleteDto : recentlyProductDeleteDto) {
            // 유저가 존재하지 않을 경우
            // 요청한 유저랑 유저 번호가 다르면 어떡하지?
            Optional<RecentlyProduct> recentlyProduct = iRecentlyProductRepository.findById(deleteDto.getId());

            // 없는 고유키로 요청할 경우
            if(!recentlyProduct.isPresent())
                continue;
            
            // 타인의 최근 본내역을 삭제하려고 할 경우
            if(user.get().getId() != recentlyProduct.get().getUser().getId())
                continue;

            iRecentlyProductRepository.deleteById(deleteDto.getId());
        }

        return "True";
    }


    // 최근 본 상품 전체 삭제
    @Override
    @Transactional
    public String deleteAllByUserId(HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
        Optional<User> user = iUserRepository.findById(userId);

        if(!user.isPresent())
            return "False";

        iRecentlyProductRepository.deleteAllByUser(user.get());

        return "True";
    }
}
