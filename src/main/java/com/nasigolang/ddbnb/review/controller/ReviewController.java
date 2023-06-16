package com.nasigolang.ddbnb.review.controller;

import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.review.dto.ReviewDTO;
import com.nasigolang.ddbnb.review.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation(value = "모든 리뷰 목록 조회")
    @GetMapping("/reviews")
    public ResponseEntity<ResponseDto> findAllReview(@PageableDefault Pageable pageable) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ReviewDTO> reviews = reviewService.findAllReview(pageable);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(reviews);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(reviews.getContent(), selectCriteria);
        //        responseMap.put("reviews", reviews.getContent());

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }

    @ApiOperation("리뷰코드로 리뷰 조회")
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ResponseDto> findReviewById(@PathVariable long reviewId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회성공", reviewService.findReviewById(reviewId)));
    }

    @ApiOperation("리뷰 작성")
    @PostMapping("/reviews")
    public ResponseEntity<ResponseDto> registNewReview(@RequestBody ReviewDTO newReview) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        newReview.setReviewWriteDate(new Date());
        reviewService.postReview(newReview);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성성공", newReview));
    }

}
