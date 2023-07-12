package com.nasigolang.ddbnb.board.repository;

import com.nasigolang.ddbnb.board.dto.PetSitterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PetSitterMapper {
    List<PetSitterDTO> searchPetSitter(Map<String, Object> searchValue);

}
