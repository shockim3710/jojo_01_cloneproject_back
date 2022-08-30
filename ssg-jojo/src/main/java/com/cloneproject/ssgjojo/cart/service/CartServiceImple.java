package com.cloneproject.ssgjojo.cart.service;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.dto.CartEditGetIdDto;
import com.cloneproject.ssgjojo.cart.repository.ICartRepository;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import com.cloneproject.ssgjojo.cartproductlist.repository.ICartProductListRepository;
import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
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
    private final IDeliveryAddressRepository iDeliveryAddressRepository;

    private final ICartProductListRepository iCartProductListRepository;


    @Override
    public CartAddDto addCart(CartAddDto cartAddDto) {
        Optional<User> user = iUserRepository.findById(cartAddDto.getUser());
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(cartAddDto.getDeliveryAddress());

        if(user.isPresent() && deliveryAddress.isPresent()) {

            Cart cart = iCartRepository.save(Cart.builder()
                            .user(user.get())
                            .deliveryAddress(deliveryAddress.get())
                            .build());

            List<CartProductListAddDto> listAddDto = new ArrayList<>();
            for(CartProductListAddDto cartProductListAddDto : cartAddDto.getCartProductListAddDtoList()) {
                Optional<Product> product = iProductRepository.findById(cartProductListAddDto.getProduct());
                Optional<ProductOption> productOption = iProductOptionRepository.findById(cartProductListAddDto.getProductOption());

                CartProductList temp = iCartProductListRepository.save(CartProductList.builder()
                                .cartCount(cartProductListAddDto.getCartCount())
                                .cart(cart)
                                .product(product.get())
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

            return CartAddDto.builder()
                    .user(user.get().getId())
                    .deliveryAddress(deliveryAddress.get().getId())
                    .address(deliveryAddress.get().getAddress())
                    .cartProductListAddDtoList(listAddDto)
                    .build();
        }

        return null;
    }

    @Override
    public List<CartEditGetIdDto> getCartByUserId(Long id) {
        Optional<User> userOptional = iUserRepository.findById(id);

        if(userOptional.isPresent()) {

            List<Cart> cartList = iCartRepository.findAllByUser(userOptional.get());
            List<CartEditGetIdDto> cartEditGetIdDtoList = new ArrayList<>();

            cartList.forEach(user -> {

                Optional<Cart> cartOptional = iCartRepository.findById(user.getId());

//                if(cartOptional.isPresent()) {
                    List<CartProductList> cartProductLists = iCartProductListRepository.findAllByCart(cartOptional.get());
                    List<CartProductListGetIdEditDto> listGetIdDto = new ArrayList<>();

                    cartProductLists.forEach(cart -> {
                        Product product = cart.getProduct();
                        Long newPrice = 0L;
                        Long oldPrice = 0L;
                        int discountRate = product.getDiscountRate();

                        if(discountRate != 0) {
                            oldPrice = product.getPrice();
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
//                }

                cartEditGetIdDtoList.add(CartEditGetIdDto.builder()
                                .id(user.getId())
                                .user(user.getUser().getId())
                                .deliveryAddress(user.getDeliveryAddress().getId())
                                .address(user.getDeliveryAddress().getAddress())
                                .cartProductListGetIdEditDtoList(listGetIdDto)
                                .build());
            });

            return cartEditGetIdDtoList;
        }

        return null;
    }

    @Override
    public CartEditGetIdDto editCart(CartEditGetIdDto cartEditGetIdDto) {

        Optional<Cart> cart = iCartRepository.findById(cartEditGetIdDto.getId());
        Optional<User> user = iUserRepository.findById(cartEditGetIdDto.getUser());
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(cartEditGetIdDto.getDeliveryAddress());

        if(cart.isPresent() && user.isPresent() && deliveryAddress.isPresent()) {

            Cart temp = iCartRepository.save(Cart.builder()
                            .id(cartEditGetIdDto.getId())
                            .user(user.get())
                            .deliveryAddress(deliveryAddress.get())
                            .build());

            List<CartProductListGetIdEditDto> listEditDto = new ArrayList<>();
            for (CartProductListGetIdEditDto cartProductListGetIdEditDto : cartEditGetIdDto.getCartProductListGetIdEditDtoList()) {
                Optional<CartProductList> cartProductListOptional = iCartProductListRepository.findById(cartProductListGetIdEditDto.getId());
                Optional<Product> product = iProductRepository.findById(cartProductListGetIdEditDto.getProduct());
                Optional<ProductOption> productOption = iProductOptionRepository.findById(cartProductListGetIdEditDto.getProductOption());

                CartProductList cartProductList = iCartProductListRepository.save(CartProductList.builder()
                                .id(cartProductListGetIdEditDto.getId())
                                .cartCount(cartProductListGetIdEditDto.getCartCount())
                                .cart(temp)
                                .product(product.get())
                                .productOption(productOption.get())
                                .build());

                listEditDto.add(CartProductListGetIdEditDto.builder()
                                .id(cartProductList.getId())
                                .cartCount(cartProductList.getCartCount())
                                .cart(temp.getId())
                                .product(cartProductList.getProduct().getId())
                                .productName(product.get().getProductName())
                                .manufactureCompany(product.get().getManufactureCompany())
                                .thumbnail(product.get().getThumbnail())
                                .price(product.get().getPrice())
                                .discountRate(product.get().getDiscountRate())
                                .fee(product.get().getFee())
                                .productOption(cartProductList.getProductOption().getId())
                                .productOption1Contents(productOption.get().getProductOption1Contents())
                                .productOption2Contents(productOption.get().getProductOption2Contents())
                                .stock(productOption.get().getStock())
                                .build());
            }

            return CartEditGetIdDto.builder()
                    .id(temp.getId())
                    .user(user.get().getId())
                    .deliveryAddress(deliveryAddress.get().getId())
                    .address(deliveryAddress.get().getAddress())
                    .cartProductListGetIdEditDtoList(listEditDto)
                    .build();
        }

        return null;
    }

    @Override
    public void deleteCart(Long id) {
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
