package com.nasigolang.ddbnb.Pet.petsitter.service;

import com.nasigolang.ddbnb.Pet.petsitter.dto.PetsitterboardDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PetsitterService {
    public Page<PetsitterboardDTO> findMenuList(Pageable page) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));

        return null;
    }
}
