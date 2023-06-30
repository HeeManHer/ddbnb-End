package com.nasigolang.ddbnb.message.controller;

import com.nasigolang.ddbnb.common.ResponseDto;
import com.nasigolang.ddbnb.common.paging.Pagenation;
import com.nasigolang.ddbnb.common.paging.ResponseDtoWithPaging;
import com.nasigolang.ddbnb.common.paging.SelectCriteria;
import com.nasigolang.ddbnb.message.dto.MessageDTO;
import com.nasigolang.ddbnb.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @GetMapping("/messages/receive/{whom}")
    public ResponseEntity<ResponseDto> findSendMessage(@PageableDefault Pageable page, @PathVariable long whom) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<MessageDTO> messageList = messageService.findSendMessageList(page, whom);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(messageList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(messageList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @GetMapping("/messages/send/{whom}")
    public ResponseEntity<ResponseDto> findReceiveMessage(@PageableDefault Pageable page, @PathVariable long whom) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<MessageDTO> messageList = messageService.findReceiveMessageList(page, whom);
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(messageList);

        ResponseDtoWithPaging data = new ResponseDtoWithPaging(messageList.getContent(), selectCriteria);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "조회 성공", data));
    }

    @GetMapping("/message/{messageId}")
    public ResponseEntity<ResponseDto> findMessage(@PathVariable long messageId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                             .headers(headers)
                             .body(new ResponseDto(HttpStatus.OK, "조회 성공", messageService.findMessage(messageId)));
    }

    @PostMapping("/message")
    public ResponseEntity<ResponseDto> registNewMessage(@RequestBody MessageDTO newMessage) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        newMessage.setWriteDate(new Date());

        messageService.registNewmessage(newMessage);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.CREATED, "생성 성공", null));

    }

    @PutMapping("/message")
    public ResponseEntity<ResponseDto> modifyMessage(@RequestBody MessageDTO modifyMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        messageService.modifySample(modifyMessage);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.OK, "수정 성공", null));
    }

    @DeleteMapping("/messages")
    public ResponseEntity<ResponseDto> deleteMessage(@RequestBody List<Long> messageId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        messageService.deleteSample(messageId);

        return ResponseEntity.ok().headers(headers).body(new ResponseDto(HttpStatus.NO_CONTENT, "삭제 성공", null));
    }


}
