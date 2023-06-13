package com.nasigolang.ddbnb.report.repositroy;

import com.nasigolang.ddbnb.report.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByReportCategoryAndReportDate(String category, LocalDate yesterDay);

    Page<Report> findByReportCategory(Pageable page, String category);

    Page<Report> findByReportDate(Pageable page, LocalDate now);
}
