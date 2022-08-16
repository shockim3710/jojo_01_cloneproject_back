package com.cloneproject.ssgjojo.orders.service;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.deliveryaddress.repository.IDeliveryAddressRepository;
import com.cloneproject.ssgjojo.orders.domain.Orders;
import com.cloneproject.ssgjojo.orders.dto.OrdersAddDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersEditGetAllDto;
import com.cloneproject.ssgjojo.orders.dto.OrdersGetIdDto;
import com.cloneproject.ssgjojo.orders.repository.IOrdersRepository;
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

    @Override
    public OrdersAddDto addOrders(OrdersAddDto ordersAddDto) {
        Optional<User> user = iUserRepository.findById(ordersAddDto.getUser());
        Optional<Product> product = iProductRepository.findById(ordersAddDto.getProduct());
        Optional<ProductOption> productOption = iProductOptionRepository.findById(ordersAddDto.getProductOption());
        Optional<DeliveryAddress> deliveryAddress = iDeliveryAddressRepository.findById(ordersAddDto.getDeliveryAddress());

        if (user.isPresent() && product.isPresent() && productOption.isPresent() && deliveryAddress.isPresent()) {

            ordersAddDto.setWhetherExchange(false);
            ordersAddDto.setWhetherRefund(false);

            Orders temp = iOrdersRepository.save(Orders.builder()
                            .count(ordersAddDto.getCount())
                            .ordersPrice(ordersAddDto.getOrderPrice())
                            .whetherExchange(ordersAddDto.isWhetherExchange())
                            .whetherRefund(ordersAddDto.isWhetherRefund())
                            .ordersName(ordersAddDto.getOrderName())
                            .ordersPhone(ordersAddDto.getOrderPhone())
                            .ordersEmail(ordersAddDto.getOrdersEmail())
                            .deliveryDate(ordersAddDto.getDeliveryDate())
                            .deliveryRequest(ordersAddDto.getDeliveryRequest())
                            .user(user.get())
                            .product(product.get())
                            .productOption(productOption.get())
                            .deliveryAddress(deliveryAddress.get())
                            .build());

            return OrdersAddDto.builder()
                    .count(temp.getCount())
                    .orderPrice(temp.getOrdersPrice())
                    .whetherExchange(temp.isWhetherExchange())
                    .whetherRefund(temp.isWhetherRefund())
                    .orderName(temp.getOrdersName())
                    .orderPhone(temp.getOrdersPhone())
                    .ordersEmail(temp.getOrdersEmail())
                    .deliveryDate(temp.getDeliveryDate())
                    .deliveryRequest(temp.getDeliveryRequest())
                    .user(temp.getUser().getId())
                    .name(temp.getUser().getName())
                    .phone(temp.getUser().getPhone())
                    .email(temp.getUser().getEmail())
                    .product(temp.getProduct().getId())
                    .productName(temp.getProduct().getProductName())
                    .manufactureCompany(temp.getProduct().getManufactureCompany())
//                    .thumbnail(temp.getProduct().getThumbnail())
                    .deliveryAddress(temp.getDeliveryAddress().getId())
                    .address(temp.getDeliveryAddress().getAddress())
                    .productOption(temp.getProductOption().getId())
                    .productOption1Contents(temp.getProductOption().getProductOption1Contents())
                    .productOption2Contents(temp.getProductOption().getProductOption2Contents())
                    .build();
        }

        return null;
    }

    @Override
    public List<OrdersGetIdDto> getOrdersByUserId(Long id) {
        Optional<User> userOptional = iUserRepository.findById(id);
        Optional<Product> productOptional = iProductRepository.findById(id);
        Optional<ProductOption> productOptionOptional = iProductOptionRepository.findById(id);

        if (userOptional.isPresent() && productOptional.isPresent() && productOptionOptional.isPresent()) {
            List<Orders> ordersList = iOrdersRepository.findAllByUser(userOptional.get());
            List<OrdersGetIdDto> ordersGetIdDtoList = new ArrayList<>();

            ordersList.forEach(user -> {
                ordersGetIdDtoList.add(OrdersGetIdDto.builder()
                                .id(user.getId())
                                .count(user.getCount())
                                .orderPrice(user.getOrdersPrice())
                                .whetherRefund(user.isWhetherRefund())
                                .orderName(user.getOrdersName())
                                .orderPhone(user.getOrdersPhone())
                                .ordersEmail(user.getOrdersEmail())
                                .deliveryDate(user.getDeliveryDate())
                                .deliveryRequest(user.getDeliveryRequest())
                                .user(user.getUser().getId())
                                .product(user.getProduct().getId())
                                .productOption(user.getProductOption().getId())
                                .build());
            });

            return ordersGetIdDtoList;
        }

        return null;
    }

    @Override
    public OrdersEditGetAllDto editOrders(OrdersEditGetAllDto ordersEditGetAllDto) {
        return null;
    }

    @Override
    public void deleteDeliveryAddress(Long id) {

    }
}
