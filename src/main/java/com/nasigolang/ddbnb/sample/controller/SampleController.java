package com.nasigolang.ddbnb.sample.controller;

import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.sample.dto.SampleDTO;
import com.nasigolang.ddbnb.sample.service.SampleService;
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
@RequestMapping("/member")
@AllArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> findAllMember(@PageableDefault Pageable page) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<SampleDTO> memberList = sampleService.findMenuList(page);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(memberList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(memberList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseDto> registNewMember(@RequestBody SampleDTO newMember) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        sampleService.registNewMember(newMember);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성 성공", null));

    }

}
