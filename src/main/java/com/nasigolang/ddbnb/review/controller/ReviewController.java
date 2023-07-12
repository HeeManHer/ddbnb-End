package com.nasigolang.ddbnb.review.controller;

import com.nasigolang.ddbnb.common.ResponseDTO;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDTOWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.review.dto.ReviewDTO;
import com.nasigolang.ddbnb.review.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "모든 리뷰 목록 조회")
    @GetMapping("/reviews")
    public ResponseEntity<ResponseDTO> findAllReview(@PageableDefault Pageable pageable, @RequestParam long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ReviewDTO> reviews = reviewService.findAllReview(pageable, memberId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(reviews);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(reviews.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }

    @ApiOperation("리뷰코드로 리뷰 조회")
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ResponseDTO> findReviewById(@PathVariable long reviewId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                             .headers(headers)
                             .body(new ResponseDTO(HttpStatus.OK, "조회성공", reviewService.findReviewById(reviewId)));
    }

    @ApiOperation("리뷰 작성")
    @PostMapping("/reviews")
    public ResponseEntity<ResponseDTO> registNewReview(@RequestPart("newReview") ReviewDTO newReview,
                                                       @RequestPart(value = "img", required = false) List<MultipartFile> image) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        newReview.setReviewWriteDate(new Date());
        reviewService.postReview(newReview, image);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "생성성공", newReview));
    }

}
