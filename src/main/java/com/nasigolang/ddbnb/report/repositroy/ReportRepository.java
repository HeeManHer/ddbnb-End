package com.nasigolang.ddbnb.report.repositroy;

import com.nasigolang.ddbnb.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
