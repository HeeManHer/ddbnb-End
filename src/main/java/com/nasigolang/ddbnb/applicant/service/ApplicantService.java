package com.nasigolang.ddbnb.applicant.service;

import com.nasigolang.ddbnb.applicant.dto.ApplicantDTO;
import com.nasigolang.ddbnb.applicant.entity.Applicant;
import com.nasigolang.ddbnb.applicant.repository.ApplicantRepository;
import com.nasigolang.ddbnb.board.entity.Board;
import com.nasigolang.ddbnb.board.repository.BoardRepository;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    public Page<ApplicantDTO> findApplicantList(Pageable page, long boardId) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("appliedDate").descending());

        return applicantRepository.findByBoard(page, boardRepository.findById(boardId))
                .map(list -> modelMapper.map(list, ApplicantDTO.class));
    }

    @Transactional
    public void registApplicant(ApplicantDTO applicant) {
        applicantRepository.save(modelMapper.map(applicant, Applicant.class));
    }

    public Page<ApplicantDTO> findMyApply(Pageable page, long memberId, String category) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                Sort.by("appliedDate").descending());

        List<Board> boards = boardRepository.findByBoardCategoryContaining(category);

        return applicantRepository.findByMemberAndBoardIn(page, memberRepository.findById(memberId), boards)
                .map(petSitter -> modelMapper.map(petSitter, ApplicantDTO.class));
    }


    @Transactional
    public void deleteMyPetSitterApp(long applicantId) {

        applicantRepository.deleteById(applicantId);
    }
}
