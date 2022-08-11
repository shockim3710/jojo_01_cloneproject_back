package com.cloneproject.ssgjojo.attention.repository;

import com.cloneproject.ssgjojo.attention.domain.Attention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttentionRepository extends JpaRepository<Attention, Long> {
}
