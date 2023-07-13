package com.nasigolang.ddbnb.board.controller;

import com.nasigolang.ddbnb.board.dto.PetMomDTO;
import com.nasigolang.ddbnb.board.service.PetMomService;
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
public class PetMomController {

    private final PetMomService petmomService;

    @PostMapping("/petMom")
    public ResponseEntity<ResponseDTO> registNewReport(@RequestPart("newPetMom") PetMomDTO newPetMom,
                                                       @RequestPart(value = "image", required = false) List<MultipartFile> images) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        newPetMom.setBoardDate(new Date());
        newPetMom.setBoardStatus("모집중");
        petmomService.registNewPetMom(newPetMom, images);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "생성 성공", null));

    }

    @GetMapping("/petMom")
    public ResponseEntity<ResponseDTO> findAllPetMom(@PageableDefault Pageable page,
                                                     @RequestParam(name = "location", defaultValue = "") String location,
                                                     @RequestParam(name = "startDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                     @RequestParam(name = "endDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                     @RequestParam(name = "petYN", defaultValue = "") String petYN,
                                                     @RequestParam(name = "otherCondition", defaultValue = "") List<String> otherCondition) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> searchValue = new HashMap<>();

        if (!location.equals("")) {
            searchValue.put("location", "%" + location + "%");
        }

        if (startDate != null) {
            searchValue.put("startDate", startDate);
        }
        if (endDate != null) {
            searchValue.put("endDate", endDate);
        }
        if (!petYN.equals("")) {
            searchValue.put("petYN", petYN);
        }
        if (otherCondition.size() > 0) {
            searchValue.put("otherCondition", otherCondition);
        }

        System.out.println(searchValue.get("otherCondition"));

        Page<PetMomDTO> petMoms = petmomService.findAllPetMoms(page, searchValue);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(petMoms.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }


    @GetMapping("/petMom/{boardId}")
    @ApiOperation(value = "펫시터 상세 조회")
    public ResponseEntity<ResponseDTO> findList(@PathVariable("boardId") long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        PetMomDTO petMom = petmomService.findPetMomByBoardNo(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", petMom));
    }

    @PutMapping("/petMom")
    public ResponseEntity<ResponseDTO> modifyPetMom(
            @RequestPart("modifyPetSitter") PetMomDTO modifyPetMom,
            @RequestPart(value = "image", required = false) List<MultipartFile> images) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petmomService.modifyPetMom(modifyPetMom, images);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "수정 성공", null));
    }

    @DeleteMapping("/petMom/{borderId}")
    public ResponseEntity<ResponseDTO> deletePetMom(@PathVariable int borderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petmomService.deletePetMom(borderId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "삭제 성공", null));
    }

    @ApiOperation(value = "나의 펫맘 조회")
    @GetMapping("/mypetmoms")
    public ResponseEntity<ResponseDTO> findMyPetMom(@PageableDefault Pageable pageable, @RequestParam long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetMomDTO> petMoms = petmomService.findMyPetMom(pageable, memberId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(petMoms.getContent(), selectCriteria);

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }


}