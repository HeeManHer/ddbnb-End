package com.nasigolang.ddbnb.Pet.petmom.controller;

import com.nasigolang.ddbnb.Pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.Pet.petmom.service.PetMomService;
import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Delete;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/petmom")
@AllArgsConstructor
public class PetMomController {
    private final PetMomService petmomService;

    @PostMapping("/regist")
    public ResponseEntity<ResponseDto> registNewReport(@RequestBody PetMomDTO newPetmom){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        newPetmom.setPetMomDate(new Date());
        petmomService.registNewPetMom(newPetmom);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성 성공", null));

    }

    @GetMapping("/list")
    public  ResponseEntity<ResponseDto> findAllPetMom(@PageableDefault Pageable pageable){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();

        Page<PetMomDTO> petMoms = petmomService.findAllPetMoms(pageable);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petMoms.getContent(), selectCriteria);
        responseMap.put("petmoms", petMoms.getContent());


        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }
    @GetMapping("/list/find")
    public  ResponseEntity<ResponseDto> findPetMom(@PageableDefault Pageable pageable,
                                                   @RequestParam(name= "location",defaultValue ="")String location,
                                                   @RequestParam(name ="startDate",defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @RequestParam(name = "endDate",defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate,
                                                   @RequestParam(name="petYN", defaultValue = "")Boolean petYN,
                                                   @RequestParam(name="other", defaultValue = "")String other){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetMomDTO> petMoms = petmomService.findPetMom(pageable,location,startDate,endDate,petYN,other);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petMoms.getContent(), selectCriteria);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }
}


