package com.nasigolang.ddbnb.board.service;

import com.nasigolang.ddbnb.board.dto.BoardDTO;
import com.nasigolang.ddbnb.board.entity.Board;
import com.nasigolang.ddbnb.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void updateMomCancel(BoardDTO status) {
        Board board = boardRepository.findById(status.getBoardId()).get();

        board.setBoardStatus(status.getBoardStatus());
    }
}

