package com.nasigolang.ddbnb.review.dto;

import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReviewDTO {

    private long reviewId;
    private String reviewTitle;

    private MemberSimpleDTO member;
    private MemberSimpleDTO reviewer;

    private String reviewDetail;
    private Date reviewWriteDate;
    private int reviewStarPoint;

    private String reviewImageUrl;
}