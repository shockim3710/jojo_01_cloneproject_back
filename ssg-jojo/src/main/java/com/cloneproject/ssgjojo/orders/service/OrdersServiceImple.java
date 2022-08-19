package com.cloneproject.ssgjojo.orders.service;

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
public class OrdersServiceImple implements IOrdersService{

    private final IOrdersRepository iOrdersRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final IDeliveryAddressRepository iDeliveryAddressRepository;

    private final IOrdersProductListRepository iOrdersProductListRepository;

    @Override
    public OrdersAddDto addOrders(OrdersAddDto ordersAddDto) {
        Optional<User> user = iUserRepository.findById(ordersAddDto.getUser());
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(ordersAddDto.getDeliveryAddress());

        if (user.isPresent() && deliveryAddress.isPresent()) {

            ordersAddDto.setWhetherExchange(false);

            Orders orders = iOrdersRepository.save(Orders.builder()
                            .ordersPrice(ordersAddDto.getOrdersPrice())
                            .whetherExchange(ordersAddDto.isWhetherExchange())
                            .ordersName(ordersAddDto.getOrdersName())
                            .ordersPhone(ordersAddDto.getOrdersPhone())
                            .ordersEmail(ordersAddDto.getOrdersEmail())
                            .deliveryDate(ordersAddDto.getDeliveryDate())
                            .deliveryRequest(ordersAddDto.getDeliveryRequest())
                            .user(user.get())
                            .deliveryAddress(deliveryAddress.get())
                            .build());

            List<OrdersProductListAddDto> listAddDto = new ArrayList<>();
            for(OrdersProductListAddDto ordersProductListAddDto : ordersAddDto.getOrdersProductListAddDtoList()) {
                Optional<Product> product = iProductRepository.findById(ordersProductListAddDto.getProduct());
                Optional<ProductOption> productOption = iProductOptionRepository.findById(ordersProductListAddDto.getProductOption());

                ordersProductListAddDto.setWhetherRefund(false);

                OrdersProductList temp = iOrdersProductListRepository.save(OrdersProductList.builder()
                        .count(ordersProductListAddDto.getCount())
                        .whetherRefund(ordersProductListAddDto.isWhetherRefund())
                        .orders(orders)
                        .product(product.get())
                        .productOption(productOption.get())
                        .build());

                listAddDto.add(OrdersProductListAddDto.builder()
                        .count(temp.getCount())
                        .product(temp.getProduct().getId())
                        .productName(product.get().getProductName())
                        .manufactureCompany(product.get().getManufactureCompany())
                        .thumbnail(product.get().getThumbnail())
                        .productOption(temp.getProductOption().getId())
                        .productOption1Contents(productOption.get().getProductOption1Contents())
                        .productOption2Contents(productOption.get().getProductOption2Contents())
                        .orders(orders.getId())
                        .build()
                );
            }

            return OrdersAddDto.builder()
                    .ordersPrice(orders.getOrdersPrice())
                    .whetherExchange(orders.isWhetherExchange())
                    .ordersName(orders.getOrdersName())
                    .ordersPhone(orders.getOrdersPhone())
                    .ordersEmail(orders.getOrdersEmail())
                    .deliveryDate(orders.getDeliveryDate())
                    .deliveryRequest(orders.getDeliveryRequest())
                    .user(user.get().getId())
                    .name(user.get().getName())
                    .phone(user.get().getPhone())
                    .email(user.get().getEmail())
                    .deliveryAddress(deliveryAddress.get().getId())
                    .address(deliveryAddress.get().getAddress())
                    .ordersProductListAddDtoList(listAddDto)
                    .build();

        }

        return null;
    }

    @Override
    public List<OrdersGetIdDto> getOrdersByUserId(Long id) {

        Optional<User> userOptional = iUserRepository.findById(id);

        if (userOptional.isPresent()) {

            List<Orders> ordersList = iOrdersRepository.findAllByUser(userOptional.get());
            List<OrdersGetIdDto> ordersGetIdDtoList = new ArrayList<>();

            ordersList.forEach(user -> {



                Optional<Orders> ordersOptional = iOrdersRepository.findById(user.getId());

//                if(ordersOptional.isPresent()) {
                    List<OrdersProductList> ordersProductLists = iOrdersProductListRepository.findAllByOrders(ordersOptional.get());
                    List<OrdersProductListGetIdEditDto> listGetIdDto = new ArrayList<>();

                    ordersProductLists.forEach(order -> {
                        listGetIdDto.add(OrdersProductListGetIdEditDto.builder()
                                .id(order.getId())
                                .count(order.getCount())
                                .whetherRefund(order.isWhetherRefund())
                                .orders(order.getOrders().getId())
                                .product(order.getProduct().getId())
                                .productName(order.getProduct().getProductName())
                                .manufactureCompany(order.getProduct().getManufactureCompany())
                                .thumbnail(order.getProduct().getThumbnail())
                                .productOption(order.getProductOption().getId())
                                .productOption1Contents(order.getProductOption().getProductOption1Contents())
                                .productOption2Contents(order.getProductOption().getProductOption2Contents())
                                .build());

                    });
//                }

                ordersGetIdDtoList.add(OrdersGetIdDto.builder()
                        .id(user.getId())
                        .ordersPrice(user.getOrdersPrice())
                        .ordersName(user.getOrdersName())
                        .ordersPhone(user.getOrdersPhone())
                        .ordersEmail(user.getOrdersEmail())
                        .deliveryDate(user.getDeliveryDate())
                        .deliveryRequest(user.getDeliveryRequest())
                        .user(user.getUser().getId())
                        .deliveryAddress(user.getDeliveryAddress().getId())
                        .address(user.getDeliveryAddress().getAddress())
                        .ordersProductListGetIdDtoListEdit(listGetIdDto)
                        .build());

            });

            return ordersGetIdDtoList;
        }

        return null;
    }

