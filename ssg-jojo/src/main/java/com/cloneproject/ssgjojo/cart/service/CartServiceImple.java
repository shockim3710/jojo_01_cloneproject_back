package com.cloneproject.ssgjojo.cart.service;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.dto.CartEditGetIdDto;
import com.cloneproject.ssgjojo.cart.repository.ICartRepository;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import com.cloneproject.ssgjojo.cartproductlist.repository.ICartProductListRepository;
import com.cloneproject.ssgjojo.coupon.domain.Coupon;
import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
import com.cloneproject.ssgjojo.jwt.JwtTokenProvider;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.repository.IOrdersRepository;
import com.cloneproject.ssgjojo.ordersproductlist.domain.OrdersProductList;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdEditDto;
import com.cloneproject.ssgjojo.ordersproductlist.repository.IOrdersProductListRepository;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.product.repository.IProductRepository;
import com.cloneproject.ssgjojo.productoption.domain.ProductOption;
import com.cloneproject.ssgjojo.productoption.repository.IProductOptionRepository;
import com.cloneproject.ssgjojo.user.domain.User;
import com.cloneproject.ssgjojo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImple implements ICartService {

    private final ICartRepository iCartRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final ICartProductListRepository iCartProductListRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public List<CartProductListAddDto> addCart(CartAddDto cartAddDto, HttpServletRequest request) {
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        Optional<User> user = iUserRepository.findById(Long.valueOf(userId));

        if(user.isPresent()) {
            Optional<Cart> tempCart = iCartRepository.findByUserId(userId);
            Cart cart = new Cart();

            if(!tempCart.isPresent()) {
                cart = iCartRepository.save(Cart.builder()
                        .user(user.get())
                        .build());
            } else {
                cart = tempCart.get();
            }

            List<CartProductListAddDto> listAddDto = new ArrayList<>();
            for(CartProductListAddDto cartProductListAddDto : cartAddDto.getCartProductListAddDtoList()) {
                Optional<Product> product = iProductRepository.findById(cartProductListAddDto.getProduct());
                Optional<ProductOption> productOption = iProductOptionRepository.findById(cartProductListAddDto.getProductOption());

                List<CartProductList> tmp = iCartProductListRepository.findProductOptionIdAndCartId(productOption.get().getId(), cart.getId());

                if(tmp.size() == 0) {
                    CartProductList temp = iCartProductListRepository.save(CartProductList.builder()
                            .cartCount(cartProductListAddDto.getCartCount())
                            .product(product.get())
                            .cart(cart)
                            .productOption(productOption.get())
                            .build());

                    listAddDto.add(CartProductListAddDto.builder()
                            .cartCount(temp.getCartCount())
                            .cart(cart.getId())
                            .product(temp.getProduct().getId())
                            .productName(temp.getProduct().getProductName())
                            .manufactureCompany(temp.getProduct().getManufactureCompany())
                            .thumbnail(temp.getProduct().getThumbnail())
                            .price(temp.getProduct().getPrice())
                            .discountRate(temp.getProduct().getDiscountRate())
                            .fee(temp.getProduct().getFee())
                            .productOption(temp.getProductOption().getId())
                            .productOption1Contents(temp.getProductOption().getProductOption1Contents())
                            .productOption2Contents(temp.getProductOption().getProductOption2Contents())
                            .stock(temp.getProductOption().getStock())
                            .build());
                }
                else {
                    CartProductList cpl = tmp.get(0);
                    cpl.setCartCount(cpl.getCartCount() + cartProductListAddDto.getCartCount());
                }
            }

            return listAddDto;
        }

        return null;
    }

    @Override
    public List<CartProductListGetIdEditDto> getCartByUserId(HttpServletRequest request) {

        String userId = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        Optional<User> userOptional = iUserRepository.findById(Long.valueOf(userId));

        List<CartProductListGetIdEditDto> listGetIdDto = new ArrayList<>();


        if(userOptional.isPresent()) {

            Optional<Cart> cartOptional = iCartRepository.findByUserId(userOptional.get().getId());

            if(cartOptional.isPresent()) {

                List<CartProductList> cartProductLists = iCartProductListRepository.findAllByCart(cartOptional.get());


                cartProductLists.forEach(cart -> {
                    Product product = cart.getProduct();
                    Long newPrice = 0L;
                    Long oldPrice = product.getPrice();
                    int discountRate = product.getDiscountRate();

                    if(discountRate != 0) {
                        newPrice = (long) ((float) oldPrice * (1 - ((float) discountRate /100 )));
                    }
                    else
                        newPrice= product.getPrice();

                    listGetIdDto.add(CartProductListGetIdEditDto.builder()
                            .id(cart.getId())
                            .cartCount(cart.getCartCount())
                            .cart(cart.getCart().getId())
                            .product(cart.getProduct().getId())
                            .productName(cart.getProduct().getProductName())
                            .manufactureCompany(cart.getProduct().getManufactureCompany())
                            .thumbnail(cart.getProduct().getThumbnail())
                            .newPrice(newPrice)
                            .oldPrice(oldPrice)
                            .discountRate(cart.getProduct().getDiscountRate())
                            .fee(cart.getProduct().getFee())
                            .productOption(cart.getProductOption().getId())
                            .productOption1Contents(cart.getProductOption().getProductOption1Contents())
                            .productOption2Contents(cart.getProductOption().getProductOption2Contents())
                            .stock(cart.getProductOption().getStock())
                            .build());
                });

                return listGetIdDto;
            }
        }

        return listGetIdDto;
    }

    @Override
    @Transactional
    public String editCart(CartProductListGetIdEditDto cartProductListGetIdEditDto) {

        Optional<CartProductList> cartProductListOptional = iCartProductListRepository.findById(cartProductListGetIdEditDto.getId());

        if(cartProductListGetIdEditDto.getPlma().equals("plus")) {
            cartProductListOptional.get().setCartCount(cartProductListOptional.get().getCartCount() + 1);
        } else if (cartProductListGetIdEditDto.getPlma().equals("minus")) {
            cartProductListOptional.get().setCartCount(cartProductListOptional.get().getCartCount() - 1);
        }

        return "변경되었습니다.";
    }

//    @Override
//    public CartEditGetIdDto editCart(CartEditGetIdDto cartEditGetIdDto) {
//
//        Optional<Cart> cart = iCartRepository.findById(cartEditGetIdDto.getId());
//        Optional<User> user = iUserRepository.findById(cartEditGetIdDto.getUser());
//        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(cartEditGetIdDto.getDeliveryAddress());
//
//        if(cart.isPresent() && user.isPresent() && deliveryAddress.isPresent()) {
//
//            Cart temp = iCartRepository.save(Cart.builder()
//                            .id(cartEditGetIdDto.getId())
//                            .user(user.get())
//                            .deliveryAddress(deliveryAddress.get())
//                            .build());
//
//            List<CartProductListGetIdEditDto> listEditDto = new ArrayList<>();
//            for (CartProductListGetIdEditDto cartProductListGetIdEditDto : cartEditGetIdDto.getCartProductListGetIdEditDtoList()) {
//                Optional<CartProductList> cartProductListOptional = iCartProductListRepository.findById(cartProductListGetIdEditDto.getId());
//                Optional<Product> product = iProductRepository.findById(cartProductListGetIdEditDto.getProduct());
//                Optional<ProductOption> productOption = iProductOptionRepository.findById(cartProductListGetIdEditDto.getProductOption());
//
//                //                productOption.get().setStock(productOption.get().getStock()-1);
////                userEditDto.setIsLeave(false);
//
////                if(cartProductListGetIdEditDto.getPlma() == "plus") {
////                    cartProductListGetIdEditDto.setCartCount(cartProductListOptional.get().getCartCount() + cartProductListGetIdEditDto.getCnt());
////
////                } else if (cartProductListGetIdEditDto.getPlma() == "minus") {
////                    cartProductListGetIdEditDto.setCartCount(cartProductListOptional.get().getCartCount() - cartProductListGetIdEditDto.getCnt());
////
////                }
//
//                CartProductList cartProductList = iCartProductListRepository.save(CartProductList.builder()
//                                .id(cartProductListGetIdEditDto.getId())
//                                .cartCount(cartProductListGetIdEditDto.getCartCount())
//                                .cart(temp)
//                                .product(product.get())
//                                .productOption(productOption.get())
//                                .build());
//
//                listEditDto.add(CartProductListGetIdEditDto.builder()
//                                .id(cartProductList.getId())
//                                .cartCount(cartProductList.getCartCount())
//                                .cart(temp.getId())
//                                .product(cartProductList.getProduct().getId())
//                                .productName(product.get().getProductName())
//                                .manufactureCompany(product.get().getManufactureCompany())
//                                .thumbnail(product.get().getThumbnail())
//                                .price(product.get().getPrice())
//                                .discountRate(product.get().getDiscountRate())
//                                .fee(product.get().getFee())
//                                .productOption(cartProductList.getProductOption().getId())
//                                .productOption1Contents(productOption.get().getProductOption1Contents())
//                                .productOption2Contents(productOption.get().getProductOption2Contents())
//                                .stock(productOption.get().getStock())
//                                .build());
//            }
//
//            return CartEditGetIdDto.builder()
//                    .id(temp.getId())
//                    .user(user.get().getId())
//                    .deliveryAddress(deliveryAddress.get().getId())
//                    .address(deliveryAddress.get().getAddress())
//                    .cartProductListGetIdEditDtoList(listEditDto)
//                    .build();
//        }
//
//        return null;
//    }

    @Override
    public void deleteCart(Long id) {

        Optional<CartProductList> cartProductList = iCartProductListRepository.findById(id);

        if(cartProductList.isPresent()) {
            iCartProductListRepository.deleteById(id);

            List<CartProductList> cartProductListOptional = iCartProductListRepository.findByCartId(cartProductList.get().getCart().getId());

            if(cartProductListOptional.isEmpty()) {
                iCartRepository.deleteById(cartProductList.get().getCart().getId());
            }
        }
    }

    @Override
    public void deleteCartAll(Long id) {
        Optional<Cart> cart = iCartRepository.findById(id);

        if(cart.isPresent()) {

            List<CartProductList> cartProductLists = iCartProductListRepository.findAllByCart(cart.get());

            for (CartProductList temp : cartProductLists) {
                iCartProductListRepository.deleteById(temp.getId());
            }

            iCartRepository.deleteById(id);
        }

    }
}
