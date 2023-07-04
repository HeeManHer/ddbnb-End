package com.nasigolang.ddbnb.pet.petmom.service;


import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.pet.petmom.dto.ApplicantDTO;
import com.nasigolang.ddbnb.pet.petmom.entity.MomApplicant;
import com.nasigolang.ddbnb.pet.petmom.repositroy.MomApplicantRepository;
import com.nasigolang.ddbnb.pet.petmom.repositroy.PetMomRepository;
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
    private final MemberRepository memberRepository;
    private final PetMomRepository petMomRepository;

    public Page<ApplicantDTO> findMomApplicantList(Pageable page, long boardId) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId"));
        return momApplicantRepository.findByBoardId(page, petMomRepository.findById(boardId)).map(list -> modelMapper.map(list, ApplicantDTO.class));
    }


    public void registNewApplicant(ApplicantDTO newApplicant) {
        momApplicantRepository.save(modelMapper.map(newApplicant, MomApplicant.class));
    }

    public  Page<ApplicantDTO> findMyPetMomApp(Pageable page, long memberId){
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId"));

        return momApplicantRepository.findByMember(page, memberRepository.findById(memberId))
                .map(petMom -> modelMapper.map(petMom, ApplicantDTO.class));
    }

}

