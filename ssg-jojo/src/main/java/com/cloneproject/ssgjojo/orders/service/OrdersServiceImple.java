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
                            .ordersPrice(ordersAddDto.getOrdersPrice())
                            .whetherExchange(ordersAddDto.isWhetherExchange())
                            .whetherRefund(ordersAddDto.isWhetherRefund())
                            .ordersName(ordersAddDto.getOrdersName())
                            .ordersPhone(ordersAddDto.getOrdersPhone())
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
                    .ordersPrice(temp.getOrdersPrice())
                    .whetherExchange(temp.isWhetherExchange())
                    .whetherRefund(temp.isWhetherRefund())
                    .ordersName(temp.getOrdersName())
                    .ordersPhone(temp.getOrdersPhone())
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
        Optional<DeliveryAddress> deliveryAddressOptional = iDeliveryAddressRepository.findById(id);

        if (userOptional.isPresent() && deliveryAddressOptional.isPresent()) {
            List<Orders> ordersList = iOrdersRepository.findAllByUser(userOptional.get());
            List<OrdersGetIdDto> ordersGetIdDtoList = new ArrayList<>();

            ordersList.forEach(user -> {
                ordersGetIdDtoList.add(OrdersGetIdDto.builder()
                                .id(user.getId())
                                .count(user.getCount())
                                .ordersPrice(user.getOrdersPrice())
                                .whetherRefund(user.isWhetherRefund())
                                .ordersName(user.getOrdersName())
                                .ordersPhone(user.getOrdersPhone())
                                .ordersEmail(user.getOrdersEmail())
                                .deliveryDate(user.getDeliveryDate())
                                .deliveryRequest(user.getDeliveryRequest())
                                .user(user.getUser().getId())
                                .product(user.getProduct().getId())
                                .productName(user.getProduct().getProductName())
                                .manufactureCompany(user.getProduct().getManufactureCompany())
//                                .thumbnail(user.getProduct().getThumbnail())
                                .deliveryAddress(user.getDeliveryAddress().getId())
                                .address(user.getDeliveryAddress().getAddress())
                                .productOption(user.getProductOption().getId())
                                .productOption1Contents(user.getProductOption().getProductOption1Contents())
                                .productOption2Contents(user.getProductOption().getProductOption2Contents())
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
                            .count(ordersEditGetAllDto.getCount())
                            .ordersPrice(ordersEditGetAllDto.getOrdersPrice())
                            .whetherExchange(ordersEditGetAllDto.isWhetherExchange())
                            .whetherRefund(ordersEditGetAllDto.isWhetherRefund())
                            .ordersName(ordersEditGetAllDto.getOrdersName())
                            .ordersPhone(ordersEditGetAllDto.getOrdersPhone())
                            .ordersEmail(ordersEditGetAllDto.getOrdersEmail())
                            .deliveryDate(ordersEditGetAllDto.getDeliveryDate())
                            .deliveryRequest(ordersEditGetAllDto.getDeliveryRequest())
                            .user(iUserRepository.findById(ordersEditGetAllDto.getUser()).get())
                            .deliveryAddress(iDeliveryAddressRepository.findById(ordersEditGetAllDto.getDeliveryAddress()).get())
                            .product(iProductRepository.findById(ordersEditGetAllDto.getProduct()).get())
                            .productOption(iProductOptionRepository.findById(ordersEditGetAllDto.getProductOption()).get())
                            .build());

            return OrdersEditGetAllDto.builder()
                    .id(temp.getId())
                    .count(temp.getCount())
                    .ordersPrice(temp.getOrdersPrice())
                    .whetherExchange(temp.isWhetherExchange())
                    .whetherRefund(temp.isWhetherRefund())
                    .ordersName(temp.getOrdersName())
                    .ordersPhone(temp.getOrdersPhone())
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
    public void deleteOrders(Long id) {
        Optional<Orders> orders = iOrdersRepository.findById(id);

        if(orders.isPresent()) {
            iOrdersRepository.deleteById(id);
        }

    }
}
