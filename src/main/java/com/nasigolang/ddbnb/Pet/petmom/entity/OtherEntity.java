package com.nasigolang.ddbnb.Pet.petmom.entity;

import com.nasigolang.ddbnb.Pet.Board;
import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="Other")
@Table(name = "other")
@SequenceGenerator(
        name="other_sequence_generator",
        sequenceName = "sequence_other_id",
        initialValue = 1,
        allocationSize = 50
)
public class OtherEntity implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "other_sequence_generator"
    )

   @Column(name="OTHER")
    private String other;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board boardId;
}

