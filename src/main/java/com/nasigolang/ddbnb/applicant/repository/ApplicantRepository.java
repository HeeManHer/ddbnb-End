package com.nasigolang.ddbnb.applicant.repository;

import com.nasigolang.ddbnb.applicant.entity.Applicant;
import com.nasigolang.ddbnb.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Page<Applicant> findByBoardId(Pageable page, long boardId);

    Page<Applicant> findByMember(Pageable page, Optional<Member> memberId);

}