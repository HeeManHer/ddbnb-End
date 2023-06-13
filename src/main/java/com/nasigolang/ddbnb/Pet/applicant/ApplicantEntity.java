package com.nasigolang.ddbnb.Pet.applicant;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "APPLICANT")
@Table(name = "Applicant")
@SequenceGenerator(
        name = "applicant_sequence_generator",
        sequenceName = "sequence_applicant_id",
        initialValue = 1,
        allocationSize = 50
)
public class ApplicantEntity{

    @Id
    @Column(name = "JOIN_MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicant_sequence_generator")
    private int joinMemberId;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PETMOM_ID")
    private PetMom petMom;

}
