package com.nasigolang.ddbnb.member.controller;

import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.member.dto.MemberDTO;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.member.service.MemberService;
import com.nasigolang.ddbnb.report.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.HashMap;
import java.util.Map;

@Api(tags = "멤버 관련 기능 API")
@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final ReportService reportService;
    private final ModelMapper modelMapper;
    private final HttpHeaders headers;

    @Autowired
    public MemberController(MemberService memberService, ModelMapper modelMapper, ReportService reportService) {

        this.memberService = memberService;
        this.modelMapper = modelMapper;
        this.reportService = reportService;

        this.headers = new HttpHeaders();
        this.headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    }

    @ApiOperation(value = "멤버 소셜 id로 조회")
    @GetMapping("/members/{socialLogin}/{socialId}")
    public ResponseEntity<ResponseDto> findBySocialId(@PathVariable String socialLogin, @PathVariable String socialId) {

        MemberDTO foundMember = memberService.findBySocialId(socialLogin, socialId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", foundMember);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "소셜 아이디 검색 성공", responseMap));
    }

    @GetMapping("/member")
    public ResponseEntity<ResponseDto> findAllMember(@PageableDefault Pageable page,
            @RequestParam(name = "nickname", defaultValue = "") String nickname,
            @RequestParam(name = "signDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate signDate) {

        System.out.println(page);
        System.out.println(signDate);

        Page<MemberSimpleDTO> memberList = memberService.findAllMembers(page, nickname, signDate);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(memberList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(memberList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }


    @GetMapping("/amount")
    public ResponseEntity<ResponseDto> findMemberAmount() {

        LocalDate today = LocalDate.now();

        Map<String, Integer> memberAmount = new HashMap<>();
        memberAmount.put("allMember", memberService.findAllMemberAmount());
        memberAmount.put("todayVisitant", memberService.findTodayVisitant(today));
        memberAmount.put("newMember", memberService.findNewMemberAmount(today));

        memberAmount.put("memberReport", reportService.findReportAmount("회원", today).size());
        memberAmount.put("boardReport", reportService.findReportAmount("게시글", today).size());

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", memberAmount));
    }

    //일부 멤버 조회
    @ApiOperation("멤버코드로 멤버 정보 조회")
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ResponseDto> findMemberById(@PathVariable long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MemberSimpleDTO foundMember = memberService.findMemberById(memberId);

        System.out.println(foundMember);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("members", foundMember);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회성공", responseMap));
    }
}

