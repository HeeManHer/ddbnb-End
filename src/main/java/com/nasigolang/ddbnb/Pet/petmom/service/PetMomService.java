package com.nasigolang.ddbnb.Pet.petmom.service;

import com.nasigolang.ddbnb.Pet.petmom.dto.OtherDTO;
import com.nasigolang.ddbnb.Pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.Pet.petmom.repositroy.PetMomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));

        return petMomRepository.findAll(page).map(petMom -> modelMapper.map(petMom, PetMomDTO.class));

    }
    @Transactional
    public void modifypetMom(PetMomDTO modifypetmom) {

        PetMom foundPetMom = petMomRepository.findById(modifypetmom.getPetMomId()).get();

        foundPetMom.setPetMomTitle((modifypetmom.getPetMomTitle()));
        foundPetMom.setLocation(modifypetmom.getLocation());
//        foundPetMom.setPetYN(modifypetmom.getPetYN());
        foundPetMom.setPetMomCategory(modifypetmom.getPetMomCategory());
        foundPetMom.setCare(modifypetmom.getCare());
        foundPetMom.setEndDate(modifypetmom.getEndDate());
        foundPetMom.setDateRate(modifypetmom.getDateRate());
        foundPetMom.setHourlyRate(modifypetmom.getHourlyRate());
//        foundPetMom.setOther(modifypetmom.getother());
        foundPetMom.setRequest(modifypetmom.getRequest());
        foundPetMom.setImg(modifypetmom.getImg());
        foundPetMom.setSignficant(modifypetmom.getSignficant());
        foundPetMom.setStartDate(modifypetmom.getStartDate());
        foundPetMom.setPetMomCategory(modifypetmom.getPetMomCategory());
        foundPetMom.setHouseType(modifypetmom.getHouseType());
    }

    public void deletePetMom(int petMomId) { petMomRepository.deleteById(petMomId);
    }


//    public Page<PetMomDTO> findPetMom(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean isPetYN, String other) {
//        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("memberId"));
//        return petMomRepository.findPetMomByLocationOrStartDateOrEndDateOrPetYNOrOther(page, location, startDate, endDate, isPetYN, other).map(list -> modelMapper.map(list, PetMomDTO.class));
//    }
}


