package com.nasigolang.ddbnb.pet.petmom.service;


import com.nasigolang.ddbnb.pet.petmom.dto.ApplicantDTO;
import com.nasigolang.ddbnb.pet.petmom.entity.MomApplicant;
import com.nasigolang.ddbnb.pet.petmom.repositroy.MomApplicantRepository;
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

//    public Page<ApplicantDTO> findMomApplicantList(Pageable page, long boardId) {
//
//        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));
//        return momApplicantRepository.findByBoardId(page, boardId).map(list -> modelMapper.map(list, ApplicantDTO.class));
//    }


    public void registNewApplicant(ApplicantDTO newApplicant) {
        momApplicantRepository.save(modelMapper.map(newApplicant, MomApplicant.class));
    }
}

