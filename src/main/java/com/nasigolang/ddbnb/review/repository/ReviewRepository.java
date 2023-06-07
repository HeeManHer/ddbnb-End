package com.nasigolang.ddbnb.review.repository;

import com.nasigolang.ddbnb.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
