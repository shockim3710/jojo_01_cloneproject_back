package com.cloneproject.ssgjojo.deliveryaddress.repository;

import com.cloneproject.ssgjojo.deliveryaddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> findAllByUserOrderByWhetherDefaultAddressDesc(User user);
    Optional<DeliveryAddress> findByWhetherDefaultAddressAndUserId(boolean whetherDefaultAddress, Long userId);
    Optional<DeliveryAddress> findByWhetherOnlyThisTimeAndUserId(boolean whetherOnlyThisTime, Long userId);
}
