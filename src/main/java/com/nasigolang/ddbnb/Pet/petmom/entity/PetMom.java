package com.nasigolang.ddbnb.Pet.petmom.entity;

import com.nasigolang.ddbnb.Pet.ApplicantEntity;
import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "PETMOM")
@Entity(name = "PetMom")
@SequenceGenerator(name = "PETMOM_SEQ_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_PETMOM_ID")
public class PetMom {
    @Id
    @Column(name = "PETMOM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PETMOM_SEQ_GENERATOR")
    private int petMomId;

    @Column(name = "PETMOM_TITLE")
    private String petMomTitle;

    @Column(name = "PETMOM_CATEGORY")
    private String petMomCategory;

    @Column(name = "PETMOM_DATE",columnDefinition = "DATE DEFAULT SYSDATE")
    @Temporal(TemporalType.DATE)
    private Date petMomDate;


    @Column(name = "HOURLY_RATE")
    private int hourlyRate;

    @Column(name = "DATE_RATE")
    private int dateRate;

    @Column(name= "HOUSE_TYPE")
    private  String houseType;

    @Column(name = "PET_YN")
    private boolean petYN;

    @Column(name ="LOCATION")
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

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "IMG")
    private byte[] img;

   @JoinColumn(name = "MEMBER_ID")
   @ManyToOne
   private Member memberId;


    @OneToMany(mappedBy = "petMom")
    private List<ApplicantEntity> applicants;

    @OneToMany
    private List<OtherEntity> other;



}
