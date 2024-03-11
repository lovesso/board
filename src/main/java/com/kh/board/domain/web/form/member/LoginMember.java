package com.kh.board.domain.web.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class LoginMember {   //회원정보
  private Long memberId;            //	NUMBER(10,0) 수정안되니까 수정방법을 막아둠 => getter는 모두열어두고 setter는 nickname과 gubun만 가능하도록
  private String email;             //	VARCHAR2(50 BYTE) 수정안되니까 수정방법을 막아둠
  private String nickname;          //	VARCHAR2(30 BYTE) 변경가능
  private String gubun;             //	VARCHAR2(11 BYTE)

  //변경요인을 최소화하는게 오류를 방지하는 최소한의 방법. getter 로 모든 정보는 읽되 아래 두개만 setter를 만들어서 수정이 가능하도록
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setGubun(String gubun) {
    this.gubun = gubun;
  }
}