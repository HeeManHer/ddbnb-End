package com.nasigolang.ddbnb.member.repository;

import com.nasigolang.ddbnb.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member AS m WHERE m.socialLogin LIKE :socialLogin AND m.socialId LIKE :socialId")
    Member findBySocialId(String socialLogin, String socialId);

    @Query("SELECT COUNT(*) FROM Member m")
    int findMemberAmount();

    @Query("SELECT COUNT(*) FROM Member m WHERE m.lastVisitDate = :now")
    int findByLastVisitDate(LocalDate now);

    @Query("SELECT COUNT(*) FROM Member m WHERE m.signDate = :now")
    int findNewMember(LocalDate now);
}