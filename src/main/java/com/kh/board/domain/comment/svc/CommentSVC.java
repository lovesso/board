package com.kh.board.domain.comment.svc;

import com.kh.board.domain.entity.Comment;

import java.util.List;

public interface CommentSVC {

  //댓글 등록
  Long save(Comment comment);

  //댓글 목록
  List<Comment> findAll(Long boardId);

  //총레코드건수
  int totalCnt();

  //댓글삭제
  int deleteById(Long commentsId);

  //댓글수정
  int updateById(Long commentsId, Comment comment);

}

