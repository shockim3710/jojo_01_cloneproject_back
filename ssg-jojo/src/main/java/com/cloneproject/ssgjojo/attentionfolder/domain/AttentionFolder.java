package com.cloneproject.ssgjojo.attentionfolder.domain;

import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class AttentionFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int no;
    private String folderName;

    @ManyToOne
    private User user;

}
