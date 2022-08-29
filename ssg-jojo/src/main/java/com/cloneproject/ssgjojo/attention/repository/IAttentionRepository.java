package com.cloneproject.ssgjojo.attention.repository;

import com.cloneproject.ssgjojo.attention.domain.Attention;
import com.cloneproject.ssgjojo.attention.dto.AttentionAddDto;
import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.product.domain.Product;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttentionRepository extends JpaRepository<Attention, Long> {
    List<Attention> findByUserAndProduct(User user, Product product);
    List<Attention> findAllByAttentionFolder(AttentionFolder attentionFolder);
    void deleteAllByProductAndUser( Product product, User user);
}
