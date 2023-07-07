package com.nasigolang.ddbnb.pet.petmom.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "MomApplicant")
@Table(name = "MOM_APPLICANT")
@SequenceGenerator(name = "applicant_sequence_generator", sequenceName = "sequence_applicant_id", initialValue = 1, allocationSize = 50)
public class MomApplicant {

    @Id
    @Column(name = "APPLICANT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicant_sequence_generator")
    private int applicantId;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID", referencedColumnName = "BOARD_ID")
    private PetMom boardId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;

    @Column(name = "APPLIED_DATE")
    private Date appliedDate;

}
