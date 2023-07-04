package com.nasigolang.ddbnb.pet.petmom.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "PetMom")
@Table(name = "PET_MOM")
@SequenceGenerator(name = "petMom_sequence_generator", sequenceName = "sequence_petMom_id", initialValue = 1, allocationSize = 50)
public class PetMom {

    @Id
    @Column(name = "BOARD_ID")
    @Comment("번호")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petMom_sequence_generator")
    private long boardId;


    @Column(name = "HOURLY_RATE")
    @Comment("시간 당 요금")
    private int hourlyRate;

    @Column(name = "DATE_RATE")
    @Comment("1일 요금")
    private int dateRate;

    @Column(name = "HOUSE_TYPE")
    @Comment("주거 형태")
    private String houseType;

    @Column(name = "PET_YN")
    @Comment("펫 유무")
    private String petYN;


    @Column(name = "BOARD_TITLE")
    @Comment("제목")
    private String boardTitle;

    @Column(name = "BOARD_CATEGORY")
    @Comment("카테고리")
    private String boardCategory;

    @Column(name = "BOARD_DATE")
    @Comment("작성일")
    private Date boardDate;

    @Column(name = "LOCATION")
    @Comment("장소")
    private String location;

    @Column(name = "CARE")
    @Comment("돌봄 형태")
    private String care;

    @Column(name = "START_DATE")
    @Comment("시작일")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    @Comment("종료일")
    private LocalDate endDate;

    @Column(name = "SIGNFICANT")
    @Comment("특이사항")
    private String signficant;

    @Column(name = "REQUEST")
    @Comment("요쳥사항")
    private String request;

    @Column(name = "MOM_STATUS", columnDefinition = "VARCHAR2(255) DEFAULT '모집 중'")
    @Comment("상태")
    private String momStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    @Comment("작성자")
    private Member member;

    @ManyToMany
    @JoinTable(
            name = "OTHER",
            joinColumns = @JoinColumn(name = "BOARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "TYPE_ID")
    )
    @Comment("기타 조건")
    private List<OtherType> otherCondition = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "boardId")
    private List<BoardImage> boardImage;

    public void setMomStatus(String momStatus) {
        if (momStatus != null) {
            this.momStatus = momStatus;
        } else {
            this.momStatus = "모집 중";
        }
    }

}

