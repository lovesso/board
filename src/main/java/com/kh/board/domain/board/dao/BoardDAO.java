package com.kh.board.domain.board.dao;

import com.kh.board.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {

  //게시글 작성
  Long save(Board board);

  //게시글 단건 조회: 최대 1개 저장 Optional
  Optional<Board> findById(Long boardId);

  //게시글 전체 목록: List<> 모든 데이터를 조회하고 리스트 형태로 반환.
  List<Board> findAll(Long reqPage, Long recCnt);

  //단건 삭제
  int deleteById(Long boardId);

  //여러건 삭제
  int deleteByIds(List<Long> boardIds);

  //수정
  int updateById(Long boardId, Board board);

  //총레코드 건수
  int totalCnt();
}
