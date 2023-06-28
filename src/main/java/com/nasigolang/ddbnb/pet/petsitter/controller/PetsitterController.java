package com.nasigolang.ddbnb.pet.petsitter.controller;


import com.nasigolang.ddbnb.pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import com.nasigolang.ddbnb.pet.petsitter.service.PetsitterService;
import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import com.nasigolang.ddbnb.pet.petsitter.service.PetsitterService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/petsitter")
@AllArgsConstructor
public class PetsitterController {

    private final PetsitterService petsitterService;

    @GetMapping("/list")
    @ApiOperation(value = "펫시터 목록 조회")
    public ResponseEntity<ResponseDto> findAllList(@PageableDefault(size = 10) Pageable page,
                                                   @RequestParam(name = "location", defaultValue = "") String location,
                                                   @RequestParam(name = "petSize", defaultValue = "") String petSize,
                                                   @RequestParam(name = "care", defaultValue = "") String care,
                                                   @RequestParam(name = "sitterStatus", defaultValue = "") String sitterStatus,
                                                   @RequestParam(name = "startDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @RequestParam(name = "endDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        System.out.println(sitterStatus);
        System.out.println(location);
        Page<PetsitterboardDTO> petsitterList = petsitterService.findMenuList(page, location, petSize, care, startDate, endDate, sitterStatus);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petsitterList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petsitterList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @GetMapping("/list/{boardid}")
    @ApiOperation(value = "펫시터 상세 조회")
    public ResponseEntity<ResponseDto> findList(@PathVariable("boardid") Long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        PetsitterboardDTO petsitter = petsitterService.findPetsitterByBoardNo(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", petsitter));
    }



    @PostMapping("/regist")
    @ApiOperation(value = "펫시터 추가")
    public ResponseEntity<ResponseDto> registPetSitter(@RequestBody PetsitterboardDTO petSitter) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.registPetSitter(petSitter);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성 성공", null));
    }

    @PutMapping("/modify")
    public ResponseEntity<ResponseDto> modifyPetSitter(@RequestBody PetsitterboardDTO modifypetsitter) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.modifyPetSitter(modifypetsitter);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "수정 성공", null));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto> deletePetSitter(@PathVariable Long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.deletePetSitter(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "삭제 성공", null));
    }

    @ApiOperation(value = "펫맘 모집취소")
    @PutMapping("/list/{boardId}/status")
    public ResponseEntity<ResponseDto> updateSitterCancle(@RequestBody PetsitterboardDTO cancleSitter, @PathVariable("boardId") long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.updateSitterCancle(cancleSitter, boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "상태 변경 성공", null));
    }




}
