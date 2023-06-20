package com.nasigolang.ddbnb.review.repository;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  
    Page<Review> findByMember(Pageable page, Optional<Member> memberId);
  
}
