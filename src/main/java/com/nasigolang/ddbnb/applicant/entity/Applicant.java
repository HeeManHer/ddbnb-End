package com.nasigolang.ddbnb.applicant.entity;

import com.nasigolang.ddbnb.board.entity.Board;
import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "Applicant")
@Table(name = "APPLICANT")
@SequenceGenerator(name = "APPLICANT_SEQ_GENERATOR", sequenceName = "SEQ_APPLICANT_ID", initialValue = 1, allocationSize = 50)
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICANT_SEQ_GENERATOR")
    @Column(name = "APPLICANT_ID")
    @Comment("신청 번호")
    private long applicantId;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID", referencedColumnName = "BOARD_ID")
    @Comment("게시판 번호")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    @Comment("신청자")
    private Member member;

    @Column(name = "APPLIED_DATE")
    @Comment("신청일")
    private Date appliedDate;

}