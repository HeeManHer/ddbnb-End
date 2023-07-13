package com.nasigolang.ddbnb.applicant.repository;

import com.nasigolang.ddbnb.applicant.entity.Applicant;
import com.nasigolang.ddbnb.board.entity.Board;
import com.nasigolang.ddbnb.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Page<Applicant> findByBoard(Pageable page, Optional<Board> boardId);

    Page<Applicant> findByMemberAndBoardIn(Pageable page, Optional<Member> memberId, List<Board> boardIds);

}