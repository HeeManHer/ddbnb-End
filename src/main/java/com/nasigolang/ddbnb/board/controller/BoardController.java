package com.nasigolang.ddbnb.board.controller;

import com.nasigolang.ddbnb.board.dto.BoardDTO;
import com.nasigolang.ddbnb.board.service.BoardService;
import com.nasigolang.ddbnb.common.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "펫맘 모집상태변경")
    @PutMapping("/board/status")
    public ResponseEntity<ResponseDTO> updateMomCancle(@RequestBody BoardDTO status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        boardService.updateMomCancel(status);

        return ResponseEntity.ok().headers(headers).body(new ResponseDTO(HttpStatus.OK, "상태 변경 성공", null));
    }


}