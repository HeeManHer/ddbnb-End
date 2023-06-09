package com.nasigolang.ddbnb.Pet;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.Pet.Board;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Applicant")
@Table(name = "APPLICANT")
@SequenceGenerator(
        name = "applicant_sequence_generator",
        sequenceName = "sequence_applicant_id",
        initialValue = 1,
        allocationSize = 50
)
public class ApplicantEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member memberId;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board boardId;
}
