package com.nasigolang.ddbnb.pet.petsitter.service;

import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.pet.petsitter.dto.PetSitterImageDTO;
import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import com.nasigolang.ddbnb.pet.petsitter.entity.PetSitterImage;
import com.nasigolang.ddbnb.pet.petsitter.entity.PetsitterEntity;
import com.nasigolang.ddbnb.pet.petsitter.repository.PetSitterImageRepository;
import com.nasigolang.ddbnb.pet.petsitter.repository.PetSitterMapper;
import com.nasigolang.ddbnb.pet.petsitter.repository.PetsitterRepository;
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
import java.util.UUID;


@Service
public class PetsitterService {

    private final PetsitterRepository petSitterRepository;
    private final PetSitterImageRepository petSitterImageRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PetSitterMapper petSitterMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    public PetsitterService(PetsitterRepository petSitterRepository, PetSitterImageRepository petSitterImageRepository,
                            ModelMapper modelMapper, MemberRepository memberRepository,
                            PetSitterMapper petSitterMapper) {
        this.petSitterRepository = petSitterRepository;
        this.petSitterImageRepository = petSitterImageRepository;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
        this.petSitterMapper = petSitterMapper;
    }

    //    public Page<PetsitterboardDTO> findMenuList(Pageable page, String location, String petSize, String care, LocalDate startDate, LocalDate endDate) {
    //
    //        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId"));
    //
    //        return petSitterRepository.findPetsitterEntityByLocationOrPetSizeOrCareOrStartDateOrEndDate(
    //                page, location, petSize, care, startDate, endDate).map(list -> modelMapper.map(list, PetsitterboardDTO.class));
    //        return petSitterRepository.findAll(page).map(list -> modelMapper.map(list,PetsitterboardDTO.class));
    //    }

    //    public Page<PetsitterboardDTO> findMenuList(Pageable page, String location, String petSize, String care,
    //                                                LocalDate startDate, LocalDate endDate) {
    //        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId")
    //                                                                                                                .descending());
    //
    //        if(location != null && !location.isEmpty() || petSize != null && !petSize.isEmpty() || care != null && !care.isEmpty() || startDate != null || endDate != null) {
    //            return petSitterRepository.findByPetsitterEntityContainingAndLocationContainingAndPetSizeContainingAndCareContainingAndStartDateContainingAndEndDate(page, location, petSize, care, startDate, endDate)
    //                                      .map(list -> modelMapper.map(list, PetsitterboardDTO.class));
    //        } else {
    //            return petSitterRepository.findAll(page).map(list -> modelMapper.map(list, PetsitterboardDTO.class));
    //        }
    //    }
//    public Page<PetsitterboardDTO> findMenuList(Pageable page, String location, String petSize, String care,
//                                                LocalDate startDate, LocalDate endDate, String sitterStatus) {
//        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("boardId")
//                .descending());
//
//        if(!location.equals("")|| !petSize.equals("") || !care.equals("") || startDate != null || endDate != null) {
//            System.out.println(sitterStatus);
//            System.out.println(petSize);
//            System.out.println(care);
//            System.out.println(location);
//            return petSitterRepository.findByLocationContainingAndPetSizeContainingAndCareContainingAndStartDateContainingAndEndDateContainingAndSitterStatusContaining(page, location, petSize, care, startDate, endDate, sitterStatus)
//                    .map(list -> modelMapper.map(list, PetsitterboardDTO.class));
//        } else {
//            return petSitterRepository.findAll(page).map(list -> modelMapper.map(list, PetsitterboardDTO.class));
//        }
//    }
    public Page<PetsitterboardDTO> findAllPetSitter(Pageable page, Map<String, Object> searchValue) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                              Sort.by("boardId").descending());

        Page<PetsitterboardDTO> petSitters;

        if (searchValue.isEmpty()) {
            petSitters = petSitterRepository.findAll(page)
                                            .map(petSitter -> modelMapper.map(petSitter, PetsitterboardDTO.class));
        } else {
            List<PetsitterboardDTO> petSitterList = petSitterMapper.searchPetSitter(searchValue);

            petSitters = (Page<PetsitterboardDTO>) Pagenation.createPage(petSitterList, page);
        }

        for (PetsitterboardDTO petSitter : petSitters.getContent()) {
            petSitter.getMember().setProfileImage(IMAGE_URL + petSitter.getMember().getProfileImage());

            for (PetSitterImageDTO image : petSitter.getBoardImage()) {
                image.setImageUrl(IMAGE_URL + image.getImageUrl());
            }
        }

        return petSitters;
    }

    @Transactional
    public void registPetSitter(PetsitterboardDTO petSitter, List<MultipartFile> images) {

        long no = petSitterRepository.save(modelMapper.map(petSitter, PetsitterEntity.class)).getBoardId();

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                String imageName = UUID.randomUUID().toString().replace("-", "");

                try {
                    String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, images.get(i));

                    PetSitterImageDTO image = new PetSitterImageDTO();
                    image.setImageUrl(replaceFileName);
                    image.setBoardId(no);
                    petSitterImageRepository.save(modelMapper.map(image, PetSitterImage.class));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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

    public PetsitterboardDTO findPetsitterByBoardNo(long boardId) {

        PetsitterEntity petSitter = petSitterRepository.findById(boardId).get();

        petSitter.getMember().setProfileImage(IMAGE_URL + petSitter.getMember().getProfileImage());

        for (PetSitterImage image : petSitter.getBoardImage()) {
            image.setImageUrl(IMAGE_URL + image.getImageUrl());
        }

        return modelMapper.map(petSitter, PetsitterboardDTO.class);
    }


    @Transactional
    public void updateSitterCancle(PetsitterboardDTO sitterCancle, long boardId) {
        PetsitterEntity petsitter = petSitterRepository.findById(boardId).get();

        petsitter.setSitterStatus(sitterCancle.getSitterStatus());

    }

    //내 펫시터 조회
    public Page<PetsitterboardDTO> findMyPetSitter(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                              Sort.by("boardId"));

        return petSitterRepository.findByMember(page, memberRepository.findById(memberId))
                                  .map(petSitter -> modelMapper.map(petSitter, PetsitterboardDTO.class));
    }


}
