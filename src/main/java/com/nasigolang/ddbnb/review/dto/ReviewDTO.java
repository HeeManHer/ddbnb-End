package com.nasigolang.ddbnb.review.dto;

import java.util.Arrays;
import java.util.Date;

public class ReviewDTO {

    private long reviewId;

    private String reviewTitle;

    private long memberId;

    private long reviewerId;
    private String reviewDetail;

    private String reviewStarPoint;
    private long rallyId;

    private java.util.Date reviewWriteDate;

    private byte[] reviewImage;
    public ReviewDTO() {}

    public ReviewDTO(long reviewId, String reviewTitle, long memberId, long reviewerId, String reviewDetail, String reviewStarPoint, long rallyId, Date reviewWriteDate, byte[] reviewImage) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.memberId = memberId;
        this.reviewerId = reviewerId;
        this.reviewDetail = reviewDetail;
        this.reviewStarPoint = reviewStarPoint;
        this.rallyId = rallyId;
        this.reviewWriteDate = reviewWriteDate;
        this.reviewImage = reviewImage;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public String getReviewStarPoint() {
        return reviewStarPoint;
    }

    public void setReviewStarPoint(String reviewStarPoint) {
        this.reviewStarPoint = reviewStarPoint;
    }

    public long getRallyId() {
        return rallyId;
    }

    public void setRallyId(long rallyId) {
        this.rallyId = rallyId;
    }

    public Date getReviewWriteDate() {
        return reviewWriteDate;
    }

    public void setReviewWriteDate(Date reviewWriteDate) {
        this.reviewWriteDate = reviewWriteDate;
    }

    public byte[] getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(byte[] reviewImage) {
        this.reviewImage = reviewImage;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", memberId=" + memberId +
                ", reviewerId=" + reviewerId +
                ", reviewDetail='" + reviewDetail + '\'' +
                ", reviewStarPoint='" + reviewStarPoint + '\'' +
                ", rallyId=" + rallyId +
                ", reviewWriteDate=" + reviewWriteDate +
                ", reviewImage=" + Arrays.toString(reviewImage) +
                '}';
    }
}
