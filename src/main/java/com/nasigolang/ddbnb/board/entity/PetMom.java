package com.nasigolang.ddbnb.board.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "PetMom")
@Table(name = "PET_MOM")
public class PetMom extends Board {

    @Column(name = "HOURLY_RATE")
    @Comment("시간 당 요금")
    private int hourlyRate;

    @Column(name = "DATE_RATE")
    @Comment("1일 요금")
    private int dateRate;

    @Column(name = "HOUSE_TYPE")
    @Comment("주거형태")
    private String houseType;

    @Column(name = "PET_YN")
    @Comment("펫 유뮤")
    private String petYN;

    @ManyToMany
    @JoinTable(name = "OTHER", joinColumns = @JoinColumn(name = "BOARD_ID"), inverseJoinColumns = @JoinColumn(name = "TYPE_ID"))
    @Comment("기타 조건")
    private List<OtherType> otherCondition = new ArrayList<>();
}
