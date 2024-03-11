package com.kh.board.domain.web.req.comments;

import lombok.Data;

@Data
public class ReqSave {
  private Long commentsId;
  private Long boardId;
  private String writer;
  private String cContent;
}
