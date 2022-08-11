package com.cloneproject.ssgjojo.deliveryAddress.repository;

import com.cloneproject.ssgjojo.deliveryAddress.domain.DeliveryAddress;
import com.cloneproject.ssgjojo.recentSearches.domain.RecentSearches;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    List<DeliveryAddress> findAllByUser(User user);

}
