package com.nasigolang.ddbnb.Pet.petmom.repositroy;

import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface PetMomRepository extends JpaRepository<PetMom, Integer> {





//    Page<PetMom> findPetMomByLocationOrStartDateOrEndDateOrPetYNOrOther(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean isPetYN, String other);


}
