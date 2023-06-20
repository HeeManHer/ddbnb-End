package com.nasigolang.ddbnb.pet.petmom.controller;



import com.nasigolang.ddbnb.Pet.petmom.dto.ApplicantDTO;
import com.nasigolang.ddbnb.Pet.petmom.service.MomApplicantService;
import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
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
@RequestMapping("/api/v1/momApplicant")
@AllArgsConstructor
public class MomApplicantController {

    private final MomApplicantService momApplicantService;

    @GetMapping("/{boardId}")
    @ApiOperation(value="신청자 목록 조회")
    public ResponseEntity<ResponseDto> findMomApplicantList(@PageableDefault Pageable page,
                                                         @PathVariable ("boardId")int boardId){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ApplicantDTO> applicantList = momApplicantService.findMomApplicantList(page,boardId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(applicantList);
        ResponseDtoWithPaging data = new ResponseDtoWithPaging(applicantList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @PostMapping("regist")
    public ResponseEntity<ResponseDto> registApllicant(@RequestBody ApplicantDTO newApplicant){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        momApplicantService.registNewApplicant(newApplicant);
        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성 성공", null));

    }
}
