package com.cloneproject.ssgjojo.product.service;


import com.cloneproject.ssgjojo.categorylv1.domain.CategoryLv1;
import com.cloneproject.ssgjojo.categorylv1.repository.ICategoryLv1Repository;
import com.cloneproject.ssgjojo.categorylv2.domain.CategoryLv2;
import com.cloneproject.ssgjojo.categorylv2.repository.ICategoryLv2Repository;
import com.cloneproject.ssgjojo.categorylv3.domain.CategoryLv3;
import com.cloneproject.ssgjojo.categorylv3.repository.ICategoryLv3Repository;
import com.cloneproject.ssgjojo.categorylv4.domain.CategoryLv4;
import com.cloneproject.ssgjojo.categorylv4.repository.ICategoryLv4Repository;
import com.cloneproject.ssgjojo.categoryproductlist.domain.CategoryProductList;
import com.cloneproject.ssgjojo.categoryproductlist.repository.ICategoryProductListRepository;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.dto.*;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productdetailphoto.domain.ProductDetailPhoto;
import com.cloneproject.ssgjojo.productdetailphoto.dto.ProductDetailPhotoDto;
import com.cloneproject.ssgjojo.productdetailphoto.repository.IProductDetailPhotoRepository;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionDto;
import com.cloneproject.ssgjojo.productoption.dto.ProductOptionOutputDto;
import com.cloneproject.ssgjojo.productoption.repository.IProductOptionRepository;
import com.cloneproject.ssgjojo.productphoto.domain.ProductPhoto;
import com.cloneproject.ssgjojo.productphoto.dto.ProductPhotoDto;
import com.cloneproject.ssgjojo.productphoto.repository.IProductPhotoRepository;
import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.qna.dto.QnAOutputDto;
import com.cloneproject.ssgjojo.qna.repository.IQnARepository;
import com.cloneproject.ssgjojo.review.domain.Review;
import com.cloneproject.ssgjojo.review.dto.ReviewOutputDto;
import com.cloneproject.ssgjojo.review.repository.IReviewRepository;
import com.cloneproject.ssgjojo.reviewphoto.domain.ReviewPhoto;
import com.cloneproject.ssgjojo.reviewphoto.dto.ReviewPhotoDto;
import com.cloneproject.ssgjojo.reviewphoto.repository.IReviewPhotoRepository;
import com.cloneproject.ssgjojo.util.MultipartUtil;
import com.cloneproject.ssgjojo.util.s3.AwsS3ResourceStorage;
import com.cloneproject.ssgjojo.util.s3.FileInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final IProductOptionRepository iProductOptionRepository;
    private final IProductPhotoRepository iProductPhotoRepository;
    private final IProductDetailPhotoRepository iProductDetailPhotoRepository;
    private final IReviewRepository iReviewRepository;
    private final IReviewPhotoRepository iReviewPhotoRepository;
    private final IQnARepository iQnARepository;
    private final AwsS3ResourceStorage awsS3ResourceStorage;


    
    // 상품 추가
    @Override
    @Transactional
    public Product addProduct(ProductAddDto productAddDto) {
        Optional<CategoryLv1> categoryLv1 = iCategoryLv1Repository.findById(productAddDto.getCategoryLv1());
        Optional<CategoryLv2> categoryLv2 = iCategoryLv2Repository.findById(productAddDto.getCategoryLv2());
        Optional<CategoryLv3> categoryLv3 = iCategoryLv3Repository.findById(productAddDto.getCategoryLv3());
        Optional<CategoryLv4> categoryLv4 = iCategoryLv4Repository.findById(productAddDto.getCategoryLv4());
        
        // 카테고리 유효성 검증
        if(categoryLv1.isPresent() && categoryLv2.isPresent() && categoryLv3.isPresent() && categoryLv4.isPresent()) {
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

            iCategoryProductListRepository.save(CategoryProductList.builder()
                    .categoryLv1(categoryLv1.get())
                    .categoryLv2(categoryLv2.get())
                    .categoryLv3(categoryLv3.get())
                    .categoryLv4(categoryLv4.get())
                    .product(product)
                    .build());
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

            return product;
        }

        return null;
    }

    @Override
    public Product addProductWithPhoto(ProductAddDto productAddDto, MultipartFile thumbnail, List<MultipartFile> productPhoto, List<MultipartFile> productDetail) {
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

    // 상품 아이디를 통한 조회
    @Override
    public ProductInfoDto getProductById(Long id) {
        Optional<Product> product = iProductRepository.findById(id);

        // 상품 유효성 검증
        if(product.isPresent()) {
            // 상품 - 카테고리 중간 테이블에 데이터 있는지 확인
            Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product.get());
            // 옵션 테이블에 해당 상품에 대한 데이터 있는 
            List<ProductOption> productOptionList = iProductOptionRepository.findAllByProduct(product.get());
            List<ProductPhoto> productPhotoList = iProductPhotoRepository.findAllByProduct(product.get());
            List<ProductDetailPhoto> productDetailPhotoList = iProductDetailPhotoRepository.findAllByProduct(product.get());
            
            // 중간 테이블 및 옵션 리스트 유효성 검증
            if(categoryProductList.isPresent() && !productOptionList.isEmpty() && !productPhotoList.isEmpty() && !productDetailPhotoList.isEmpty()) {
                List<ProductOptionOutputDto> optionOutDtoList = new ArrayList<>();

                // 하나의 상품에 여러개의 옵션이 있을 수 있으므로 
                // 옵션 테이블의 데이터를 내가 보내주고 싶은 필드만
                // DTO로 만들고 build 한 뒤, optionOutDtoList에 추가해준다.
                for(ProductOption tmp : productOptionList) {
                    optionOutDtoList.add(ProductOptionOutputDto.builder()
                            .id(tmp.getId())
                            .productId(tmp.getProduct().getId())
                            .productOption1Name(tmp.getProductOption1Name())
                            .productOption1Contents(tmp.getProductOption1Contents())
                            .productOption2Name(tmp.getProductOption2Name())
                            .productOption2Contents(tmp.getProductOption2Contents())
                            .stock(tmp.getStock())
                            .build());
                }

                // 상품 이미지 목록 DTO 형태로 변환하여 저장
                List<ProductPhotoDto> photoDtoList = new ArrayList<>();
                for(ProductPhoto tmp : productPhotoList) {
                    photoDtoList.add(ProductPhotoDto.builder()
                            .productPhotoOriginName(tmp.getProductPhotoOriginName())
                            .productPhotoPath(tmp.getProductPhotoPath())
                            .productPhotoSeq(tmp.getProductPhotoSeq())
                            .productId(tmp.getProduct().getId())
                            .build());
                }

                // 상품 상세 이미지 목록 DTO 형태로 변환하여 저장
                List<ProductDetailPhotoDto> detailPhotoDtoList = new ArrayList<>();
                for(ProductDetailPhoto tmp : productDetailPhotoList) {
                    detailPhotoDtoList.add(ProductDetailPhotoDto.builder()
                            .productDetailPhotoOriginName(tmp.getProductDetailPhotoOriginName())
                            .productDetailPhotoPath(tmp.getProductDetailPhotoPath())
                            .productDetailPhotoSeq(tmp.getProductDetailPhotoSeq())
                            .productId(tmp.getProduct().getId())
                            .build());
                }

                // 최종적으로 반환될 Dto
                // 상품 기본 정보 + 카테고리 + 상품에 해당하는 옵션 리스트를 담아서 보내준다.
                ProductInfoDto returnDto = ProductInfoDto.builder()
                        .id(product.get().getId())
                        .price(product.get().getPrice())
                        .description(product.get().getDescription())
                        .productName(product.get().getProductName())
                        .manufactureCompany(product.get().getManufactureCompany())
                        .discountRate(product.get().getDiscountRate())
                        .fee(product.get().getFee())
                        .adultCase(product.get().isAdultCase())
                        .thumbnail(product.get().getThumbnail())
                        .categoryLv4(categoryProductList.get().getCategoryLv4().getId())
                        .categoryLv3(categoryProductList.get().getCategoryLv3().getId())
                        .categoryLv2(categoryProductList.get().getCategoryLv2().getId())
                        .categoryLv1(categoryProductList.get().getCategoryLv1().getId())
                        .productOptionList(optionOutDtoList)
                        .productPhotoList(photoDtoList)
                        .productDetailPhotoList(detailPhotoDtoList)
                        .build();

                return returnDto;
            }
        }
        return null;
    }

    @Override
    public List<ProductInfoDto> getAllProduct() {
        List<Product> productList = iProductRepository.findAll();
        List<ProductInfoDto> productInfoDtoList = new ArrayList<>();


        for(Product product : productList) {
            Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product);


            // 중간 테이블 및 옵션 리스트 유효성 검증
            if(categoryProductList.isPresent()) {
                List<ProductOption> optionList = iProductOptionRepository.findAllByProduct(product);
                List<ProductPhoto> photoList = iProductPhotoRepository.findAllByProduct(product);
                List<ProductDetailPhoto> detailPhotoList = iProductDetailPhotoRepository.findAllByProduct(product);

                List<ProductOptionOutputDto> optionOutputDtoList = new ArrayList<>();
                List<ProductPhotoDto> photoDtoList = new ArrayList<>();
                List<ProductDetailPhotoDto> detailPhotoDtoList = new ArrayList<>();

                for(ProductOption option : optionList) {
                    optionOutputDtoList.add(ProductOptionOutputDto.builder()
                                    .id(option.getId())
                                    .productOption1Name(option.getProductOption1Name())
                                    .productOption1Contents(option.getProductOption1Contents())
                                    .productOption2Name(option.getProductOption2Name())
                                    .productOption2Contents(option.getProductOption2Contents())
                                    .productId(product.getId())
                                    .stock(option.getStock())
                            .build()
                    );
                }

                for(ProductPhoto photo : photoList) {
                    photoDtoList.add(ProductPhotoDto.builder()
                                    .productId(photo.getProduct().getId())
                                    .productPhotoOriginName(photo.getProductPhotoOriginName())
                                    .productPhotoSeq(photo.getProductPhotoSeq())
                                    .productPhotoPath(photo.getProductPhotoPath())
                            .build());
                }

                for(ProductDetailPhoto detailPhoto : detailPhotoList) {
                    detailPhotoDtoList.add(ProductDetailPhotoDto.builder()
                                    .productId(detailPhoto.getProduct().getId())
                                    .productDetailPhotoOriginName(detailPhoto.getProductDetailPhotoOriginName())
                                    .productDetailPhotoSeq(detailPhoto.getProductDetailPhotoSeq())
                                    .productDetailPhotoPath(detailPhoto.getProductDetailPhotoPath())
                            .build()
                    );
                }


                productInfoDtoList.add(ProductInfoDto.builder()
                        .id(product.getId())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .productName(product.getProductName())
                        .manufactureCompany(product.getManufactureCompany())
                        .discountRate(product.getDiscountRate())
                        .fee(product.getFee())
                        .adultCase(product.isAdultCase())
                        .thumbnail(product.getThumbnail())
                        .categoryLv4(categoryProductList.get().getCategoryLv4().getId())
                        .categoryLv3(categoryProductList.get().getCategoryLv3().getId())
                        .categoryLv2(categoryProductList.get().getCategoryLv2().getId())
                        .categoryLv1(categoryProductList.get().getCategoryLv1().getId())
                        .productOptionList(optionOutputDtoList)
                        .productPhotoList(photoDtoList)
                        .productDetailPhotoList(detailPhotoDtoList)
                        .build());
            }
        }


        return productInfoDtoList;
    }

    // 상품 삭제
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> product = iProductRepository.findById(id);
        if(product.isPresent()) {
            Optional<CategoryProductList> categoryProductList = iCategoryProductListRepository.findByProduct(product.get());
            List<ProductOption> productOptions = iProductOptionRepository.findAllByProduct(product.get());
            List<ProductPhoto> productPhotoList = iProductPhotoRepository.findAllByProduct(product.get());
            List<ProductDetailPhoto> productDetailPhotoList = iProductDetailPhotoRepository.findAllByProduct(product.get());

            if(categoryProductList.isPresent() && !productOptions.isEmpty()) {
                iCategoryProductListRepository.deleteByProduct(product.get());

                for(ProductOption temp : productOptions)
                    iProductOptionRepository.deleteById(temp.getId());

                for(ProductPhoto temp : productPhotoList)
                    iProductPhotoRepository.deleteById(temp.getId());

                for(ProductDetailPhoto temp : productDetailPhotoList)
                    iProductDetailPhotoRepository.deleteById(temp.getId());
            }
            iProductRepository.deleteById(id);
        }
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

    @Override
    public List<ProductAllListDto> getAllProductList() {
        List<Product> productList = iProductRepository.findAll();
        List<ProductAllListDto> allList = new ArrayList<>();


        for(Product product : productList) {
            Long newPrice = 0L;
            Long oldPrice = 0L;
            Float reviewScore = iReviewRepository.getReviewAvgScore(product.getId());
            int reviewNum = iReviewRepository.getReviewCountByProduct(product.getId());

            int discountRate = product.getDiscountRate();
            if(discountRate != 0) {
                oldPrice = product.getPrice();
                newPrice = (long) ((float) oldPrice * (1 - ((float) discountRate /100 )));
            }
            else
                newPrice= product.getPrice();

            allList.add(ProductAllListDto.builder()
                    .id(product.getId())
                    .thumbnailUri(product.getThumbnail())
                    .mallName("신세계몰")
                    .productName(product.getProductName())
                    .manufactureCompany(product.getManufactureCompany())
                    .discountRate(product.getDiscountRate())
                    .oldPrice(oldPrice)
                    .newPrice(newPrice)
                    .reviewScore(reviewScore == null ? 0 : reviewScore)
                    .reviewNum(reviewNum)
                    .fee(product.getFee())
                    .adultCase(product.isAdultCase())
                    .build());
        }

        return allList;
    }

    @Override
    public ProductDetailDto getProductDetail(Long productId) {

        Optional<Product> product = iProductRepository.findById(productId);

        Long newPrice = 0L;
        Long oldPrice = 0L;
        Float reviewScore = iReviewRepository.getReviewAvgScore(product.get().getId());
        int reviewNum = iReviewRepository.getReviewCountByProduct(product.get().getId());

        int discountRate = product.get().getDiscountRate();
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
                    .userId(review.getUser().getUserId().substring(3)+"******")
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
                    .userId(qnA.getUser().getId())
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
                .QnaList(qnAOutputDtoList)
                .build();
    }
}
