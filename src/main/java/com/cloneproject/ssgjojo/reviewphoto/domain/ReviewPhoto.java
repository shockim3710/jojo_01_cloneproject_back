package com.cloneproject.ssgjojo.reviewphoto.domain;

import com.cloneproject.ssgjojo.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reviewPhotoPath;

    @Column(nullable = false)
    private String reviewPhotoOriginName;

    @ManyToOne
    private Review review;

}
