package com.nasigolang.ddbnb.Pet.petsitter.controller;

import com.nasigolang.ddbnb.Pet.petsitter.dto.PetsitterboardDTO;
import com.nasigolang.ddbnb.Pet.petsitter.service.PetsitterService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;


@RestController
@RequestMapping("/api/v1/petsitter")
@AllArgsConstructor
public class PetsitterController {

    private final PetsitterService petsitterService;

    @GetMapping("/list")
    @ApiOperation(value = "펫시터 목록 조회")
    public ResponseEntity<ResponseDto> findAllList(@PageableDefault Pageable page) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetsitterboardDTO> petsitterList = petsitterService.findMenuList(page);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petsitterList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petsitterList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }
    //
    //    @PostMapping("/list")
    //    @ApiOperation(value="펫시터 추가")
    //    public ResponseEntity<ResponseDto> modify
}
