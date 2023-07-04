package com.nasigolang.ddbnb.pet.petsitter.controller;


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
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/petsitter")
@AllArgsConstructor
public class PetsitterController {

    private final PetsitterService petsitterService;

    @GetMapping("/list")
    @ApiOperation(value = "펫시터 목록 조회")
    public ResponseEntity<ResponseDto> findAllList(@PageableDefault Pageable page,
                                                   @RequestParam(name = "location", defaultValue = "") String location,
                                                   @RequestParam(name = "petSize", defaultValue = "") List<String> petSize,
                                                   @RequestParam(name = "care", defaultValue = "") String care,
                                                   @RequestParam(name = "startDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @RequestParam(name = "endDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                   @RequestParam(name = "sitterStatus", defaultValue = "") String sitterStatus) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        Map<String, Object> searchValue = new HashMap<>();

        if (!location.equals("")) {
            searchValue.put("location", location);
        }
        if (petSize.size() > 0) {
            searchValue.put("petSize", petSize);
        }
        if (!care.equals("")) {
            searchValue.put("care", care);
        }
        if (startDate != null) {
            searchValue.put("startDate", startDate);
        }
        if (endDate != null) {
            searchValue.put("endDate", endDate);
        }
        if (!sitterStatus.equals("")) {
            searchValue.put("sitterStatus", sitterStatus);
        }

        System.out.println(searchValue);
        Page<PetsitterboardDTO> petSitters = petsitterService.findAllPetSitter(page, searchValue);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petSitters);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petSitters.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회 성공", data), headers, HttpStatus.OK);
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
    public ResponseEntity<ResponseDto> registPetSitter(@RequestPart("newPetSitter") PetsitterboardDTO petSitter,
                                                       @RequestPart(value = "image", required = false) List<MultipartFile> images) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petSitter.setBoardDate(LocalDate.now());
        petsitterService.registPetSitter(petSitter, images);

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
    public ResponseEntity<ResponseDto> updateSitterCancle(@RequestBody PetsitterboardDTO cancleSitter,
                                                          @PathVariable("boardId") long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.updateSitterCancle(cancleSitter, boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "상태 변경 성공", null));
    }


    @ApiOperation(value = "나의 펫시터 조회")
    @GetMapping("/mypetsitters")
    public ResponseEntity<ResponseDto> findMyPetSitter(@PageableDefault Pageable pageable,
                                                       @RequestParam long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetsitterboardDTO> petSitter = petsitterService.findMyPetSitter(pageable, memberId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petSitter);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petSitter.getContent(), selectCriteria);
        //        responseMap.put("reviews", reviews.getContent());

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }
}
