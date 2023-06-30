package com.nasigolang.ddbnb.pet.petmom.controller;


import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.pet.petmom.dto.ApplicantDTO;
import com.nasigolang.ddbnb.pet.petmom.entity.MomApplicant;
import com.nasigolang.ddbnb.pet.petmom.service.MomApplicantService;
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

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/v1/momapplicant")
@AllArgsConstructor
public class MomApplicantController {

    private final MomApplicantService momApplicantService;

    @GetMapping("/{boardId}")
    @ApiOperation(value = "신청자 목록 조회")
    public ResponseEntity<ResponseDto> findMomApplicantList(@PageableDefault Pageable page,
            @PathVariable("boardId") long boardId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ApplicantDTO> applicantList = momApplicantService.findMomApplicantList(page, boardId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(applicantList);
        ResponseDtoWithPaging data = new ResponseDtoWithPaging(applicantList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @PostMapping("regist")
    public ResponseEntity<ResponseDto> registApllicant(@RequestBody ApplicantDTO newApplicant) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        momApplicantService.registNewApplicant(newApplicant);
        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성 성공", null));

    }

    @ApiOperation(value = "나의 펫맘 신청 조회")
    @GetMapping("/mypetmoms")
    public ResponseEntity<ResponseDto> findMyPetMomApp(@PageableDefault Pageable pageable, @RequestParam long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ApplicantDTO> petMom = momApplicantService.findMyPetMomApp(pageable, memberId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMom);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petMom.getContent(), selectCriteria);
        //        responseMap.put("reviews", reviews.getContent());

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }
}
