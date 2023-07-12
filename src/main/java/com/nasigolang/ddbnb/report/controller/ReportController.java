package com.nasigolang.ddbnb.report.controller;

import com.nasigolang.ddbnb.common.ResponseDTO;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDTOWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.report.dto.ReportDTO;
import com.nasigolang.ddbnb.report.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @PostMapping("/report")
    public ResponseEntity<ResponseDTO> registNewReport(@RequestBody ReportDTO newReport) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        newReport.setReportDate(LocalDate.now());

        reportService.registNewReport(newReport);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "생성 성공", null));

    }

    @GetMapping("/report")
    public ResponseEntity<ResponseDTO> findAllReport(@PageableDefault Pageable page, @RequestParam String category) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        System.out.println(category);

        Page<ReportDTO> reportList = reportService.findAllReport(page, category);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(reportList);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(reportList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
    }

    @GetMapping("/report/today")
    public ResponseEntity<ResponseDTO> findReportByToday(@PageableDefault Pageable page) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<ReportDTO> reportList = reportService.findReportByToday(page, LocalDate.now());
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(reportList);

        ResponseDTOWithPaging data = new ResponseDTOWithPaging(reportList.getContent(), selectCriteria);


        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
    }

}
