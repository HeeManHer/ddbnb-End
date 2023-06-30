package com.nasigolang.ddbnb.pet.petmom.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "BOARD_ID")
    private long boardId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;


}
