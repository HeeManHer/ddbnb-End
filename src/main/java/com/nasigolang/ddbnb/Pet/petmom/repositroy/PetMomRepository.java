
package com.nasigolang.ddbnb.Pet.petmom.repositroy;

import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface PetMomRepository extends JpaRepository<PetMom, Integer> {





//    Page<PetMom> findPetBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other);


//    Page<PetMom> findBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other);
}

