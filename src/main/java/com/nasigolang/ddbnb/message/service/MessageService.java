package com.nasigolang.ddbnb.message.service;

import com.nasigolang.ddbnb.member.repository.MemberRepository;
import com.nasigolang.ddbnb.message.dto.MessageDTO;
import com.nasigolang.ddbnb.message.entity.Message;
import com.nasigolang.ddbnb.message.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public Page<MessageDTO> findSendMessageList(Pageable page, long whom) {

        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                              Sort.by("msgId")
                                  .descending());

        return messageRepository.findByWhom(page, memberRepository.findById(whom))
                                .map(list -> modelMapper.map(list, MessageDTO.class));
    }


    public Page<MessageDTO> findReceiveMessageList(Pageable page, long whom) {
        page = PageRequest.of(page.getPageNumber() <= 0 ? 0 : page.getPageNumber() - 1, page.getPageSize(),
                              Sort.by("msgId")
                                  .descending());

        return messageRepository.findByWho(page, memberRepository.findById(whom))
                                .map(list -> modelMapper.map(list, MessageDTO.class));
    }

    @Transactional
    public void registNewmessage(MessageDTO newMessage) {

        messageRepository.save(modelMapper.map(newMessage, Message.class));
    }

    @Transactional
    public void modifySample(MessageDTO modifyMessage) {

        Message message = messageRepository.findById(modifyMessage.getMsgId()).get();

        message.setMsgContent(modifyMessage.getMsgContent());
    }

    @Transactional
    public void deleteSample(List<Long> messageId) {

        messageRepository.deleteByMsgIdIn(messageId);
    }

    public MessageDTO findMessage(long messageId) {

        return modelMapper.map(messageRepository.findById(messageId).get(), MessageDTO.class);
    }

}
