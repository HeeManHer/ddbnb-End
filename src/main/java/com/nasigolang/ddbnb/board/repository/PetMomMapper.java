package com.nasigolang.ddbnb.board.repository;

import com.nasigolang.ddbnb.board.dto.PetMomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PetMomMapper {
    List<PetMomDTO> searchPetMom(Map<String, Object> searchValue);
}
