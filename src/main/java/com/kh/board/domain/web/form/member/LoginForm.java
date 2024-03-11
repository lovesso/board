package com.kh.board.domain.web.form.member;

import lombok.Data;

@Data
public class LoginForm {
  private String email;
  private String passwd;
}
