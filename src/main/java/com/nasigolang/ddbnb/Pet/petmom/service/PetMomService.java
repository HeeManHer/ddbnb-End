package com.nasigolang.ddbnb.Pet.petmom.service;

import com.nasigolang.ddbnb.Pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.Pet.petmom.repositroy.PetMomRepository;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class PetMomService {

    private final PetMomRepository petMomRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public void registNewPetMom(PetMomDTO newPetmom) {
        petMomRepository.save(modelMapper.map(newPetmom, PetMom.class));
    }

    public Page<PetMomDTO> findAllPetMoms(Pageable page) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("memberId"));

        return petMomRepository.findAll(page).map(petMom -> modelMapper.map(petMom, PetMomDTO.class));
    }


//    public Page<PetMomDTO> findPetMom(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other) {
//        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("memberId"));
//        return petMomRepository.findBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(page, location, startDate, endDate, petYN, other).map(list -> modelMapper.map(list, PetMomDTO.class));
//    }
}


