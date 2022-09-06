package com.cloneproject.ssgjojo.attention.domain;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * id : Attention PK
 * product : 상품
 * user : 유저
 * attentionFolder : 좋아요 폴더
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @ManyToOne
    private AttentionFolder attentionFolder;
}