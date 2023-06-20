package com.nasigolang.ddbnb.pet.applicant;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ModelMapper modelMapper;
    public Page<com.nasigolang.ddbnb.Pet.applicant.ApplicantDTO> findApplicantList(Pageable page, long boardId) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));

        return applicantRepository.findByBoardId(
                page, boardId).map(list -> modelMapper.map(list, com.nasigolang.ddbnb.Pet.applicant.ApplicantDTO.class));
    }

}

