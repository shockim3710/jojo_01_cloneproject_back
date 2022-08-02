package com.studybusan.mvc.study.cart.repository;

import com.studybusan.mvc.study.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {
}
