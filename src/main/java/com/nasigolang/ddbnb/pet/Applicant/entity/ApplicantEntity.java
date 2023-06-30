package com.nasigolang.ddbnb.pet.Applicant.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import com.nasigolang.ddbnb.pet.petsitter.entity.PetsitterEntity;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "APPLICANT")
@Entity(name="Applicant")
@SequenceGenerator(
        name="applicant_sequence_generator",
        sequenceName = "sequence_applicant_id",
        initialValue = 1,
        allocationSize = 50
)
public class ApplicantEntity implements Serializable {

    @Id
    @Column(name="APPLICANT_ID")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "applicant_sequence_generator")
    private long applicantId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;


    @ManyToOne
    @JoinColumn(name="BOARD_ID", referencedColumnName = "BOARD_ID")
    private PetsitterEntity boardId;
}
