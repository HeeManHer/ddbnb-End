package com.nasigolang.ddbnb.board.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Board")
@Table(name = "BOARD")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "SEQ_BOARD_ID", initialValue = 1, allocationSize = 50)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "BOARD_ID")
    @Comment("게시판 번호")
    private long boardId;

    @Column(name = "BOARD_TITLE")
    @Comment("제목")
    private String boardTitle;

    @Column(name = "BOARD_CATEGORY")
    @Comment("카테고리")
    private String boardCategory;

    @Column(name = "BOARD_STATUS")
    @Comment("게시글 상태")
    private String boardStatus;

    @Column(name = "BOARD_DATE")
    @Temporal(TemporalType.DATE)
    @Comment("작성일")
    private Date boardDate;

    @Column(name = "LOCATION")
    @Comment("지역")
    private String location;

    @Column(name = "CARE")
    @Comment("돌봄 형식")
    private String care;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    @Comment("시작일")
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    @Comment("종료일")
    private Date endDate;

    @Column(name = "SIGNIFICANT")
    @Comment("특이사항")
    private String significant;

    @Column(name = "REQUEST")
    @Comment("요청사항")
    private String request;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    @JsonBackReference
    @Comment("작성자")
    private Member member;

    @OneToMany(mappedBy = "boardId", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<BoardImage> boardImage;
}
