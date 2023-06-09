package com.nasigolang.ddbnb.Pet;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "BOARD")
@Entity(name = "board")
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "SEQ_BOARD_ID"
)
public class Board implements Serializable {

    @Id
    @Column(name="BOARD_ID")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "board_sequence_generator")
    private int borderId;

    @Column(name = "BOARD_TITLE")
    private String boardTitle;

    @Column(name = "BOARD_CATEGORY")
    private String boardCategory;

    @Column(name = "BOARD_DATE",columnDefinition = "DATE DEFAULT SYSDATE")
    @Temporal(TemporalType.DATE)
    private Date boardDate;

    @Column(name = "CARE")
    private String care;

    @Column(name = "START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDAte;

    @Column(name = "SIGNFICANT")
    private String signficant;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "IMG")
    private byte[] img;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member memberId;

}
