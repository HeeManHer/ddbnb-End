package com.nasigolang.ddbnb.pet.Applicant.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

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

    @JoinColumn(name="MEMBER_ID")
    @ManyToOne
    private Member memberId;

    @Column(name="BOARD_ID")
    private long boardId;
}
