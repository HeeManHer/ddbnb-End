package com.nasigolang.ddbnb.board.repository;

import com.nasigolang.ddbnb.board.entity.PetMom;
import com.nasigolang.ddbnb.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetMomRepository extends JpaRepository<PetMom, Long> {

    Page<PetMom> findByMember(Pageable page, Optional<Member> memberId);

}
