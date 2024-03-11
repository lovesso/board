package com.kh.board.domain.web.api;

import lombok.Getter;

@Getter
public enum ResCode {             //Enum : 한정된 값을 받는 타입
  OK("00"),FAIL("01"),EXIST("21"),NONE("22"),ETC("99");

  private final String code;
  ResCode(String code) {
    this.code = code;

  }
}