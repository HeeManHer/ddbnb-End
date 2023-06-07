package com.nasigolang.ddbnb.login.repository;


import com.nasigolang.ddbnb.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Member, Long> {
}
