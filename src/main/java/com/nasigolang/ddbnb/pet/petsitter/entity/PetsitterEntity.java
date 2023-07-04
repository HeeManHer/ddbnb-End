package com.nasigolang.ddbnb.pet.petsitter.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "PETSITTER")
@Entity(name = "Petsitter")
@SequenceGenerator(name = "petsitter_sequence_generator", sequenceName = "sequence_board_id", initialValue = 1, allocationSize = 50)
public class PetsitterEntity implements Serializable {

    @Id
    @Column(name = "BOARD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petsitter_sequence_generator")
    private long boardId;

    @Column(name = "BOARD_TITLE")
    private String boardTitle;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;

    @Column(name = "BOARD_DATE")
    private LocalDate boardDate;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "CARE")
    private String care;

    @Column(name = "START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "SIGNFICANT")
    private String signficant;

    @Column(name = "IMG")
    private byte[] IMG;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "BOARD_CATEGORY")
    private String boardCategory;

    @Column(name = "RATE")
    private int rate;

    @Column(name = "PET_NAME")
    private String petName;

    @Column(name = "PET_AGE")
    private int petAge;

    @Column(name = "PET_SHAPE")
    private String petShape;

    @Column(name = "PET_GENDER")
    private String petGender;

    @Column(name = "PET_SIZE")
    private String petSize;

    @Column(name = "SITTER_STATUS", columnDefinition = "VARCHAR2(255) DEFAULT '모집 중'")
    private String sitterStatus;

    public void setSitterStatus(String sitterStatus) {
        if (sitterStatus != null) {
            this.sitterStatus = sitterStatus;
        } else {
            this.sitterStatus = "모집 중";
        }
    }
}
