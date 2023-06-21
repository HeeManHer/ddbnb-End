package com.nasigolang.ddbnb.pet.petmom.repositroy;

import com.nasigolang.ddbnb.pet.petmom.entity.MomApplicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MomApplicantRepository extends JpaRepository<MomApplicant, Integer> {

    Page<MomApplicant> findByBoardId(Pageable page, int boardId);

}