    @Override
    public OrdersEditGetAllDto editOrders(OrdersEditGetAllDto ordersEditGetAllDto) {

        Optional<Orders> orders = iOrdersRepository.findById(ordersEditGetAllDto.getId());
        Optional<User> user = iUserRepository.findById(ordersEditGetAllDto.getUser());
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(ordersEditGetAllDto.getDeliveryAddress());

        if(orders.isPresent() && user.isPresent() && deliveryAddress.isPresent()) {
            ordersEditGetAllDto.setWhetherExchange(true);

            Orders temp = iOrdersRepository.save(Orders.builder()
                            .id(ordersEditGetAllDto.getId())
                            .ordersPrice(ordersEditGetAllDto.getOrdersPrice())
                            .whetherExchange(ordersEditGetAllDto.isWhetherExchange())
                            .ordersName(ordersEditGetAllDto.getOrdersName())
                            .ordersPhone(ordersEditGetAllDto.getOrdersPhone())
                            .ordersEmail(ordersEditGetAllDto.getOrdersEmail())
                            .deliveryDate(ordersEditGetAllDto.getDeliveryDate())
                            .deliveryRequest(ordersEditGetAllDto.getDeliveryRequest())
                            .user(user.get())
                            .deliveryAddress(deliveryAddress.get())
                            .build());

            List<OrdersProductListGetIdEditDto> listEditDto = new ArrayList<>();
            for (OrdersProductListGetIdEditDto ordersProductListGetIdEditDto : ordersEditGetAllDto.getOrdersProductListGetIdDtoListEdit()) {
                Optional<OrdersProductList> ordersProductListOptional = iOrdersProductListRepository.findById(ordersProductListGetIdEditDto.getId());
                Optional<Product> product = iProductRepository.findById(ordersProductListGetIdEditDto.getProduct());
                Optional<ProductOption> productOption = iProductOptionRepository.findById(ordersProductListGetIdEditDto.getProductOption());

                OrdersProductList ordersProductList = iOrdersProductListRepository.save(OrdersProductList.builder()
                                .id(ordersProductListGetIdEditDto.getId())
                                .count(ordersProductListGetIdEditDto.getCount())
                                .whetherRefund(ordersProductListGetIdEditDto.isWhetherRefund())
                                .orders(temp)
                                .product(product.get())
                                .productOption(productOption.get())
                                .build());

                listEditDto.add(OrdersProductListGetIdEditDto.builder()
                                .id(ordersProductList.getId())
                                .count(ordersProductList.getCount())
                                .product(ordersProductList.getProduct().getId())
                                .productName(product.get().getProductName())
                                .manufactureCompany(product.get().getManufactureCompany())
                                .thumbnail(product.get().getThumbnail())
                                .productOption(ordersProductList.getProductOption().getId())
                                .productOption1Contents(productOption.get().getProductOption1Contents())
                                .productOption2Contents(productOption.get().getProductOption2Contents())
                                .orders(temp.getId())
                                .build());
            }

            return OrdersEditGetAllDto.builder()
                    .id(temp.getId())
                    .ordersPrice(temp.getOrdersPrice())
                    .whetherExchange(temp.isWhetherExchange())
                    .ordersName(temp.getOrdersName())
                    .ordersPhone(temp.getOrdersPhone())
                    .ordersEmail(temp.getOrdersEmail())
                    .deliveryDate(temp.getDeliveryDate())
                    .deliveryRequest(temp.getDeliveryRequest())
                    .user(user.get().getId())
                    .name(user.get().getName())
                    .phone(user.get().getPhone())
                    .email(user.get().getEmail())
                    .deliveryAddress(deliveryAddress.get().getId())
                    .address(deliveryAddress.get().getAddress())
                    .ordersProductListGetIdDtoListEdit(listEditDto)
                    .build();
        }

        return null;
    }

    @Override
    public void deleteOrders(Long id) {
        Optional<Orders> orders = iOrdersRepository.findById(id);

        if(orders.isPresent()) {
            iOrdersRepository.deleteById(id);
        }

    }
}
