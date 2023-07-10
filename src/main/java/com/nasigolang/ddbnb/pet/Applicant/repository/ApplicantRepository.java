package com.nasigolang.ddbnb.pet.Applicant.repository;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.pet.Applicant.dto.ApplicantDTO;
import com.nasigolang.ddbnb.pet.Applicant.entity.ApplicantEntity;
import com.nasigolang.ddbnb.pet.petsitter.entity.PetsitterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
    Page<ApplicantEntity> findByBoardId(Pageable page, Optional<PetsitterEntity> boardId);

    Page<ApplicantEntity> findByMember(Pageable page, Optional<Member> memberId);

}
