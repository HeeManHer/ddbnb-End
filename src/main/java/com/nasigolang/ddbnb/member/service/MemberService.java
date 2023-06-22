package com.nasigolang.ddbnb.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasigolang.ddbnb.member.dto.MemberDTO;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.member.repository.MemberMapper;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Transactional
    public long registNewUser(MemberDTO newMember) {
        System.out.println(9);
        newMember.setNickname("새로운회원" + (Math.random() * 100 + 1));
        System.out.println(10);
        return memberRepository.save(modelMapper.map(newMember, Member.class)).getMemberId();
    }

    //일부 멤버 조회
    public MemberSimpleDTO findMemberById(long memberId) {

        Member foundMember = memberRepository.findById(memberId).get();

        return modelMapper.map(foundMember, MemberSimpleDTO.class);
    }

    @Transactional
    public MemberSimpleDTO updateprofile(long memberId, MemberSimpleDTO updateMember) {

        Member member = memberRepository.findById(memberId).get();

        member.setNickname(updateMember.getNickname());
        member.setPetSitterCareer(updateMember.getPetSitterCareer());
        member.setExperience(updateMember.getExperience());
        member.setDetailedHistory(updateMember.getDetailedHistory());
        member.setPreferredArea(updateMember.getPreferredArea());

        return modelMapper.map(member, MemberSimpleDTO.class);
    }

    @Transactional
    public void deleteMember(long memberId) {

        Member foundMember = memberRepository.findById(memberId).get();

        memberRepository.delete(foundMember);
    }

    public Page<MemberSimpleDTO> findAllMembers(Pageable page, Map<String, Object> searchValue) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(), Sort.by("memberId"));

        Page<MemberSimpleDTO> members;

        if(searchValue.isEmpty()) {
            members = memberRepository.findAll(page).map(member -> modelMapper.map(member, MemberSimpleDTO.class));
        } else {
            List<MemberSimpleDTO> memberList = memberMapper.searchMember(searchValue);

            int start = page.getPageNumber() * page.getPageSize();
            int end = Math.min(start + page.getPageSize(), memberList.size());

            members = new PageImpl<>(memberList.subList(start, end), page, memberList.size());
        }

        return members;
    }
  
    public Member findBySocialId(String socialLogin, String socialId) {
        System.out.println(5);
        Member foundMember = memberRepository.findBySocialId(socialLogin, socialId);
        System.out.println(6);
        if(foundMember == null) {
            return null;
        } else {
            return foundMember;
        }
    }

    public int findAllMemberAmount() {
        return memberRepository.findMemberAmount();
    }

    public int findTodayVisitant(LocalDate now) {
        return memberRepository.findByLastVisitDate(now);
    }

    public int findNewMemberAmount(LocalDate now) {
        return memberRepository.findNewMember(now);
    }


}