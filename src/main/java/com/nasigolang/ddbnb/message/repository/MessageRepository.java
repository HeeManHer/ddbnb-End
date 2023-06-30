package com.nasigolang.ddbnb.message.repository;

import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.message.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteByMsgIdIn(List<Long> messageId);

    Page<Message> findByWhom(Pageable page, Optional<Member> whom);

    Page<Message> findByWho(Pageable page, Optional<Member> byId);
}
