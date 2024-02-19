package com.kh.board.domain.board.svc;

import com.kh.board.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardSVC {

  //게시글 작성
  Long save(Board board);

  //게시글 조회
  Optional<Board> findById(Long boardId);

  //게시판 목록
  List<Board> findAll();

  //단건삭제
  int deleteById(Long boardId);

  int deleteByIds(List<Long> boardIds);

  //수정
  int updateById(Long boardId, Board board);
}

