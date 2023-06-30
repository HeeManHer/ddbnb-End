package com.nasigolang.ddbnb.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date reviewWriteDate;
    private int reviewStarPoint;

    private List<ReviewImageDTO> reviewImage;
}
