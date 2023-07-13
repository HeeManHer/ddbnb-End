package com.nasigolang.ddbnb.board.service;

import com.nasigolang.ddbnb.board.dto.BoardImageDTO;
import com.nasigolang.ddbnb.board.dto.PetMomDTO;
import com.nasigolang.ddbnb.board.entity.BoardImage;
import com.nasigolang.ddbnb.board.entity.OtherType;
import com.nasigolang.ddbnb.board.entity.PetMom;
import com.nasigolang.ddbnb.board.repository.BoardImageRepository;
import com.nasigolang.ddbnb.board.repository.PetMomMapper;
import com.nasigolang.ddbnb.board.repository.PetMomRepository;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetMomService {

    private final PetMomRepository petMomRepository;
    private final MemberRepository memberRepository;
    private final BoardImageRepository boardImageRepository;
    private final PetMomMapper petMomMapper;
    private final ModelMapper modelMapper;
    private final FileUploadUtils fileUploadUtils;

    @Transactional
    public void registNewPetMom(PetMomDTO newPetmom, List<MultipartFile> images) {
        long no = petMomRepository.save(modelMapper.map(newPetmom, PetMom.class)).getBoardId();

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                try {
                    String replaceFileName = fileUploadUtils.saveFile(images.get(i));

                    BoardImageDTO image = new BoardImageDTO();
                    image.setImageUrl(replaceFileName);
                    image.setBoardId(no);
                    boardImageRepository.save(modelMapper.map(image, BoardImage.class));

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

        return petMoms;
    }

    @Transactional
    public void modifyPetMom(PetMomDTO modifyPetMom, List<MultipartFile> images) {

        PetMom foundPetMom = petMomRepository.findById(modifyPetMom.getBoardId()).get();

        foundPetMom.setHouseType(modifyPetMom.getHouseType());
        foundPetMom.setPetYN(modifyPetMom.getPetYN());
        foundPetMom.setStartDate(modifyPetMom.getStartDate());
        foundPetMom.setRequest(modifyPetMom.getRequest());
        foundPetMom.setSignificant(modifyPetMom.getSignificant());
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

        if (images != null) {
            for (int i = 0; i < foundPetMom.getBoardImage().size(); i++) {
                if (i >= images.size()) {
                    foundPetMom.getBoardImage().remove(i);
                }
                try {
                    String replaceFileName = fileUploadUtils.saveFile(images.get(i));
                    foundPetMom.getBoardImage().get(i).setImageUrl(replaceFileName);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Transactional
    public void deletePetMom(long borderId) {
        petMomRepository.deleteById(borderId);

    }

    public PetMomDTO findPetMomByBoardNo(long boardId) {
        return petMomRepository.findById(boardId)
                .map(petMom -> modelMapper.map(petMom, PetMomDTO.class))
                .get();

    }


    //내 펫맘 조회
    public Page<PetMomDTO> findMyPetMom(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("boardId"));

        return petMomRepository.findByMember(page, memberRepository.findById(memberId))
                .map(petMom -> modelMapper.map(petMom, PetMomDTO.class));
    }


}

