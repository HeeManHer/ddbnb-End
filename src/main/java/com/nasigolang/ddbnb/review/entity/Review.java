package com.nasigolang.ddbnb.review.entity;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;


@Entity(name = "Review")  //엔티티매니저가 관리하기 위한 엔티티객체
@Table(name = "review")  //어떠한 데이터베이스의 테이블과 매핑할 것인지 지정
@SequenceGenerator(
        name = "review_sequence_generator",
        sequenceName = "sequence_review_id",
        initialValue = 1,
        allocationSize = 50
)
public class Review{

    @Id //리뷰 코드가 primary key
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_sequence_generator"
    )
    @Column(name = "REVIEW_ID")
    private long reviewId;

    @Column(name = "REVIEW_TITLE")
    private String reviewTitle;

    @Column(name = "MEMBER_ID")
    private long memberId;

    @Column(name = "REVIEWER_ID")
    private long reviewerId;

    @Column(name = "REVIEW_DETAIL")
    private String reviewDetail;

    @Column(name = "REVIEW_WRITE_DATE")
    private java.util.Date reviewWriteDate;

    @Column(name = "REVIEW_STAR_POINT")
    private String reviewStarPoint;

    @Lob // Large object 데이터 타입으로 지정
    @Column(name = "REVIEW_IMAGE")
    private byte[] reviewImage;

    public Review(long reviewId, String reviewTitle, long memberId, long reviewerId, String reviewDetail, Date reviewWriteDate, String reviewStarPoint, byte[] reviewImage) {

        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.memberId = memberId;
        this.reviewerId = reviewerId;
        this.reviewDetail = reviewDetail;
        this.reviewWriteDate = reviewWriteDate;
        this.reviewStarPoint = reviewStarPoint;
        this.reviewImage = reviewImage;
    }
    public Review() {}

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

    public Date getReviewWriteDate() {
        return reviewWriteDate;
    }

    public void setReviewWriteDate(Date reviewWriteDate) {
        this.reviewWriteDate = reviewWriteDate;
    }

    public String getReviewStarPoint() {
        return reviewStarPoint;
    }

    public void setReviewStarPoint(String reviewStarPoint) {
        this.reviewStarPoint = reviewStarPoint;
    }

    public byte[] getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(byte[] reviewImage) {
        this.reviewImage = reviewImage;
    }


    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", memberId=" + memberId +
                ", reviewerId=" + reviewerId +
                ", reviewDetail='" + reviewDetail + '\'' +
                ", reviewWriteDate=" + reviewWriteDate +
                ", reviewStarPoint='" + reviewStarPoint + '\'' +
                ", reviewImage=" + Arrays.toString(reviewImage) +
                '}';
    }
}