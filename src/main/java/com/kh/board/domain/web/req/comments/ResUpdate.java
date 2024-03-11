package com.kh.board.domain.web.req.comments;

import lombok.Data;

@Data
public class ResUpdate {
  private Long commentsId;
  private String cContent;
}
