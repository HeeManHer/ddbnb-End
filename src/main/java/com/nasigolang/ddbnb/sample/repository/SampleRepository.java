package com.nasigolang.ddbnb.sample.repository;

import com.nasigolang.ddbnb.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Integer> {}
