package com.nasigolang.ddbnb.review.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasigolang.ddbnb.review.dto.ReviewDTO;
import com.nasigolang.ddbnb.review.entity.Review;
import com.nasigolang.ddbnb.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

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

    //전체리뷰 조회
    public Page<ReviewDTO> findAllReview(Pageable page) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, 8, Sort.by("reviewId"));

//        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviewRepository.findAll(page).map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    /*일부 조회*/
//    @Transactional
    public ReviewDTO findReviewById(long reviewId) {
        Review foundReview = reviewRepository.findById(reviewId).get();
        return modelMapper.map(foundReview, ReviewDTO.class);

    }

    public void postReview(ReviewDTO newReview){
      reviewRepository.save(modelMapper.map(newReview, Review.class));

    }
}
