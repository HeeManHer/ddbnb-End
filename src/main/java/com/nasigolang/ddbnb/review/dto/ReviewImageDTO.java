package com.nasigolang.ddbnb.review.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReviewImageDTO {

    private long imgId;
    private String imageUrl;
    private long reviewId;
}
