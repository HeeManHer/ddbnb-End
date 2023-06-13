package com.nasigolang.ddbnb.Pet.petmom.entity;

import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "OTHER")
@Entity(name = "OtherEntity")
public class OtherEntity {

    @Id
    @Column(name = "OTHER_ID")
    private int otherId;

    @Column(name = "OTHER")
    private String other;


}
