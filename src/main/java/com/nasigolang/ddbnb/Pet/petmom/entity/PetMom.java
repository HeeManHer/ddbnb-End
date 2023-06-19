package com.nasigolang.ddbnb.Pet.petmom.entity;


import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "PetMom")
@Table(name = "PET_MOM")
public class PetMom {

    @Column(name = "HOURLY_RATE")
    private int hourlyRate; // 시간 당 요금`

    @Column(name = "DATE_RATE")
    private int dateRate;  // 1일 요금

    @Column(name = "HOUSE_TYPE")
    private String houseType; // 주거형태

    @Column(name = "PET_YN")
    private boolean petYN; // 펫 유무
    @Id
    @Column(name = "BOARD_ID")
    private int boardId;

    @Column(name = "BOARD_TITLE")
    private String boardTitle;

    @Column(name = "BOARD_CATEGORY")
    private String boardCategory;

    @Column(name = "BOARD_DATE")
    private LocalDate boardDate;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "CARE")
    private String care;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "SIGNFICANT")
    private String signficant; // 특이사항

    @Column(name = "REQUEST")
    private String request; // 요청사항

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member memberId;

    @ManyToMany
    @JoinTable(name = "OTHER", joinColumns = @JoinColumn(name = "BOARD_ID"), inverseJoinColumns = @JoinColumn(name = "TYPE_ID"))
    private List<OtherType> otherCondition = new ArrayList<>();
}
