package com.cloneproject.ssgjojo.attentionfolder.repository;

import com.cloneproject.ssgjojo.attentionfolder.domain.AttentionFolder;
import com.cloneproject.ssgjojo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttentionFolderRepository extends JpaRepository<AttentionFolder, Long> {
    List<AttentionFolder> findAllByUserOrderByNo(User user);
}
