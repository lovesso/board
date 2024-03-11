package com.kh.board.domain.web.form.member;

import lombok.Data;

@Data
public class JoinForm {
  private String email;
  private String passwd;
  private String nickname;
}
