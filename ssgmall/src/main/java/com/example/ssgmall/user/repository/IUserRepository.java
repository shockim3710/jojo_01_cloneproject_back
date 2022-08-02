
// Repository : Entity에 의해 생성된 DB에 접근하는 메서드(ex) findAll())들을 사용하기 위한 인터페이스
// CRUD를 어떻게 할 것인가에 대해 정의해주는 계층
package com.example.ssgmall.user.repository;

import com.example.ssgmall.user.domain.User;
// JpaRepository를 상속받도록 함으로써 기본적인 동작이 모두 가능
import org.springframework.data.jpa.repository.JpaRepository;


// extends를 통해서 상속
// 해당 Repository의 객체를 이용해서 기본적으로 제공되는 메서드(save(), findAll(), get()) 등을 사용 가능
public interface IUserRepository extends JpaRepository<User, Long> {
}
