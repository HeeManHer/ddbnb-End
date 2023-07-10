package com.nasigolang.ddbnb.member.service;

import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.member.dto.MemberDTO;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.member.repository.MemberMapper;
import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.util.FileUploadUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final FileUploadUtils fileUploadUtils;

    @Transactional
    public long registNewUser(MemberDTO newMember) {
        newMember.setNickname("새로운회원" + (Math.random() * 100 + 1));
        return memberRepository.save(modelMapper.map(newMember, Member.class)).getMemberId();
    }

    //일부 멤버 조회
    public MemberSimpleDTO findMemberById(long memberId) {

        Member foundMember = memberRepository.findById(memberId).get();
        return modelMapper.map(foundMember, MemberSimpleDTO.class);
    }

    @Transactional
    public MemberSimpleDTO updateprofile(long memberId, MemberSimpleDTO updateMember, MultipartFile image) {

        Member member = memberRepository.findById(memberId).get();

        member.setNickname(updateMember.getNickname());
        member.setPetSitterCareer(updateMember.getPetSitterCareer());
        member.setExperience(updateMember.getExperience());
        member.setDetailedHistory(updateMember.getDetailedHistory());
        member.setPreferredArea(updateMember.getPreferredArea());

        if (image != null) {
            try {
                String replaceFileName = fileUploadUtils.saveFile(image);
                member.setProfileImage(replaceFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return modelMapper.map(member, MemberSimpleDTO.class);
    }

    @Transactional
    public void deleteMember(long memberId) {

        Member foundMember = memberRepository.findById(memberId).get();

        memberRepository.delete(foundMember);
    }

    public Page<MemberSimpleDTO> findAllMembers(Pageable page, Map<String, Object> searchValue) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                              Sort.by("memberId"));

        Page<MemberSimpleDTO> members;

        if (searchValue.isEmpty()) {
            members = memberRepository.findAll(page).map(member -> modelMapper.map(member, MemberSimpleDTO.class));
        } else {
            List<MemberSimpleDTO> memberList = memberMapper.searchMember(searchValue);

            members = (Page<MemberSimpleDTO>) Pagenation.createPage(memberList, page);
        }

        return members;
    }

    public Member findBySocialId(String socialLogin, String socialId) {

        return memberRepository.findBySocialId(socialLogin, socialId);
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