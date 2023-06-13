package com.nasigolang.ddbnb.report.service;

import com.nasigolang.ddbnb.report.dto.ReportDTO;
import com.nasigolang.ddbnb.report.entity.Report;
import com.nasigolang.ddbnb.report.repositroy.ReportRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void registNewReport(ReportDTO newReport) {
        reportRepository.save(modelMapper.map(newReport, Report.class));
    }

    public List<ReportDTO> findReportAmount(String category, LocalDate yesterDay) {

        return reportRepository.findByReportCategoryAndReportDate(category, yesterDay).stream().map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
    }

    public Page<ReportDTO> findAllReport(Pageable page, String category) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("reportDate").descending());

        return reportRepository.findByReportCategory(page, category).map(report -> modelMapper.map(report, ReportDTO.class));

    }

    public Page<ReportDTO> findReportByToday(Pageable page, LocalDate now) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("reportDate").descending());

        return reportRepository.findByReportDate(page, now).map(report -> modelMapper.map(report, ReportDTO.class));
    }
}
