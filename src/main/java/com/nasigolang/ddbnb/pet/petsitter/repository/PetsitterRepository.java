package com.nasigolang.ddbnb.pet.petsitter.repository;

import com.nasigolang.ddbnb.pet.petsitter.entity.PetsitterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PetsitterRepository extends JpaRepository<PetsitterEntity, Long> {



    Page <PetsitterEntity> findByLocationContainingAndPetSizeContainingAndCareContainingAndStartDateContainingAndEndDateContainingAndSitterStatus(Pageable page, String location, String petSize, String care, LocalDate startDate, LocalDate endDate, String sitterStatus);

}
