package com.nasigolang.ddbnb.pet.petsitter.repository;

import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PetSitterMapper {
    List<PetsitterboardDTO> searchPetSitter(Map<String, Object> searchValue);

}
