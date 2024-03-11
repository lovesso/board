package com.kh.board.domain.web.req.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter@AllArgsConstructor
public class ResSave {
  private Long commentsId;
  private Long boardId;
  private String cContent;
  private String writer;
}
