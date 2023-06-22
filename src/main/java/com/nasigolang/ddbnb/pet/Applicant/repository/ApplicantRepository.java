package com.nasigolang.ddbnb.pet.Applicant.repository;

import com.nasigolang.ddbnb.pet.Applicant.entity.ApplicantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity,Long> {
    Page<ApplicantEntity> findByBoardId(Pageable page, long boardId);
}
