
package com.nasigolang.ddbnb.Pet.petmom.service;

import com.nasigolang.ddbnb.Pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.Pet.petmom.entity.OtherType;
import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.Pet.petmom.repositroy.PetMomRepository;

import com.nasigolang.ddbnb.Pet.petsitter.dto.PetsitterboardDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


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

//        System.out.println(petMomRepository.findById(3));
        return petMomRepository.findAll(page).map(petMom -> modelMapper.map(petMom, PetMomDTO.class));

    }

    @Transactional
    public void modifyPetMom(PetMomDTO modifyPetMom) {

        PetMom foundPetMom = petMomRepository.findById(modifyPetMom.getBoardId()).get();

        foundPetMom.setHouseType(modifyPetMom.getHouseType());
        foundPetMom.setPetYN(modifyPetMom.isPetYN());
        foundPetMom.setStartDate(modifyPetMom.getStartDate());
        foundPetMom.setRequest(modifyPetMom.getRequest());
        foundPetMom.setSignficant(modifyPetMom.getSignficant());
        foundPetMom.setCare(modifyPetMom.getCare());
        foundPetMom.setDateRate(modifyPetMom.getDateRate());
        foundPetMom.setLocation(modifyPetMom.getLocation());
        foundPetMom.setEndDate(modifyPetMom.getEndDate());
        foundPetMom.setHourlyRate(modifyPetMom.getHourlyRate());
        foundPetMom.setBoardCategory(modifyPetMom.getBoardCategory());
        foundPetMom.setBoardTitle(modifyPetMom.getBoardTitle());
        foundPetMom.setOtherCondition(modifyPetMom.getOtherCondition().stream().map(list -> modelMapper.map(list, OtherType.class)).collect(Collectors.toList()));
    }
    @Transactional
    public void deletePetMom(int borderId) { petMomRepository.deleteById(borderId);

    }

    public PetMomDTO findPetMomByBoardNo(int boardId) {
        return petMomRepository.findById(boardId)
                .map(petMomboard -> modelMapper.map(petMomboard, PetMomDTO.class))
                .orElseThrow(() -> new NoSuchElementException("펫시터를 찾을 수 없습니다."));

    }


//    public Page<PetMomDTO> findPetMom(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other) {
//        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("memberId"));
//        return petMomRepository.findBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(page, location, startDate, endDate, petYN, other).map(list -> modelMapper.map(list, PetMomDTO.class));
//    }
}


