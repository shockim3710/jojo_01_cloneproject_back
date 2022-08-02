package com.example.ssgmall.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor    // 모든 필드 값을 파라미터로 받는 생성자 생성
@Builder               // 해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정
@NoArgsConstructor     // 파라미터가 없는 기본 생성자 생성
@Entity                // @Entity 어노테이션을 클래스에 선언하면 그 클래스는 JPA가 관리
                       // B의 테이블과 Class(VO, DTO)와 맵핑한다면 반드시 붙여줘야 함
public class User {
    @Id                                                      // JPA가 객체를 관리할 때 식별할 기본키를 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)      // primary key 값을 위한 자동 생성 전략 -> strategy, generator
                                                             // MYSQL은 IDENTITY, ORACLE은 SEQUENCE
    // 각 엔티티 클래스마다 독립적으로 id가 auto_increment 되어짐
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String address;
}
