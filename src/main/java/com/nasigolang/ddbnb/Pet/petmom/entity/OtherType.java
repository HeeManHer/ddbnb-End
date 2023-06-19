package com.nasigolang.ddbnb.Pet.petmom.entity;



import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "OtherType")
@Table(name = "OTHER_TYPE")
public class OtherType {

    @Id
    @Column(name = "TYPE_ID")
    private int typeId;

    @Column(name = "TYPE_CONTENT")
    private String typeContent;

    @ManyToMany(mappedBy = "otherCondition")
    private List<com.nasigolang.ddbnb.Pet.petmom.entity.PetMom> users = new ArrayList<>();
}
