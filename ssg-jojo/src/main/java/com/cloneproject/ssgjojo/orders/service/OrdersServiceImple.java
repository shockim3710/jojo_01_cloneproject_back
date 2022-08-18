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

            OrdersAddDto addDto = OrdersAddDto.builder()
                    .ordersPrice(orders.getOrdersPrice())
                    .whetherExchange(orders.isWhetherExchange())
                    .ordersName(orders.getOrdersName())
                    .ordersPhone(orders.getOrdersPhone())
                    .ordersEmail(orders.getOrdersEmail())
                    .deliveryDate(orders.getDeliveryDate())
                    .deliveryRequest(orders.getDeliveryRequest())
                    .user(orders.getUser().getId())
                    .name(orders.getUser().getName())
                    .phone(orders.getUser().getPhone())
                    .email(orders.getUser().getEmail())
                    .deliveryAddress(orders.getDeliveryAddress().getId())
                    .address(orders.getDeliveryAddress().getAddress())
                    .build();

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
                        .productOption(temp.getProductOption().getId())
                        .productOption1Contents(productOption.get().getProductOption1Contents())
                        .productOption2Contents(productOption.get().getProductOption2Contents())
                        .orders(orders.getId())
                        .build()
                );


                /*OrdersProductListAddDto.builder()
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
                        .build();*/
            }

//            return orders;

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

//            return OrdersAddDto.builder()
//                    .ordersPrice(temp.getOrdersPrice())
//                    .whetherExchange(temp.isWhetherExchange())
//                    .ordersName(temp.getOrdersName())
//                    .ordersPhone(temp.getOrdersPhone())
//                    .ordersEmail(temp.getOrdersEmail())
//                    .deliveryDate(temp.getDeliveryDate())
//                    .deliveryRequest(temp.getDeliveryRequest())
//                    .user(temp.getUser().getId())
//                    .name(temp.getUser().getName())
//                    .phone(temp.getUser().getPhone())
//                    .email(temp.getUser().getEmail())
//                    .deliveryAddress(temp.getDeliveryAddress().getId())
//                    .address(temp.getDeliveryAddress().getAddress())
//                    .build();
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
                                .build());
            });

            return ordersGetIdDtoList;
        }

        return null;
    }

    @Override
    public OrdersEditGetAllDto editOrders(OrdersEditGetAllDto ordersEditGetAllDto) {

        Optional<Orders> orders = iOrdersRepository.findById(ordersEditGetAllDto.getId());
        Optional<User> user = iUserRepository.findById(ordersEditGetAllDto.getId());
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(ordersEditGetAllDto.getId());

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
                            .user(iUserRepository.findById(ordersEditGetAllDto.getUser()).get())
                            .deliveryAddress(iDeliveryAddressRepository.findById(ordersEditGetAllDto.getDeliveryAddress()).get())
                            .build());

            return OrdersEditGetAllDto.builder()
                    .id(temp.getId())
                    .ordersPrice(temp.getOrdersPrice())
                    .whetherExchange(temp.isWhetherExchange())
                    .ordersName(temp.getOrdersName())
                    .ordersPhone(temp.getOrdersPhone())
                    .ordersEmail(temp.getOrdersEmail())
                    .deliveryDate(temp.getDeliveryDate())
                    .deliveryRequest(temp.getDeliveryRequest())
                    .user(temp.getUser().getId())
                    .name(temp.getUser().getName())
                    .phone(temp.getUser().getPhone())
                    .email(temp.getUser().getEmail())
                    .deliveryAddress(temp.getDeliveryAddress().getId())
                    .address(temp.getDeliveryAddress().getAddress())
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
