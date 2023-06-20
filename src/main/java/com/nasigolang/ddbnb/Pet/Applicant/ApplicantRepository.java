package com.nasigolang.ddbnb.Pet.applicant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<com.nasigolang.ddbnb.Pet.applicant.Applicant,Long> {
    Page<com.nasigolang.ddbnb.Pet.applicant.Applicant> findByBoardId(Pageable page, long boardId);
}
