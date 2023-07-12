package com.nasigolang.ddbnb.board.repository;

import com.nasigolang.ddbnb.board.entity.PetSitter;
import com.nasigolang.ddbnb.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {

    Page<PetSitter> findByMember(Pageable page, Optional<Member> memberId);
}
