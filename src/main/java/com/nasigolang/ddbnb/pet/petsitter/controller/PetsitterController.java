package com.nasigolang.ddbnb.pet.petsitter.controller;

import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import com.nasigolang.ddbnb.pet.petsitter.service.PetsitterService;
import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
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

import java.nio.charset.Charset;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/petsitter")
@AllArgsConstructor
public class PetsitterController {

    private final PetsitterService petsitterService;

    @GetMapping("/list")
    @ApiOperation(value="펫시터 목록 조회")
    public ResponseEntity<ResponseDto> findAllList(@PageableDefault Pageable page,
                                                   @RequestParam(name = "location", defaultValue = "") String location,
                                                   @RequestParam(name = "petSize", defaultValue="") String petSize,
                                                   @RequestParam(name= "care", defaultValue= "") String care,
                                                   @RequestParam(name= "startDate", defaultValue= "")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @RequestParam(name= "endDate",defaultValue="")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetsitterboardDTO> petsitterList = petsitterService.findMenuList(page,location,petSize,care,startDate,endDate);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petsitterList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petsitterList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @GetMapping("/list/{boardid}")
    @ApiOperation(value="펫시터 상세 조회")
    public ResponseEntity<ResponseDto> findList(@PathVariable("boardid") Long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        PetsitterboardDTO petsitter = petsitterService.findPetsitterByBoardNo(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", petsitter));
    }

    @PostMapping("/regist")
    @ApiOperation(value="펫시터 추가")
    public ResponseEntity<ResponseDto> registPetSitter(@RequestBody PetsitterboardDTO petSitter) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",Charset.forName("UTF-8")));

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
    public ResponseEntity<ResponseDto> deletePetSitter(@PathVariable Long boardId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petsitterService.deletePetSitter(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "삭제 성공", null));
    }

}
