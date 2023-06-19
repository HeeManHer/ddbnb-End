package com.nasigolang.ddbnb.Pet.petmom.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "Applicant")
@Table(name = "APPLICANT")
public class Applicant {

    @Id
    @Column(name = "APPLICANT_ID")
    private int applicantId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private PetMom petMom;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;
}
