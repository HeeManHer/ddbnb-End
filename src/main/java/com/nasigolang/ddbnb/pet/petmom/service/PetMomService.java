package com.nasigolang.ddbnb.pet.petmom.service;

import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.pet.petmom.dto.BoardImageDTO;
import com.nasigolang.ddbnb.pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.pet.petmom.entity.BoardImage;
import com.nasigolang.ddbnb.pet.petmom.entity.OtherType;
import com.nasigolang.ddbnb.pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.pet.petmom.repositroy.PetMomImageRepository;
import com.nasigolang.ddbnb.pet.petmom.repositroy.PetMomMapper;
import com.nasigolang.ddbnb.pet.petmom.repositroy.PetMomRepository;
import com.nasigolang.ddbnb.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetMomService {

    private final PetMomRepository petMomRepository;
    private final MemberRepository memberRepository;
    private final PetMomMapper petMomMapper;
    private final PetMomImageRepository petMomImageRepository;
    private final ModelMapper modelMapper;


    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    public PetMomService(PetMomRepository petMomRepository, MemberRepository memberRepository,
                         PetMomMapper petMomMapper,
                         PetMomImageRepository petMomImageRepository, ModelMapper modelMapper) {
        this.petMomRepository = petMomRepository;
        this.memberRepository = memberRepository;
        this.petMomMapper = petMomMapper;
        this.petMomImageRepository = petMomImageRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void registNewPetMom(PetMomDTO newPetmom, List<MultipartFile> images) {
        long no = petMomRepository.save(modelMapper.map(newPetmom, PetMom.class)).getBoardId();

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                String imageName = UUID.randomUUID().toString().replace("-", "");

                try {
                    String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, images.get(i));

                    BoardImageDTO image = new BoardImageDTO();
                    image.setImageUrl(replaceFileName);
                    image.setBoardId(no);
                    petMomImageRepository.save(modelMapper.map(image, BoardImage.class));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }


    public Page<PetMomDTO> findAllPetMoms(Pageable page, Map<String, Object> searchValue) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("boardId").descending());

        Page<PetMomDTO> petMoms;

        if (searchValue.isEmpty()) {
            petMoms = petMomRepository.findAll(page).map(petMom -> modelMapper.map(petMom, PetMomDTO.class));
        } else {
            List<PetMomDTO> petMomList = petMomMapper.searchPetMom(searchValue);

            petMoms = (Page<PetMomDTO>) Pagenation.createPage(petMomList, page);
        }

        for (PetMomDTO petMom : petMoms.getContent()) {
            if (petMom.getMember() != null) {
                petMom.getMember().setProfileImage(IMAGE_URL + petMom.getMember().getProfileImage());
            }
            
            if (petMom.getBoardImage() != null) {
                for (BoardImageDTO image : petMom.getBoardImage()) {
                    image.setImageUrl(IMAGE_URL + image.getImageUrl());
                }
            }
        }

        return petMoms;
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
        foundPetMom.setOtherCondition(modifyPetMom.getOtherCondition()
                .stream()
                .map(list -> modelMapper.map(list, OtherType.class))
                .collect(Collectors.toList()));
    }

    @Transactional
    public void deletePetMom(long borderId) {
        petMomRepository.deleteById(borderId);

    }

    public PetMomDTO findPetMomByBoardNo(long boardId) {
        return petMomRepository.findById(boardId)
                .map(petMomboard -> modelMapper.map(petMomboard, PetMomDTO.class))
                .orElseThrow(() -> new NoSuchElementException("펫시터를 찾을 수 없습니다."));

    }


    //내 펫맘 조회
    public Page<PetMomDTO> findMyPetMom(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("boardId"));

        //        Page<Review> reviews = reviewRepository.findAll(pageable);
        return petMomRepository.findByMember(page, memberRepository.findById(memberId))
                .map(petMom -> modelMapper.map(petMom, PetMomDTO.class));
    }


    @Transactional
    public void updateMomCancle(PetMomDTO momCancle, long boardId) {
        PetMom petMom = petMomRepository.findById(boardId).get();

        petMom.setMomStatus(momCancle.getMomStatus());
    }


    //    public Page<PetMomDTO> findPetMom(Pageable page, String location, LocalDate startDate, LocalDate endDate, boolean petYN, String other) {
    //        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));
    //        return petMomRepository.findBoardByLocationOrStartDateOrEndDateOrPetYNOrOther(page, location, startDate, endDate, petYN, other).map(list -> modelMapper.map(list, PetMomDTO.class));
    //    }
}

