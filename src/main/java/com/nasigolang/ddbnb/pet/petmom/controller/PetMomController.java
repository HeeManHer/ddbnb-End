package com.nasigolang.ddbnb.pet.petmom.controller;

import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.pet.petmom.dto.PetMomDTO;
import com.nasigolang.ddbnb.pet.petmom.service.PetMomService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/petmom")
public class PetMomController {
    private final PetMomService petmomService;

    @Autowired
    public PetMomController(PetMomService petmomService) {
        this.petmomService = petmomService;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseDto> registNewReport(@RequestBody PetMomDTO newPetmom) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        newPetmom.setBoardDate(new Date());
        petmomService.registNewPetMom(newPetmom);


        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "생성 성공", null));

    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> findAllPetMom(@PageableDefault Pageable pageable) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();

        Page<PetMomDTO> petMoms = petmomService.findAllPetMoms(pageable);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petMoms.getContent(), selectCriteria);
        responseMap.put("petmoms", petMoms.getContent());


        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }


    @GetMapping("/list/{boardid}")
    @ApiOperation(value = "펫시터 상세 조회")
    public ResponseEntity<ResponseDto> findList(@PathVariable("boardid") int boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        PetMomDTO petMom = petmomService.findPetMomByBoardNo(boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", petMom));
        //    @GetMapping("/list/find")
        //    public  ResponseEntity<ResponseDto> findPetMom(@PageableDefault Pageable pageable,
        //                                                   @RequestParam(name= "location",defaultValue ="")String location,
        //                                                   @RequestParam(name ="startDate",defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        //                                                   @RequestParam(name = "endDate",defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate,
        //                                                   @RequestParam(name="petYN", defaultValue = "")Boolean petYN,
        //                                                   @RequestParam(name="other", defaultValue = "")String other){
        //        HttpHeaders headers = new HttpHeaders();
        //        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //
        //        Page<PetMomDTO> petMoms = petmomService.findPetMom(pageable,location,startDate,endDate,petYN,other);
        //        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);
        //
        //        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petMoms.getContent(), selectCriteria);
        //        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
        //    }
    }

    @PutMapping("/modify")
    public ResponseEntity<ResponseDto> modifyPetMom(@RequestBody PetMomDTO modifyPetMom) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petmomService.modifyPetMom(modifyPetMom);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "수정 성공", null));
    }

    @DeleteMapping("/{borderId}")
    public ResponseEntity<ResponseDto> deletePetMom(@PathVariable int borderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petmomService.deletePetMom(borderId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "삭제 성공", null));
    }

    @ApiOperation(value = "모든 리뷰 목록 조회")
    @GetMapping("/mypetmoms")
    public ResponseEntity<ResponseDto> findMyPetMom(@PageableDefault Pageable pageable, @RequestParam long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<PetMomDTO> petMoms = petmomService.findMyPetMom(pageable, memberId);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(petMoms);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(petMoms.getContent(), selectCriteria);
        //        responseMap.put("reviews", reviews.getContent());

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "조회성공", data), headers, HttpStatus.OK);
    }

    @ApiOperation(value = "펫맘 모집취소")
    @PutMapping("/list/{boardid}/collectcancle")
    public ResponseEntity<ResponseDto> updateMomCancle(@RequestBody PetMomDTO cancleMom, @PathVariable("boardId") long boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        petmomService.updateMomCancle(cancleMom, boardId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "취소상태로 변경 성공", null));
    }


}