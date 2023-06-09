package com.nasigolang.ddbnb.Pet.petsitter.entity;

import com.nasigolang.ddbnb.Pet.Board;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="Petsitter")
@Table(name = "petsitter")
@SequenceGenerator(
        name="petsitter_sequence_generator",
        sequenceName = "sequence_board_id",
        initialValue = 1,
        allocationSize = 50
)
public class PetsitterEntity implements Serializable {

    @Id
    @JoinColumn(name="BOARD_ID")
    @ManyToOne
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "board_sequence_generator")
    private Board borderId;

    @Column(name = "RATE")
    private int rate;

    @Column(name = "PET_NAME")
    private String petName;

    @Column(name = "PET_AGE")
    private int petAge;

    @Column(name = "PET_SHAPE")
    private String petShape;

    @Column(name = "PET_GENDER")
    private String petGender;

    @Column(name = "PET_SIZE")
    private String petSize;


}
