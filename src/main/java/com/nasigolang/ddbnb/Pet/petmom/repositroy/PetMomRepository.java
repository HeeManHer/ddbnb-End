package com.nasigolang.ddbnb.pet.petmom.repositroy;

import com.nasigolang.ddbnb.pet.petmom.entity.PetMom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetMomRepository extends JpaRepository<PetMom, Integer> {


    //    Page<PetMom> findPetBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other);


    //    Page<PetMom> findBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other);
}

