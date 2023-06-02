package com.nasigolang.ddbnb.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.nasigolang.ddbnb"}, annotationClass = Mapper.class)
public class MybatisConfig {

}
