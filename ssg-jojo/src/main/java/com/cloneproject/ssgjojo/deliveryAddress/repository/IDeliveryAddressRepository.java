package com.cloneproject.ssgjojo.deliveryAddress.repository;

import com.cloneproject.ssgjojo.deliveryAddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {


}
