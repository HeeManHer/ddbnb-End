package com.nasigolang.ddbnb.review.entity;


import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "Review")  //엔티티매니저가 관리하기 위한 엔티티객체
@Table(name = "review")  //어떠한 데이터베이스의 테이블과 매핑할 것인지 지정
@SequenceGenerator(name = "review_sequence_generator", sequenceName = "seq_review_id", initialValue = 1, allocationSize = 50)
public class Review {

    @Id //리뷰 코드가 primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence_generator")
    @Column(name = "REVIEW_ID")
    private long reviewId;

    @Column(name = "REVIEW_TITLE")
    private String reviewTitle;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "REVIEWER_ID", referencedColumnName = "MEMBER_ID")
    private Member reviewer;

    @Column(name = "REVIEW_DETAIL")
    private String reviewDetail;

    @Column(name = "REVIEW_WRITE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewWriteDate;

    @Column(name = "REVIEW_STAR_POINT")
    private int reviewStarPoint;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "reviewId")
    private List<ReviewImage> reviewImage;

}