package com.kh.board.domain.member.svc;

import com.kh.board.domain.entity.Member;

import java.util.Optional;

public interface MemberSVC {

  //회원가입
  Long joinMember(Member member);

  //회원조회 ---> 1) 동일한 이메일 있는지? 2)회원이 있는지?
  // 1) 동일한 이메일 있는지?
  boolean hasEmail(String email);

  //2)회원이 있는지?
  Optional<Member> findByEmailAndPasswd(String email, String passwd);
}
