package com.nasigolang.ddbnb.sample.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "MEMBER")
@Entity(name = "Member")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_MEMBER_ID")
public class Sample {
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private int memberId;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date signDate;

    @Column(name = "REPORTED_COUNT")
    private int reportedCount;

}
