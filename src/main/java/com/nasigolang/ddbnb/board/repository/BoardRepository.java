package com.nasigolang.ddbnb.board.repository;

import com.nasigolang.ddbnb.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findByBoardCategoryContaining(String category);
}
