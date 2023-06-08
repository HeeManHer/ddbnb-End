package com.nasigolang.ddbnb.review.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasigolang.ddbnb.review.dto.ReviewDTO;
import com.nasigolang.ddbnb.review.entity.Review;
import com.nasigolang.ddbnb.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper;

    private EntityManager entityManager;

    private long reviewId;

    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    public List<ReviewDTO> findAllReview(Pageable pageable) {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
    }
}
