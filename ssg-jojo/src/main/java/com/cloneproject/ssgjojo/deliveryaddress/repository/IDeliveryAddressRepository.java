package com.cloneproject.ssgjojo.deliveryaddress.repository;

import com.cloneproject.ssgjojo.cart.domain.Cart;
import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.qna.domain.QnA;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> findAllByUserOrderByWhetherDefaultAddressDesc(User user);

    Optional<DeliveryAddress> findByWhetherDefaultAddressAndWhetherOnlyThisTimeAndUserId(boolean whetherDefaultAddress, boolean whetherOnlyThisTime, Long userId);
    Optional<DeliveryAddress> findByWhetherDefaultAddressAndUserId(boolean whetherDefaultAddress, Long userId);
    Optional<DeliveryAddress> findByWhetherOnlyThisTimeAndUserId(boolean whetherOnlyThisTime, Long userId);


}
