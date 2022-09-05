package com.cloneproject.ssgjojo.product.service;


import com.cloneproject.ssgjojo.attention.repository.IAttentionRepository;
import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.dto.CategoryDto;
import com.cloneproject.ssgjojo.categorylv1.repository.ICategoryLv1Repository;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv3.repository.ICategoryLv3Repository;
import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categorylv4.repository.ICategoryLv4Repository;

import com.cloneproject.ssgjojo.categoryproductlist.domain.CategoryProductList;
import com.cloneproject.ssgjojo.categoryproductlist.repository.ICategoryProductListRepository;
import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.*;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productdetailphoto.domain.ProductDetailPhoto;
import com.cloneproject.ssgjojo.productdetailphoto.dto.ProductDetailPhotoDto;
import com.cloneproject.ssgjojo.productdetailphoto.repository.IProductDetailPhotoRepository;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionDto;
import com.cloneproject.ssgjojo.productoption.repository.IProductOptionRepository;
import com.cloneproject.ssgjojo.productphoto.domain.ProductPhoto;
import com.cloneproject.ssgjojo.productphoto.dto.ProductPhotoDto;
import com.cloneproject.ssgjojo.productphoto.repository.IProductPhotoRepository;
import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.QnAOutputDto;
import com.cloneproject.ssgjojo.qna.repository.IQnARepository;

import com.cloneproject.ssgjojo.recentlyproduct.domain.RecentlyProduct;
import com.cloneproject.ssgjojo.recentlyproduct.repository.IRecentlyProductRepository;

import com.cloneproject.ssgjojo.recentsearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.recentsearches.repository.IRecentSearchesRepository;

