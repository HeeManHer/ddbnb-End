package com.nasigolang.ddbnb.pet.petmom.repositroy;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.pet.petmom.entity.PetMom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PetMomRepository extends JpaRepository<PetMom, Long> {

    Page<PetMom> findByMember(Pageable page, Optional<Member> memberId);

}
