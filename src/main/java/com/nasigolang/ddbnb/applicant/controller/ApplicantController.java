package com.nasigolang.ddbnb.applicant.controller;

import com.nasigolang.ddbnb.applicant.dto.ApplicantDTO;
import com.nasigolang.ddbnb.applicant.service.ApplicantService;
import com.nasigolang.ddbnb.common.ResponseDTO;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDTOWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/applicant/{boardId}")
    @ApiOperation(value = "신청자 목록 조회")
    public ResponseEntity<ResponseDTO> findApplicantList(@PageableDefault Pageable page,
                                                         @PathVariable long boardId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ApplicantDTO> applicantList = applicantService.findApplicantList(page, boardId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(applicantList);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(applicantList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
    }


    @PostMapping("/applicant")
    public ResponseEntity<ResponseDTO> findApplicant(@RequestBody ApplicantDTO applicant) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        applicant.setAppliedDate(new Date());
        applicantService.registApplicant(applicant);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "생성 성공", null));

    }

    @ApiOperation(value = "나의 펫시터 신청 조회")
    @GetMapping("/applicant/myApply")
    public ResponseEntity<ResponseDTO> findMyPetSitterApp(@PageableDefault Pageable pageable,
                                                          @RequestParam long memberId,
                                                          @RequestParam String category) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ApplicantDTO> petSitter = applicantService.findMyApply(pageable, memberId, category);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petSitter);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(petSitter.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }

    @ApiOperation(value = "신청취소")
    @ApiResponses({
            @ApiResponse(code = 204, message = "신청취소 성공"),
            @ApiResponse(code = 400, message = "잘못된 파라미터")
    })
    @DeleteMapping("/applicant/{applicantId}")
    public ResponseEntity<?> deleteMyPetSitterApp(@PathVariable long applicantId) {

        applicantService.deleteMyPetSitterApp(applicantId);

        return ResponseEntity
                .noContent()
                .build();
    }
}