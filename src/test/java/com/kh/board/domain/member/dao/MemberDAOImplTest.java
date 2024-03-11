package com.kh.board.domain.member.dao;

import com.kh.board.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class MemberDAOImplTest {
  @Autowired
  MemberDAO memberDAO;

 // @Test
  void test() {
    log.info("memberDAO={}", memberDAO.getClass().getName());
  }

  //@Test
  @DisplayName("회원가입")
  void insertMember() {
    Member member = new Member();
    member.setEmail("user1@kh.com");
    member.setPasswd("user1");
    member.setNickname("사용자1");
    Long memberId = memberDAO.joinMember(member);
    log.info("memberId={}}", memberId);  //테스트 돌려보면 오라클에 사용자1이 등록된다. 성공!
  }

  //@Test
  @DisplayName("이메일 중복(Y)")
  void hasEmail(){
    boolean yesEmail = memberDAO.hasEmail("user1@kh.com");
    Assertions.assertThat(yesEmail).isEqualTo(true);
  } //이메일 존재 확인. 성공!

 // @Test
  @DisplayName("이메일 중복(N)")
  void hasNoEmail(){
    boolean noEmail = memberDAO.hasEmail("dkdkdkd@ksks.com");
    Assertions.assertThat(noEmail).isEqualTo(false);
  } //동일한 이메일 없음. 성공!

  //@Test
  @DisplayName("이메일비번조회(Y)")
  void findByEmailAndPasswd() {
    Optional<Member> byEmailAndPasswd = memberDAO.findByEmailAndPasswd("aa@kh.com","aa");
    Assertions.assertThat(byEmailAndPasswd).isPresent();

    Member chkMember = byEmailAndPasswd.get();
    Assertions.assertThat(chkMember.getEmail()).isEqualTo("aa@kh.com");
    Assertions.assertThat(chkMember.getPasswd()).isEqualTo("aa");
  } //성공

  //@Test
  @DisplayName("이메일비번조회(N)")
  void findByEmailAndPasswd2() {
    Optional<Member> optionalMember = memberDAO.findByEmailAndPasswd("user9@kh.com", "user1");
    // 결과 검증
    Assertions.assertThat(optionalMember).isEmpty(); // Optional이 없어 함
  }
}
