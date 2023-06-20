package com.nasigolang.ddbnb.Pet.petmom.service;


import com.nasigolang.ddbnb.Pet.petmom.dto.ApplicantDTO;
import com.nasigolang.ddbnb.Pet.petmom.entity.MomApplicant;
import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.Pet.petmom.repositroy.MomApplicantRepository;
import com.nasigolang.ddbnb.Pet.petmom.repositroy.PetMomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MomApplicantService {

    private final MomApplicantRepository momApplicantRepository;
    private final ModelMapper modelMapper;
    public Page<ApplicantDTO> findMomApplicantList(Pageable page, int boardId) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));
        return momApplicantRepository.findByBoardId(page,boardId).map(list -> modelMapper.map(list, ApplicantDTO.class));
    }


    public void registNewApplicant(ApplicantDTO newApplicant) {
        momApplicantRepository.save(modelMapper.map(newApplicant, MomApplicant.class));
    }
}

