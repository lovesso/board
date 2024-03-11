package com.kh.board.domain.comment.dao;

import com.kh.board.domain.entity.Comment;

import java.util.List;

public interface CommentDAO {

  //등록, 목록, 삭제, 수정

  //등록
  Long save(Comment comment);

  //목록 전체 (boardId = 게시물 선택)
  List<Comment> findAll(Long boardId);

  //삭제
  int deleteById(Long commentsId);

  //수정.
  int updateById(Long commentsId, Comment comment);

  //총레코드 건수
  int totalCnt();

}
