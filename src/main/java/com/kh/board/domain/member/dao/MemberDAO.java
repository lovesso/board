package com.kh.board.domain.member.dao;

import com.kh.board.domain.entity.Member;

import java.util.Optional;

public interface MemberDAO {

  //1) 회원 가입
  Long joinMember(Member member);

  //2) 회원 조회 ---> 1) 이메일 조회 2)회원 조회

 // 1) 이메일 조회
  boolean hasEmail(String email); //있는 경우, 없는 경우 2가지 -> boolean

 // 2)회원 조회
  Optional<Member> findByEmailAndPasswd(String email, String passwd);

}
