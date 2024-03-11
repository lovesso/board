package com.kh.board.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {  //게시판 객체

  private Long boardId;           //게시판 아이디
  private String title;             //제목
  private String content;             //내용
  private String writer;              //작성자
  private LocalDateTime cdate;      //생성일시
  private LocalDateTime udate;      //수정일시
}