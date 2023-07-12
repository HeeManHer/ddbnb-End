package com.nasigolang.ddbnb.member.controller;

import com.nasigolang.ddbnb.common.ResponseDTO;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDTOWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.member.service.MemberService;
import com.nasigolang.ddbnb.report.service.ReportService;
import io.swagger.annotations.Api;
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
import java.util.Map;

@Api(tags = "멤버 관련 기능 API")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ReportService reportService;

    @ApiOperation(value = "멤버 소셜 id로 조회")
    @GetMapping("/members/{socialLogin}/{socialId}")
    public ResponseEntity<ResponseDTO> findBySocialId(@PathVariable String social_Login,
                                                      @PathVariable String social_Id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Member foundMember = memberService.findBySocialId(social_Login, social_Id);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", foundMember);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "소셜 아이디 검색 성공", responseMap));
    }

    @GetMapping("/member")
    public ResponseEntity<ResponseDTO> findAllMember(@PageableDefault Pageable page,
                                                     @RequestParam(name = "nickname", defaultValue = "") String nickname,
                                                     @RequestParam(name = "signDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate signDate) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> searchValue = new HashMap<>();

        if (!nickname.equals("")) {
            searchValue.put("nickname", "%" + nickname + "%");
        }

        if (signDate != null) {
            searchValue.put("signDate", signDate);
        }

        Page<MemberSimpleDTO> memberList = memberService.findAllMembers(page, searchValue);

        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(memberList);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(memberList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
    }

    @GetMapping("/amount")
    public ResponseEntity<ResponseDTO> findMemberAmount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        LocalDate today = LocalDate.now();

        Map<String, Integer> memberAmount = new HashMap<>();
        memberAmount.put("allMember", memberService.findAllMemberAmount());
        memberAmount.put("todayVisitant", memberService.findTodayVisitant(today));
        memberAmount.put("newMember", memberService.findNewMemberAmount(today));

        memberAmount.put("memberReport", reportService.findReportAmount("회원", today).size());
        memberAmount.put("boardReport", reportService.findReportAmount("게시글", today).size());

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", memberAmount));
    }

    //일부 멤버 조회
    @ApiOperation("멤버코드로 멤버 정보 조회")
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ResponseDTO> findMemberById(@PathVariable long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MemberSimpleDTO foundMember = memberService.findMemberById(memberId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("members", foundMember);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회성공", responseMap));
    }

    @ApiOperation("프로필 등록")
    @PutMapping("/member/{memberId}/postprofile")
    public ResponseEntity<ResponseDTO> updateprofile(@PathVariable long memberId,
                                                     @RequestPart("newProfile") MemberSimpleDTO memberSimpleDTO,
                                                     @RequestPart(value = "image", required = false) MultipartFile image) {


        return ResponseEntity.ok()
                             .body(new ResponseDTO(HttpStatus.CREATED, "프로필 수정 성공",
                                                   memberService.updateprofile(memberId, memberSimpleDTO, image)));

    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable long memberId) {

        memberService.deleteMember(memberId);

        return ResponseEntity.noContent().build();
    }

}