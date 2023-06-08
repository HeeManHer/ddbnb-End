package com.nasigolang.ddbnb.petmom.repositroy;

import com.nasigolang.ddbnb.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetMomRepository extends JpaRepository<Review, Long> {
}
