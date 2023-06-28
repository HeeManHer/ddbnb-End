package com.nasigolang.ddbnb.pet.petmom.repositroy;

import com.nasigolang.ddbnb.pet.petmom.dto.PetMomDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;
@Mapper
public interface PetMomMapper {
    List<PetMomDTO> searchPetMom(Map<String, Object> searchValue);
}
