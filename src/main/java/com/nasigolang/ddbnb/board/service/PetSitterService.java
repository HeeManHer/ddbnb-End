package com.nasigolang.ddbnb.board.service;

import com.nasigolang.ddbnb.board.dto.BoardImageDTO;
import com.nasigolang.ddbnb.board.dto.PetSitterDTO;
import com.nasigolang.ddbnb.board.entity.BoardImage;
import com.nasigolang.ddbnb.board.entity.PetSitter;
import com.nasigolang.ddbnb.board.repository.BoardImageRepository;
import com.nasigolang.ddbnb.board.repository.PetSitterMapper;
import com.nasigolang.ddbnb.board.repository.PetSitterRepository;
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


@Service
@AllArgsConstructor
public class PetSitterService {

    private final PetSitterRepository petSitterRepository;
    private final PetSitterMapper petSitterMapper;
    private final MemberRepository memberRepository;
    private final BoardImageRepository boardImageRepository;
    private final ModelMapper modelMapper;
    private final FileUploadUtils fileUploadUtils;


    public Page<PetSitterDTO> findAllPetSitter(Pageable page, Map<String, Object> searchValue) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("boardId").descending());

        Page<PetSitterDTO> petSitters;

        if (searchValue.isEmpty()) {
            petSitters = petSitterRepository.findAll(page)
                    .map(petSitter -> modelMapper.map(petSitter, PetSitterDTO.class));
        } else {
            List<PetSitterDTO> petSitterList = petSitterMapper.searchPetSitter(searchValue);

            petSitters = (Page<PetSitterDTO>) Pagenation.createPage(petSitterList, page);
        }

        return petSitters;
    }

    @Transactional
    public void registPetSitter(PetSitterDTO petSitter, List<MultipartFile> images) {

        long no = petSitterRepository.save(modelMapper.map(petSitter, PetSitter.class)).getBoardId();

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

    @Transactional
    public void modifyPetSitter(PetSitterDTO modifyPetSitter, List<MultipartFile> images) {

        PetSitter foundPetSitter = petSitterRepository.findById(modifyPetSitter.getBoardId()).get();

        foundPetSitter.setBoardTitle(modifyPetSitter.getBoardTitle());
        foundPetSitter.setLocation(modifyPetSitter.getLocation());
        foundPetSitter.setCare(modifyPetSitter.getCare());
        foundPetSitter.setStartDate(modifyPetSitter.getStartDate());
        foundPetSitter.setEndDate(modifyPetSitter.getEndDate());
        foundPetSitter.setSignificant(modifyPetSitter.getSignificant());
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

    public PetSitterDTO findPetsitterByBoardNo(long boardId) {

        PetSitter petSitter = petSitterRepository.findById(boardId).get();

        return modelMapper.map(petSitter, PetSitterDTO.class);
    }


    //내 펫시터 조회
    public Page<PetSitterDTO> findMyPetSitter(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("boardId"));

        return petSitterRepository.findByMember(page, memberRepository.findById(memberId))
                .map(petSitter -> modelMapper.map(petSitter, PetSitterDTO.class));
    }


}
