package com.nasigolang.ddbnb.Pet.Applicant;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/v1/applicant")
@AllArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/{boardId}")
    @ApiOperation(value="신청자 목록 조회")
    public ResponseEntity<ResponseDto> findApplicantList(@PageableDefault Pageable page,
                                                         @PathVariable long boardId){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<com.nasigolang.ddbnb.Pet.applicant.ApplicantDTO> applicantList = applicantService.findApplicantList(page,boardId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(applicantList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(applicantList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }
}
