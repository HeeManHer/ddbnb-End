package com.nasigolang.ddbnb.pet.petmom.service;

import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.pet.petmom.entity.OtherType;
import com.nasigolang.ddbnb.pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.pet.petmom.repositroy.PetMomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetMomService {

    private final PetMomRepository petMomRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    private long boardId;

    public PetMomService(PetMomRepository petMomRepository, MemberRepository memberRepository,
                         ModelMapper modelMapper) {
        this.petMomRepository = petMomRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void registNewPetMom(PetMomDTO newPetmom) {
        petMomRepository.save(modelMapper.map(newPetmom, PetMom.class));
    }

    public Page<PetMomDTO> findAllPetMoms(Pageable page) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("boardId"));

        //        System.out.println(petMomRepository.findById(3));
        return petMomRepository.findAll(page).map(petMoms -> modelMapper.map(petMoms, PetMomDTO.class));

    }

    @Transactional
    public void modifyPetMom(PetMomDTO modifyPetMom) {

        PetMom foundPetMom = petMomRepository.findById((long) modifyPetMom.getBoardId()).get();

        foundPetMom.setHouseType(modifyPetMom.getHouseType());
        foundPetMom.setPetYN(modifyPetMom.getPetYN());
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
    public void deletePetMom(long borderId) {
        petMomRepository.deleteById(borderId);

    }

    public PetMomDTO findPetMomByBoardNo(long boardId) {
        return petMomRepository.findById(boardId).map(petMomboard -> modelMapper.map(petMomboard, PetMomDTO.class)).orElseThrow(() -> new NoSuchElementException("펫시터를 찾을 수 없습니다."));

    }


    //내 펫맘 조회
    public Page<PetMomDTO> findMyPetMom(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("boardId"));

        //        Page<Review> reviews = reviewRepository.findAll(pageable);
        return petMomRepository.findByMember(page, memberRepository.findById(memberId)).map(petMom -> modelMapper.map(petMom, PetMomDTO.class));
    }


    @Transactional
    public void updateMomCancle(PetMomDTO momCancle, long boardId) {
        PetMom petMom = petMomRepository.findById(boardId).get();

        petMom.setMomStatus(momCancle.getMomStatus());

    }



    //    public Page<PetMomDTO> findPetMom(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other) {
    //        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("memberId"));
    //        return petMomRepository.findBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(page, location, startDate, endDate, petYN, other).map(list -> modelMapper.map(list, PetMomDTO.class));
    //    }
}

