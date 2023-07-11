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
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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


@Service
@AllArgsConstructor
public class PetsitterService {

    private final PetsitterRepository petSitterRepository;
    private final PetSitterImageRepository petSitterImageRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PetSitterMapper petSitterMapper;
    private final FileUploadUtils fileUploadUtils;


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

//        for (PetsitterboardDTO petSitter : petSitters.getContent()) {
//            if (petSitter.getMember() != null) {
//                petSitter.getMember().setProfileImage(fileUploadUtils.fileUrl(petSitter.getMember().getProfileImage()));
//            }
//
//            if (petSitter.getBoardImage() != null) {
//                for (PetSitterImageDTO image : petSitter.getBoardImage()) {
//
//                    image.setImageUrl(fileUploadUtils.fileUrl(image.getImageUrl()));
//                }
//            }
//        }

        return petSitters;
    }

    @Transactional
    public void registPetSitter(PetsitterboardDTO petSitter, List<MultipartFile> images) {

        long no = petSitterRepository.save(modelMapper.map(petSitter, PetsitterEntity.class)).getBoardId();

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                try {
                    String replaceFileName = fileUploadUtils.saveFile(images.get(i));

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
    public void modifyPetSitter(PetsitterboardDTO modifyPetSitter, List<MultipartFile> images) {

        PetsitterEntity foundPetSitter = petSitterRepository.findById(modifyPetSitter.getBoardId()).get();

        foundPetSitter.setBoardTitle(modifyPetSitter.getBoardTitle());
        foundPetSitter.setLocation(modifyPetSitter.getLocation());
        foundPetSitter.setCare(modifyPetSitter.getCare());
        foundPetSitter.setStartDate(modifyPetSitter.getStartDate());
        foundPetSitter.setEndDate(modifyPetSitter.getEndDate());
        foundPetSitter.setSignficant(modifyPetSitter.getSignficant());
        foundPetSitter.setRequest(modifyPetSitter.getRequest());
        foundPetSitter.setPetName(modifyPetSitter.getPetName());
        foundPetSitter.setPetAge(modifyPetSitter.getPetAge());
        foundPetSitter.setPetShape(modifyPetSitter.getPetShape());
        foundPetSitter.setPetGender(modifyPetSitter.getPetGender());
        foundPetSitter.setPetSize(modifyPetSitter.getPetSize());

        if (images != null) {
            for (int i = 0; i < foundPetSitter.getBoardImage().size(); i++) {
                if (i >= images.size()) {
                    foundPetSitter.getBoardImage().remove(i);
                }
                try {
                    String replaceFileName = fileUploadUtils.saveFile(images.get(i));
                    foundPetSitter.getBoardImage().get(i).setImageUrl(replaceFileName);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Transactional
    public void deletePetSitter(Long borderId) {
        petSitterRepository.deleteById(borderId);
    }

    public PetsitterboardDTO findPetsitterByBoardNo(long boardId) {

        PetsitterEntity petSitter = petSitterRepository.findById(boardId).get();

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
