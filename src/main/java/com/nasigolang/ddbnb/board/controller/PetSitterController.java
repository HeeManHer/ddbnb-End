package com.nasigolang.ddbnb.board.controller;


import com.nasigolang.ddbnb.board.dto.PetSitterDTO;
import com.nasigolang.ddbnb.board.service.PetSitterService;
import com.nasigolang.ddbnb.common.ResponseDTO;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDTOWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PetSitterController {

    private final PetSitterService petsitterService;

    @GetMapping("/petSitter")
    @ApiOperation(value = "펫시터 목록 조회")
    public ResponseEntity<ResponseDTO> findAllList(@PageableDefault Pageable page,
                                                   @RequestParam(name = "location", defaultValue = "") String location,
                                                   @RequestParam(name = "petSize", defaultValue = "") List<String> petSize,
                                                   @RequestParam(name = "care", defaultValue = "") List<String> care,
                                                   @RequestParam(name = "startDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @RequestParam(name = "endDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                   @RequestParam(name = "sitterStatus", defaultValue = "") String sitterStatus) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> searchValue = new HashMap<>();

        if (!location.equals("")) {
            searchValue.put("location", "%" + location + "%");
        }
        if (petSize.size() > 0) {
            searchValue.put("petSize", petSize);
        }
        if (care.size() > 0) {
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

        Page<PetSitterDTO> petSitters = petsitterService.findAllPetSitter(page, searchValue);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petSitters);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(petSitters.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK, "조회 성공", data), headers, HttpStatus.OK);
    }


    @GetMapping("/petSitter/{boardId}")
    @ApiOperation(value = "펫시터 상세 조회")
    public ResponseEntity<ResponseDTO> findList(@PathVariable("boardId") long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        PetSitterDTO petSitter = petsitterService.findPetsitterByBoardNo(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", petSitter));
    }


    @PostMapping("/petSitter")
    @ApiOperation(value = "펫시터 추가")
    public ResponseEntity<ResponseDTO> registPetSitter(@RequestPart("newPetSitter") PetSitterDTO petSitter,
                                                       @RequestPart(value = "image", required = false) List<MultipartFile> images) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petSitter.setBoardDate(new Date());
        petSitter.setBoardStatus("모집중");
        petsitterService.registPetSitter(petSitter, images);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "생성 성공", null));
    }

    @PutMapping("/petSitter")
    public ResponseEntity<ResponseDTO> modifyPetSitter(
            @RequestPart("modifyPetSitter") PetSitterDTO modifyPetSitter,
            @RequestPart(value = "image", required = false) List<MultipartFile> images) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.modifyPetSitter(modifyPetSitter, images);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "수정 성공", null));
    }

    @DeleteMapping("/petSitter/{boardId}")
    public ResponseEntity<ResponseDTO> deletePetSitter(@PathVariable Long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.deletePetSitter(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "삭제 성공", null));
    }

    @ApiOperation(value = "나의 펫시터 조회")
    @GetMapping("/mypetsitters")
    public ResponseEntity<ResponseDTO> findMyPetSitter(@PageableDefault Pageable pageable,
                                                       @RequestParam long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetSitterDTO> petSitter = petsitterService.findMyPetSitter(pageable, memberId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petSitter);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(petSitter.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }
}