import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import com.cloneproject.ssgjojo.reviewphoto.dto.ReviewPhotoDto;
import com.cloneproject.ssgjojo.reviewphoto.repository.IReviewPhotoRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import com.cloneproject.ssgjojo.util.MultipartUtil;
import com.cloneproject.ssgjojo.util.s3.AwsS3ResourceStorage;
import com.cloneproject.ssgjojo.util.s3.FileInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImple implements IProductService {
    private final IProductRepository iProductRepository;

    private final ICategoryLv4Repository iCategoryLv4Repository;
    private final ICategoryLv3Repository iCategoryLv3Repository;
    private final ICategoryLv2Repository iCategoryLv2Repository;
    private final ICategoryLv1Repository iCategoryLv1Repository;
    private final ICategoryProductListRepository iCategoryProductListRepository;

    private final IUserRepository iUserRepository;

    private final IProductOptionRepository iProductOptionRepository;
    private final IProductPhotoRepository iProductPhotoRepository;
    private final IProductDetailPhotoRepository iProductDetailPhotoRepository;

    private final IRecentlyProductRepository iRecentlyProductRepository;

    private final IReviewRepository iReviewRepository;
    private final IReviewPhotoRepository iReviewPhotoRepository;
    private final IQnARepository iQnARepository;
    private final AwsS3ResourceStorage awsS3ResourceStorage;
    private final JwtTokenProvider jwtTokenProvider;

    private final IRecentSearchesRepository iRecentSearchesRepository;
    private final IAttentionRepository iAttentionRepository;


    // 상품 추가 (이미지 포함)
    @Override
    public Product addProduct(ProductAddDto productAddDto, MultipartFile thumbnail, List<MultipartFile> productPhoto, List<MultipartFile> productDetail) {
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productAddDto.getCategoryLv1());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productAddDto.getCategoryLv2());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productAddDto.getCategoryLv3());
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productAddDto.getCategoryLv4());

        // 카테고리 유효성 검증
        if(categoryLv1.isPresent() && categoryLv2.isPresent() && categoryLv3.isPresent() && categoryLv4.isPresent()) {

            if(thumbnail.isEmpty())
                return null;

            // 상품 저장
            Product product = iProductRepository.save(Product.builder()
                    .productName(productAddDto.getProductName())
                    .price(productAddDto.getPrice())
                    .discountRate(productAddDto.getDiscountRate())
                    .description(productAddDto.getDescription())
                    .manufactureCompany(productAddDto.getManufactureCompany())
                    .adultCase(productAddDto.isAdultCase())
                    .fee(productAddDto.getFee())
                    .adultCase(productAddDto.isAdultCase())
                    .build()
            );

            FileInfoDto thumbnailDto = FileInfoDto.multipartOf(thumbnail, "product", product.getId());
            awsS3ResourceStorage.store(thumbnailDto, thumbnail);

            product.setThumbnail(MultipartUtil.createURL(thumbnailDto.getRemotePath()));


            // 상품 - 카테고리 중칸테이블 저장
            iCategoryProductListRepository.save(CategoryProductList.builder()
                    .categoryLv1(categoryLv1.get())
                    .categoryLv2(categoryLv2.get())
                    .categoryLv3(categoryLv3.get())
                    .categoryLv4(categoryLv4.get())
                    .product(product)
                    .build());

            // 상품 옵션 저장
            // 상품 등록할 때 옵션을 여러가지 등록할 수 있으므로 foreach로 하나씩 저장
            for(ProductOptionDto productOptionDto : productAddDto.getProductOptionDtoList()) {
                iProductOptionRepository.save(ProductOption.builder()
                        .product(product)
                        .productOption1Name(productOptionDto.getProductOption1Name())
                        .productOption1Contents(productOptionDto.getProductOption1Contents())
                        .productOption2Name(productOptionDto.getProductOption2Name())
                        .productOption2Contents(productOptionDto.getProductOption2Contents())
                        .stock(productOptionDto.getStock())
                        .build());
            }

            List<ProductPhoto> productPhotoList = new ArrayList<>();
            List<ProductDetailPhoto> productDetailPhotoList = new ArrayList<>();

            int count = 1;

            // 이미지 리스트로 추가
            for(MultipartFile file : productPhoto) {
                FileInfoDto fileInfoDto = FileInfoDto.multipartOf(file, "product", product.getId());
                awsS3ResourceStorage.store(fileInfoDto, file);

                productPhotoList.add(iProductPhotoRepository.save(ProductPhoto.builder()
                                .productPhotoPath(MultipartUtil.createURL(fileInfoDto.getRemotePath()))
                                .productPhotoOriginName(fileInfoDto.getName())
                                .productPhotoSeq(count++)
                                .product(product)
                        .build()
                ));
            }

            count = 1;

            for(MultipartFile file : productDetail) {
                FileInfoDto fileInfoDto = FileInfoDto.multipartOf(file, "product", product.getId());
                awsS3ResourceStorage.store(fileInfoDto, file);

                productDetailPhotoList.add(iProductDetailPhotoRepository.save(ProductDetailPhoto.builder()
                        .productDetailPhotoPath(MultipartUtil.createURL(fileInfoDto.getRemotePath()))
                        .productDetailPhotoOriginName(fileInfoDto.getName())
                        .productDetailPhotoSeq(count++)
                        .product(product)
                        .build()
                ));
            }


            return product;
        }

        return null;
    }

    // 상품 삭제
    @Override
    @Transactional
    public boolean deleteProduct(Long id) {
        Optional<Product> product = iProductRepository.findById(id);
        if(product.isPresent()) {

            iCategoryProductListRepository.deleteAllByProduct(product.get());
            iProductOptionRepository.deleteByProduct(product.get());
            iProductPhotoRepository.deleteAllByProduct(product.get());
            iProductDetailPhotoRepository.deleteAllByProduct(product.get());

            iProductRepository.deleteById(id);

            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Product editProduct(ProductUpdateDto productUpdateDto) {
        Optional<Product> product = iProductRepository.findById(productUpdateDto.getId());
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productUpdateDto.getCategoryLv4());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productUpdateDto.getCategoryLv3());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productUpdateDto.getCategoryLv2());
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productUpdateDto.getCategoryLv1());

        Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product.get());

        if(product.isPresent() && categoryLv4.isPresent() && categoryLv3.isPresent() && categoryLv2.isPresent() && categoryLv1.isPresent()) {
            Product editProduct = iProductRepository.save(Product.builder()
                    .id(productUpdateDto.getId())
                    .productName(productUpdateDto.getProductName())
                    .price(productUpdateDto.getPrice())
                    .description(productUpdateDto.getDescription())
                    .manufactureCompany(productUpdateDto.getManufactureCompany())
                    .discountRate(productUpdateDto.getDiscountRate())
                    .fee(productUpdateDto.getFee())
                    .adultCase(productUpdateDto.isAdultCase())
                    .build()
            );

            iCategoryProductListRepository.save(CategoryProductList.builder()
                    .id(categoryProductList.get().getId())
                    .categoryLv1(categoryLv1.get())
                    .categoryLv2(categoryLv2.get())
                    .categoryLv3(categoryLv3.get())
                    .categoryLv4(categoryLv4.get())
                    .product(editProduct)
                    .build());

            for(ProductOption productOption : productUpdateDto.getProductOptionList()) {
                // 새로 등록된 옵션이면 아래 로직
                if(productOption.getId() == null) {
                    iProductOptionRepository.save(ProductOption.builder()
                            .product(editProduct)
                            .productOption1Name(productOption.getProductOption1Name())
                            .productOption1Contents(productOption.getProductOption1Contents())
                            .productOption2Name(productOption.getProductOption2Name())
                            .productOption2Contents(productOption.getProductOption2Contents())
                            .stock(productOption.getStock())
                            .build());
                }
                else {
                    iProductOptionRepository.save(ProductOption.builder()
                            .id(productOption.getId())
                            .product(editProduct)
                            .productOption1Name(productOption.getProductOption1Name())
                            .productOption1Contents(productOption.getProductOption1Contents())
                            .productOption2Name(productOption.getProductOption2Name())
                            .productOption2Contents(productOption.getProductOption2Contents())
                            .stock(productOption.getStock())
                            .build());
                }
            }

            return editProduct;
        }

        return null;

    }

    // 전체 상품 목록 반환
    @Override
    public List<ProductListDto> getAllProductList(HttpServletRequest request) {
        List<ProductListDto> productList = new ArrayList<>();
        Pageable pr = PageRequest.of(0, 20, Sort.by("id").ascending());

        if(request.getHeader("Authorization") != null && !request.getHeader("Authorization").equals("null")) {
            Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
            Optional<User> user = iUserRepository.findById(userId);

            if(user.isPresent()) {
                productList = iProductRepository.getProductListWithUser(pr, userId);
            }
        }
        else
            productList= iProductRepository.getProductList(pr);

        return productList;
    }


    // 상품 상세
    @Override
    @Transactional
    public ProductDetailDto getProductDetail(Long productId, HttpServletRequest request) {
        Optional<Product> product = iProductRepository.findById(productId);

        // Request 헤더에서 Authorization에 대한 설정이 있을 경우
        // 최근 본 상품에 등록
        if(request.getHeader("Authorization") != null && !request.getHeader("Authorization").equals("null")) {
            Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
            Optional<User> user = iUserRepository.findById(userId);

            if(user.isPresent()) {
                Optional<RecentlyProduct> recentlyProduct = iRecentlyProductRepository.findByUserAndProduct(user.get(), product.get());

                if(recentlyProduct.isPresent())
                    recentlyProduct.get().setViewTime(new Timestamp(System.currentTimeMillis()));

                else
                    iRecentlyProductRepository.save(RecentlyProduct.builder()
                            .product(product.get())
                            .user(user.get())
                            .viewTime(new Timestamp(System.currentTimeMillis()))
                            .build());
            }
        }

        if(!product.isPresent())
            return null;

        // 리뷰 평점, 리뷰 개수 조회
        Float reviewScore = iReviewRepository.getReviewAvgScore(product.get().getId());
        int reviewNum = iReviewRepository.getReviewCountByProduct(product.get().getId());

        Long newPrice = 0L;
        Long oldPrice = 0L;
        int discountRate = product.get().getDiscountRate();

        // 할인 가격 적용
        if(discountRate != 0) {
            oldPrice = product.get().getPrice();
            newPrice = (long) ((float) oldPrice * (1 - ((float) discountRate / 100)));
        }
        else
            newPrice= product.get().getPrice();

        List<ProductPhoto> productPhotoList = iProductPhotoRepository.findAllByProduct(product.get());
        List<ProductPhotoDto> productPhotoDtoList = new ArrayList<>();

        for(ProductPhoto productPhoto : productPhotoList) {
            productPhotoDtoList.add(ProductPhotoDto.builder()
                    .productPhotoPath(productPhoto.getProductPhotoPath())
                    .productPhotoOriginName(productPhoto.getProductPhotoOriginName())
                    .productPhotoSeq(productPhoto.getProductPhotoSeq())
                    .productId(productPhoto.getProduct().getId())
                    .build());
        }

        List<ProductOption> productOption = iProductOptionRepository.findAllByProduct(product.get());
        List<Object> optionList = new ArrayList<Object>();
        HashMap<String, Object> map = new HashMap<>();

        if(productOption.size() != 0) {
            HashMap<String, Object> opt1Map = new HashMap<>();
            HashMap<String, Object> opt2Map = new HashMap<>();
            List<Object> opt1List = new ArrayList<>();
            List<Object> opt2List = new ArrayList<>();

            if(productOption.get(0).getProductOption1Name() == null) {
                map.put("productOption1Name", null);
                map.put("productOption2Name", null);

                opt2Map.put("productOption2Contents", null);
                opt2Map.put("optionId", productOption.get(0).getId());
                opt2Map.put("stock", productOption.get(0).getStock());

                opt2List.add(opt2Map);

                opt1Map.put("productOption1Contents", null);
                opt1Map.put("option2", opt2Map);

                opt1List.add(opt1Map);

                map.put("options", opt1List);
            } else {
                String option1Name = productOption.get(0).getProductOption1Name();
                String option2Name = productOption.get(0).getProductOption2Name();

                map.put("productOption1Name", option1Name);
                map.put("productOption2Name", option2Name);

                List<String> opt1ContentsList = iProductOptionRepository.getOpt1(productId);

                for(String opt1Contents : opt1ContentsList) {
                    List<ProductOption> productOptionList = iProductOptionRepository.findAllByProductAndProductOption1Contents(product.get(), opt1Contents);

                    opt1Map = new HashMap<>();
                    opt2List = new ArrayList<>();
                    for(ProductOption option : productOptionList) {
                        opt2Map = new HashMap<>();
                        opt2Map.put("productOption2Contents", option.getProductOption2Contents());
                        opt2Map.put("optionId", option.getId());
                        opt2Map.put("stock", option.getStock());

                        opt2List.add(opt2Map);
                    }
                    opt1Map.put("productOption1Contents", opt1Contents);
                    opt1Map.put("option2", opt2List);
                    opt1List.add(opt1Map);
                }
                map.put("options", opt1List);
            }
        }

        List<ProductDetailPhoto> productDetailPhotoList = iProductDetailPhotoRepository.findAllByProduct(product.get());
        List<ProductDetailPhotoDto> detailPhotoDtoList = new ArrayList<>();

        for(ProductDetailPhoto productDetailPhoto : productDetailPhotoList) {
            detailPhotoDtoList.add(ProductDetailPhotoDto.builder()
                    .productDetailPhotoPath(productDetailPhoto.getProductDetailPhotoPath())
                    .productDetailPhotoOriginName(productDetailPhoto.getProductDetailPhotoOriginName())
                    .productDetailPhotoSeq(productDetailPhoto.getProductDetailPhotoSeq())
                    .productId(product.get().getId())
                    .build());
        }

        List<Review> reviewList = iReviewRepository.findTop5ByProduct(product.get());
        List<ReviewOutputDto> reviewOutputDtoList = new ArrayList<>();

        for(Review review : reviewList) {
            List<ReviewPhoto> reviewPhotoList = iReviewPhotoRepository.findAllByReview(review);
            List<ReviewPhotoDto> reviewPhotoDtoList = new ArrayList<>();

            if(!reviewPhotoList.isEmpty()) {
                for(ReviewPhoto reviewPhoto : reviewPhotoList) {
                    reviewPhotoDtoList.add(ReviewPhotoDto.builder()
                            .id(reviewPhoto.getId())
                            .reviewPhotoPath(reviewPhoto.getReviewPhotoPath())
                            .reviewId(reviewPhoto.getReview().getId())
                            .build());
                }
            }
            reviewOutputDtoList.add(ReviewOutputDto.builder()
                    .id(review.getId())
                    .title(review.getTitle())
                    .mainText(review.getMainText())
                    .score(review.getScore())
                    .userAccount(review.getUser().getUserId().substring(3)+"******")
                    .productId(review.getProduct().getId())
                    .createdTime(review.getCreatedDate())
                    .reviewPhotoDtoList(reviewPhotoDtoList)
                    .build());
        }

        List<QnA> qnAList = iQnARepository.findAllByProduct(product.get());
        List<QnAOutputDto> qnAOutputDtoList = new ArrayList<>();

        for(QnA qnA : qnAList) {

            qnAOutputDtoList.add(QnAOutputDto.builder()
                    .id(qnA.getId())
                    .title(qnA.getTitle())
                    .questionMain(qnA.getQuestionMain())
                    .questionDate(qnA.getQuestionDate())
                    .answerMain(qnA.getAnswerMain())
                    .answerDate(qnA.getAnswerDate())
                    .lockCase(qnA.isLockCase())
                    .userAccount(qnA.getUser().getUserId().substring(3)+"******")
                    .productId(qnA.getProduct().getId())
                    .build());
        }

        return ProductDetailDto.builder()
                .id(product.get().getId())
                .mallName("신세계몰")
                .productName(product.get().getProductName())
                .manufactureCompany(product.get().getManufactureCompany())
                .oldPrice(oldPrice)
                .newPrice(newPrice)
                .discountRate(product.get().getDiscountRate())
                .reviewScore(reviewScore)
                .reviewNum(reviewNum)
                .productPhotoList(productPhotoDtoList)
                .productDetailPhotoList(detailPhotoDtoList)
                .reviewList(reviewOutputDtoList)
                .productOptions(map)
                .QnaList(qnAOutputDtoList)
                .build();
    }


    // 상품 리스트 조회
    @Override
    public ProductInfoCategoryDto findProductByCategoryLv(Long lv, Long id, int page, HttpServletRequest request) {
        List<ProductListDto> findResult = new ArrayList<>();

        Pageable pr = PageRequest.of(page - 1, 20, Sort.by("id").descending());

        List<CategoryDto> parentLevelCategory = new ArrayList<CategoryDto>();
        List<CategoryDto> sameLevelCategory = new ArrayList<CategoryDto>();
        List<CategoryDto> childLevelCategory = new ArrayList<CategoryDto>();
        Long totalCnt = 0L;
        Long userId = -1L;

        if(request.getHeader("Authorization") != null && !request.getHeader("Authorization").equals("null")) {
            userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
            User user = iUserRepository.findById(userId).orElseThrow(()
                    -> new IllegalStateException("없는 사용자입니다."));
        }

        if (lv == 1) {
            if(userId != -1L)
                findResult = iCategoryProductListRepository.findByCategoryLv1idWithUser(id, userId, pr);
            else
                findResult = iCategoryProductListRepository.findByCategoryLv1id(id, pr);
            parentLevelCategory = null;
            sameLevelCategory = iCategoryLv1Repository.getCategoryLv1(id);
            childLevelCategory = iCategoryLv2Repository.findAllByCategoryLv1_Id(id);

            totalCnt = iCategoryProductListRepository.countByCategoryLv1(id);

        } else if (lv == 2) {
            if(userId != -1L)
                findResult = iCategoryProductListRepository.findByCategoryLv2idWithUser(id, userId, pr);
            else
                findResult = iCategoryProductListRepository.findByCategoryLv2id(id, pr);

            parentLevelCategory = iCategoryLv2Repository.getParentCategoryLv2();
            sameLevelCategory = iCategoryLv2Repository.getCategoryLv2(id);
            childLevelCategory = iCategoryLv3Repository.findAllByCategoryLv2_Id(id);

            totalCnt = iCategoryProductListRepository.countByCategoryLv2(id);
        } else if (lv == 3) {
            if(userId != -1L)
                findResult = iCategoryProductListRepository.findByCategoryLv3idWithUser(id, userId, pr);
            else
                findResult = iCategoryProductListRepository.findByCategoryLv3id(id, pr);

            parentLevelCategory = iCategoryLv3Repository.getParentCategoryLv3(id);
            sameLevelCategory = iCategoryLv3Repository.getCategoryLv3(id);
            childLevelCategory = iCategoryLv4Repository.findAllByCategoryLv3_Id(id);

            totalCnt = iCategoryProductListRepository.countByCategoryLv3(id);
        } else if (lv == 4) {
            if(userId != -1L)
                findResult = iCategoryProductListRepository.findByCategoryLv4idWithUser(id, userId, pr);
            else
                findResult = iCategoryProductListRepository.findByCategoryLv4id(id, pr);

            parentLevelCategory = iCategoryLv4Repository.getParentCategoryLv4(id);
            sameLevelCategory = iCategoryLv4Repository.getCategoryLv4(id);

            totalCnt = iCategoryProductListRepository.countByCategoryLv4(id);
        } else {
            return null;
        }

        return ProductInfoCategoryDto.builder()
                .productList(findResult)
                .parentCategory(parentLevelCategory)
                .childLevelCategory(childLevelCategory)
                .sameLevelCategory(sameLevelCategory)
                .totalCnt(totalCnt)
                .build();
    }

    // 상품 검색
    @Override
    public List<ProductListDto> productSearch(String keyword, int page, HttpServletRequest request) {
        Pageable pr = PageRequest.of(page - 1, 20, Sort.by("id").descending());
        Long userId = -1L;

        // Request 헤더에서 Authorization에 대한 설정이 있을 경우
        // 최근 검색어에 등록
        if(request.getHeader("Authorization") != null && !request.getHeader("Authorization").equals("null")) {
            userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));
            Optional<User> user = iUserRepository.findById(userId);

            if(user.isPresent())
                iRecentSearchesRepository.save(RecentSearches.builder()
                        .histories(keyword)
                        .user(user.get())
                        .build());
        }

        List<ProductListDto> productListDtoList = new ArrayList<>();

        keyword = "%" + keyword + "%";

        if(userId != -1L)
            productListDtoList = iProductRepository.getProductListWithKeywordAndUser(pr, userId, keyword);
        else
            productListDtoList = iProductRepository.getProductListWithKeyword(pr, keyword);

        return productListDtoList;
    }
}
