package com.cloneproject.ssgjojo.ordersproductlist.service;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.repository.IOrdersRepository;
import com.cloneproject.ssgjojo.ordersproductlist.domain.OrdersProductList;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListAddDto;
import com.cloneproject.ssgjojo.ordersproductlist.dto.OrdersProductListGetIdDto;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersProductListServiceImple implements IOrdersProductListService {

    private final IOrdersProductListRepository iOrdersProductListRepository;
    private final IProductRepository iProductRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final IOrdersRepository iOrdersRepository;


    @Override
    public OrdersProductListAddDto addOrdersProductList(OrdersProductListAddDto ordersProductListAddDto) {
        Optional<Product> product = iProductRepository.findById(ordersProductListAddDto.getProduct());
        Optional<ProductOption> productOption = iProductOptionRepository.findById(ordersProductListAddDto.getProductOption());
        Optional<Orders> orders = iOrdersRepository.findById(ordersProductListAddDto.getOrders());


        if(product.isPresent() && productOption.isPresent()) {
            ordersProductListAddDto.setWhetherRefund(false);

            OrdersProductList temp = iOrdersProductListRepository.save(OrdersProductList.builder()
                            .count(ordersProductListAddDto.getCount())
                            .whetherRefund(ordersProductListAddDto.isWhetherRefund())
                            .orders(orders.get())
                            .product(product.get())
                            .productOption(productOption.get())
                            .build());

            return OrdersProductListAddDto.builder()
                    .count(temp.getCount())
                    .whetherRefund(temp.isWhetherRefund())
                    .orders(temp.getOrders().getId())
                    .product(temp.getProduct().getId())
                    .productName(temp.getProduct().getProductName())
                    .manufactureCompany(temp.getProduct().getManufactureCompany())
//                    .thumbnail(temp.getProduct().getThumbnail())
                    .productOption(temp.getProductOption().getId())
                    .productOption1Contents(temp.getProductOption().getProductOption1Contents())
                    .productOption2Contents(temp.getProductOption().getProductOption2Contents())
                    .build();
        }

        return null;
    }

    @Override
    public List<OrdersProductListGetIdDto> getOrdersProductListByOrdersId(Long id) {
        Optional<Orders> ordersOptional = iOrdersRepository.findById(id);

        if(ordersOptional.isPresent()) {
            List<OrdersProductList> ordersProductLists = iOrdersProductListRepository.findAllByOrders(ordersOptional.get());
            List<OrdersProductListGetIdDto> ordersProductListGetIdDtoList = new ArrayList<>();

            ordersProductLists.forEach(orders -> {
                ordersProductListGetIdDtoList.add(OrdersProductListGetIdDto.builder()
                                .id(orders.getId())
                                .count(orders.getCount())
                                .whetherRefund(orders.isWhetherRefund())
                                .orders(orders.getOrders().getId())
                                .product(orders.getProduct().getId())
                                .productName(orders.getProduct().getProductName())
                                .manufactureCompany(orders.getProduct().getManufactureCompany())
//                                .thumbnail(orders.getProduct().getThumbnail())
                                .productOption(orders.getProductOption().getId())
                                .productOption1Contents(orders.getProductOption().getProductOption1Contents())
                                .productOption2Contents(orders.getProductOption().getProductOption2Contents())
                                .build());
            });

            return ordersProductListGetIdDtoList;

//            return ordersProductLists.stream().map(ordersProductList -> {
//                return OrdersProductListGetIdDto.builder()
//                        .id(ordersProductList.getId())
//                        .count(ordersProductList.getCount())
//                        .whetherRefund(ordersProductList.isWhetherRefund())
//                        .product(ordersProductList.getProduct().getId())
//                        .productName(ordersProductList.getProduct().getProductName())
//                        .manufactureCompany(ordersProductList.getProduct().getManufactureCompany())
////                                .thumbnail(orders.getProduct().getThumbnail())
//                        .productOption(ordersProductList.getProductOption().getId())
//            }).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public void deleteOrdersProductList(Long id) {
        Optional<OrdersProductList> ordersProductList = iOrdersProductListRepository.findById(id);

        if(ordersProductList.isPresent()) {
            iOrdersProductListRepository.deleteById(id);
        }
    }
}
