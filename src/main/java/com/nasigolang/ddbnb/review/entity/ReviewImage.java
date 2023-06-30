package com.nasigolang.ddbnb.review.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "REVIEW_IMAGE")
@Entity(name = "ReviewImage")
@SequenceGenerator(name = "review_image_sequence_generator", sequenceName = "seq_img_id", initialValue = 1, allocationSize = 50)
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_image_sequence_generator")
    @Column(name = "IMAGE_ID")
    private long imgId;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "REVIEW_ID")
    private long reviewId;
}
