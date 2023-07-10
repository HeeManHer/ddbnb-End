package com.nasigolang.ddbnb.review.service;

import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.review.dto.ReviewDTO;
import com.nasigolang.ddbnb.review.dto.ReviewImageDTO;
import com.nasigolang.ddbnb.review.entity.Review;
import com.nasigolang.ddbnb.review.entity.ReviewImage;
import com.nasigolang.ddbnb.review.repository.ReviewImageRepository;
import com.nasigolang.ddbnb.review.repository.ReviewRepository;
import com.nasigolang.ddbnb.util.FileUploadUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final FileUploadUtils fileUploadUtils;

    //전체리뷰 조회
    public Page<ReviewDTO> findAllReview(Pageable page, long memberId) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                              Sort.by("reviewId"));

        Page<Review> reviews = reviewRepository.findByMember(page, memberRepository.findById(memberId));

        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    /*일부 조회*/
    public ReviewDTO findReviewById(long reviewId) {
        Review foundReview = reviewRepository.findById(reviewId).get();

        return modelMapper.map(foundReview, ReviewDTO.class);
    }

    @Transactional
    public void postReview(ReviewDTO newReview, List<MultipartFile> images) {
        long no = reviewRepository.save(modelMapper.map(newReview, Review.class)).getReviewId();

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                try {
                    String imageUrl = fileUploadUtils.saveFile(images.get(i));

                    ReviewImageDTO image = new ReviewImageDTO();
                    image.setImageUrl(imageUrl);
                    image.setReviewId(no);
                    reviewImageRepository.save(modelMapper.map(image, ReviewImage.class));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
