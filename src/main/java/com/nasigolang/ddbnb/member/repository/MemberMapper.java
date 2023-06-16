package com.nasigolang.ddbnb.member.repository;

import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    List<MemberSimpleDTO> searchMember(Map<String, Object> searchValue);
}
