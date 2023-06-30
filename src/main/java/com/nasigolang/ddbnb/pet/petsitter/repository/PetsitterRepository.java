package com.nasigolang.ddbnb.pet.petsitter.repository;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.pet.petsitter.entity.PetsitterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetsitterRepository extends JpaRepository<PetsitterEntity, Long> {

    Page<PetsitterEntity> findByMember(Pageable page, Optional<Member> memberId);
}
