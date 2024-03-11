package com.kh.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment { //댓글 객체
  private Long commentsId;      // COMMENTS_ID   // 댓글 id
  private Long boardId;          // BLOG_ID	    // 게시글 id
  private String cContent;      // CCONTENT	           // 댓글 내용
  private String writer;         // WRITER	// 작성자
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime cdate;   // CDATE	       // 작성날짜
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime udate;   // UDATE	    // 수정날짜
}
