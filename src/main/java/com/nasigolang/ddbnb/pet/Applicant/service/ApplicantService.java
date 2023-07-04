package com.nasigolang.ddbnb.pet.Applicant.service;

import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.pet.Applicant.dto.ApplicantDTO;
import com.nasigolang.ddbnb.pet.Applicant.entity.ApplicantEntity;
import com.nasigolang.ddbnb.pet.Applicant.repository.ApplicantRepository;
import com.nasigolang.ddbnb.pet.petsitter.repository.PetsitterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PetsitterRepository petSitterRepository;


    public Page<ApplicantDTO> findApplicantList(Pageable page, long boardId) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId"));

        return applicantRepository.findByBoardId(page, petSitterRepository.findById(boardId)).map(list -> modelMapper.map(list, ApplicantDTO.class));
    }

    @Transactional
    public void registApplicant(ApplicantDTO applicant) {
        applicantRepository.save(modelMapper.map(applicant, ApplicantEntity.class));
    }

    public Page<ApplicantDTO> findMyPetSitterApp(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId"));

        return applicantRepository.findByMember(page, memberRepository.findById(memberId))
                .map(petSitter -> modelMapper.map(petSitter, ApplicantDTO.class));
    }


}

