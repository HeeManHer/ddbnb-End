package com.nasigolang.ddbnb.pet.petmom.repositroy;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.pet.petmom.entity.MomApplicant;
import com.nasigolang.ddbnb.pet.petmom.entity.PetMom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MomApplicantRepository extends JpaRepository<MomApplicant, Long> {
    Page<MomApplicant> findByBoardId(Pageable page, Optional<PetMom> boardId);

    Page<MomApplicant> findByMember(Pageable page, Optional<Member> memberId);
}
