package com.nasigolang.ddbnb.report.service;

import com.nasigolang.ddbnb.report.dto.ReportDTO;
import com.nasigolang.ddbnb.report.entity.Report;
import com.nasigolang.ddbnb.report.repositroy.ReportRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;



    @Transactional
    public void registNewReport(ReportDTO newReport) {
       reportRepository.save(modelMapper.map(newReport, Report.class));
    }
}
