package com.nasigolang.ddbnb.Pet.petsitter.service;

import com.nasigolang.ddbnb.Pet.petsitter.dto.PetsitterboardDTO;
import com.nasigolang.ddbnb.Pet.petsitter.entity.PetsitterEntity;
import com.nasigolang.ddbnb.Pet.petsitter.repository.PetsitterRepository;
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
public class PetsitterService {

    private final PetsitterRepository petSitterRepository;
    private final ModelMapper modelMapper;

    public Page<PetsitterboardDTO> findMenuList(Pageable page, String location, String petSize, String care, LocalDate startDate, LocalDate endDate) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));

        return petSitterRepository.findPetsitterEntityByLocationAndPetSizeAndCareAndStartDateAndEndDate(
                page, location, petSize, care, startDate, endDate).map(list -> modelMapper.map(list, PetsitterboardDTO.class));
    }







    @Transactional
    public void registPetSitter(PetsitterboardDTO petSitter) {
        petSitterRepository.save(modelMapper.map(petSitter, PetsitterEntity.class));
    }

    @Transactional
    public void modifyPetSitter(PetsitterboardDTO modifypetsitter) {

        PetsitterEntity foundPetsitter = petSitterRepository.findById(modifypetsitter.getBoardId()).get();

        foundPetsitter.setBoardTitle(modifypetsitter.getBoardTitle());
        foundPetsitter.setLocation(modifypetsitter.getLocation());
        foundPetsitter.setCare(modifypetsitter.getCare());
        foundPetsitter.setStartDate(modifypetsitter.getStartDate());
        foundPetsitter.setEndDate(modifypetsitter.getEndDate());
        foundPetsitter.setSignficant(modifypetsitter.getSignficant());
        foundPetsitter.setRequest(modifypetsitter.getRequest());
        foundPetsitter.setPetName(modifypetsitter.getPetName());
        foundPetsitter.setPetAge(modifypetsitter.getPetAge());
        foundPetsitter.setPetShape(modifypetsitter.getPetShape());
        foundPetsitter.setPetGender(modifypetsitter.getPetGender());
        foundPetsitter.setPetSize(modifypetsitter.getPetSize());

    }

    @Transactional
    public void deletePetSitter(Long borderId) {
        petSitterRepository.deleteById(borderId);
    }
}
