package com.cloneproject.ssgjojo.attentionfolder.domain;

import com.cloneproject.ssgjojo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * id: AttentionFolder PK
 * no: 폴더 번호
 * fodlerName: 폴더 이름
 * user : 사용자
 */
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
